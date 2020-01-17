package com.roncoo.education.user.service.biz.auth;

import com.roncoo.education.course.common.bean.vo.CourseUserCollectionVO;
import com.roncoo.education.course.feign.IBossCourseUserCollection;
import com.roncoo.education.user.service.common.bo.auth.AuthUserUpdateBO;
import com.roncoo.education.user.service.common.dto.auth.AuthApiUserCollectionsDTO;
import com.roncoo.education.user.service.dao.PlatformDao;
import com.roncoo.education.user.service.dao.SvipDao;
import com.roncoo.education.user.service.dao.UserDao;
import com.roncoo.education.user.service.dao.impl.mapper.entity.Platform;
import com.roncoo.education.user.service.dao.impl.mapper.entity.Svip;
import com.roncoo.education.user.service.dao.impl.mapper.entity.User;
import com.roncoo.education.util.enums.StatusIdEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.roncoo.education.system.feign.IBossSys;
import com.roncoo.education.user.service.common.bo.auth.AuthUserExtBO;
import com.roncoo.education.user.service.common.bo.auth.AuthUserExtViewBO;
import com.roncoo.education.user.service.common.dto.auth.AuthUserExtDTO;
import com.roncoo.education.user.service.dao.UserExtDao;
import com.roncoo.education.user.service.dao.impl.mapper.entity.UserExt;
import com.roncoo.education.util.aliyun.Aliyun;
import com.roncoo.education.util.aliyun.AliyunUtil;
import com.roncoo.education.util.base.BaseBiz;
import com.roncoo.education.util.base.Result;
import com.roncoo.education.util.enums.ResultEnum;
import com.roncoo.education.util.tools.BeanUtil;
import com.xiaoleilu.hutool.util.ObjectUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * 用户教育信息
 *
 * @author wujing
 */
@Component
public class AuthApiUserExtBiz extends BaseBiz {

	@Autowired
	private UserExtDao userExtDao;
	@Autowired
	private UserDao userDao;
	@Autowired
	private IBossSys bossSys;
	@Autowired
	private SvipDao svipDao;
	@Autowired
	private PlatformDao platformDao;
	@Autowired
	private IBossCourseUserCollection bossCollection;
	@Autowired
	private RedisTemplate<String, String> redisTemplate;

	/**
	 * 用户信息查看接口
	 * 
	 * @param authUserExtViewBO
	 * @author wuyun
	 */
	public Result<AuthUserExtDTO> view(AuthUserExtViewBO authUserExtViewBO) {
		if (ObjectUtil.isNull(authUserExtViewBO)) {
			return Result.error("authUserExtUserNoBO不能为空");
		}
		if (null == authUserExtViewBO.getUserNo()) {
			return Result.error("userNo不能为空");
		}
		UserExt userExt = userExtDao.getByUserNo(authUserExtViewBO.getUserNo());
		if (userExt == null) {
			return Result.error("找不到该用户信息");
		}
		AuthUserExtDTO authUserExtDTO = BeanUtil.copyProperties(userExt, AuthUserExtDTO.class);
		Svip svip = svipDao.getByUserNo(authUserExtViewBO.getUserNo());
		if(ObjectUtil.isNull(svip)){
			authUserExtDTO.setIsVip(0);
		}else{
			authUserExtDTO.setIsVip(1);
		}
		User user = userDao.getByUserNo(authUserExtViewBO.getUserNo());
		authUserExtDTO.setOpenId(user.getOpenId());
		return Result.success(authUserExtDTO);
	}

	/**
	 * 用户信息更新接口
	 * 
	 * @param authUserExtBO
	 * @author wuyun
	 */
	public Result<AuthUserExtDTO> update(AuthUserExtBO authUserExtBO) {
		if (authUserExtBO.getId() == null) {
			return Result.error("Id不能为空");
		}
		if (authUserExtBO.getUserNo() == null) {
			return Result.error("userNo不能为空");
		}

		// 根据传入userNo查找到对应的用户信息
		UserExt userExt = userExtDao.getByUserNo(authUserExtBO.getUserNo());
		if (ObjectUtil.isNull(userExt)) {
			return Result.error("没找到对应的用户信息");
		}
		// 如果修改图片删除阿里云oss上的原图片
		if (!StringUtils.isEmpty(authUserExtBO.getHeadImgUrl())) {
			if (!StringUtils.isEmpty(userExt.getHeadImgUrl()) && !authUserExtBO.getHeadImgUrl().equals(userExt.getHeadImgUrl())) {
				AliyunUtil.delete(userExt.getHeadImgUrl(), BeanUtil.copyProperties(bossSys.getSys(), Aliyun.class));
			}
		}
		userExt = BeanUtil.copyProperties(authUserExtBO, UserExt.class);
		int resultNum = userExtDao.updateByUserNo(userExt);
		if (resultNum > 0) {
			// 修改成功返回用户信息
			UserExt education = userExtDao.getByUserNo(authUserExtBO.getUserNo());
			AuthUserExtDTO dto = BeanUtil.copyProperties(education, AuthUserExtDTO.class);
			return Result.success(dto);
		}
		return Result.error(ResultEnum.USER_UPDATE_FAIL.getDesc());

	}

	public Result<Boolean> isVip(AuthUserExtViewBO authUserExtViewBO) {
		Svip svip = svipDao.getByUserNo(authUserExtViewBO.getUserNo());
		if(ObjectUtil.isNull(svip)){
			return Result.success(false);
		}
		return Result.success(true);
	}

	public Result<AuthUserExtDTO> updatePhone(AuthUserUpdateBO authUserUpdateBO) {
		if (StringUtils.isEmpty(authUserUpdateBO.getMobile())) {
			return Result.error("手机号不能为空");
		}
		if (StringUtils.isEmpty(authUserUpdateBO.getId())) {
			return Result.error("Id不能为空");
		}
		Platform platform = platformDao.getByClientId(authUserUpdateBO.getClientId());
		if (null == platform) {
			return Result.error("该平台不存在");
		}
		if (!StatusIdEnum.YES.getCode().equals(platform.getStatusId())) {
			return Result.error("该平台状态异常，请联系管理员");
		}
		// 验证码校验
		String redisSmsCode = redisTemplate.opsForValue().get(platform.getClientId() + authUserUpdateBO.getMobile());
		if (StringUtils.isEmpty(redisSmsCode)) {
			return Result.error("请获取验证码");
		}
		if (StringUtils.isEmpty(authUserUpdateBO.getCode())) {
			return Result.error("请输入验证码");
		}
		if (!redisSmsCode.equals(authUserUpdateBO.getCode())) {
			return Result.error("验证码不正确，请重新输入");
		}
		UserExt userExt = userExtDao.getByMobile(authUserUpdateBO.getMobile());
		if(ObjectUtil.isNotNull(userExt)){
			return Result.error("该手机号码已注册！");
		}
		userExt = BeanUtil.copyProperties(authUserUpdateBO, UserExt.class);
		User user = new User();
		userExtDao.updateById(userExt);
		userExt = userExtDao.getById(authUserUpdateBO.getId());
		user.setUserNo(userExt.getUserNo());
		user.setMobile(authUserUpdateBO.getMobile());
		userDao.updateByUserNo(user);
		AuthUserExtDTO authUserExtDTO = BeanUtil.copyProperties(userExtDao.getById(authUserUpdateBO.getId()), AuthUserExtDTO.class);
		return Result.success(authUserExtDTO);
	}

	public Result<AuthApiUserCollectionsDTO> collections(AuthUserExtViewBO authUserExtViewBO) {
		if(StringUtils.isEmpty(authUserExtViewBO.getUserNo())){
			return Result.error("用户编号不能为空！");
		}
		List<CourseUserCollectionVO> collectionList = bossCollection.getCollectionListByUserNo(authUserExtViewBO.getUserNo());
		List<Long> longList = new ArrayList<>();
		collectionList.forEach(c -> longList.add(c.getCourseId()));
		AuthApiUserCollectionsDTO authApiUserCollectionsDTO = new AuthApiUserCollectionsDTO();
		authApiUserCollectionsDTO.setCourseUserCollections(longList);
		return Result.success(authApiUserCollectionsDTO);
	}
}
