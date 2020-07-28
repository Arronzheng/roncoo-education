package com.roncoo.education.user.service.biz;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;

import com.roncoo.education.user.service.common.WeChatUtil;
import com.roncoo.education.user.service.common.bo.*;
import com.roncoo.education.user.service.common.dto.JssdkConfigDTO;
import com.roncoo.education.user.service.common.dto.UserWXLoginDTO;
import com.roncoo.education.user.service.common.wechat.AccessToken;
import com.roncoo.education.user.service.common.wechat.JsapiTicket;
import com.roncoo.education.util.config.SystemUtil;
import com.roncoo.education.util.enums.*;
import com.roncoo.education.util.tencentcloud.Tencent;
import com.roncoo.education.util.tencentcloud.TencentUtil;
import com.roncoo.education.util.tools.*;
import com.xiaoleilu.hutool.util.CollectionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import com.aliyuncs.exceptions.ClientException;
import com.roncoo.education.system.common.bean.vo.SysVO;
import com.roncoo.education.system.feign.IBossSys;
import com.roncoo.education.user.service.common.bo.auth.UserUpdateBO;
import com.roncoo.education.user.service.common.dto.UserLoginDTO;
import com.roncoo.education.user.service.dao.PlatformDao;
import com.roncoo.education.user.service.dao.SendSmsLogDao;
import com.roncoo.education.user.service.dao.UserDao;
import com.roncoo.education.user.service.dao.UserExtDao;
import com.roncoo.education.user.service.dao.UserLogLoginDao;
import com.roncoo.education.user.service.dao.impl.mapper.entity.Platform;
import com.roncoo.education.user.service.dao.impl.mapper.entity.SendSmsLog;
import com.roncoo.education.user.service.dao.impl.mapper.entity.User;
import com.roncoo.education.user.service.dao.impl.mapper.entity.UserExt;
import com.roncoo.education.user.service.dao.impl.mapper.entity.UserLogLogin;
import com.roncoo.education.util.aliyun.Aliyun;
import com.roncoo.education.util.aliyun.AliyunUtil;
import com.roncoo.education.util.base.BaseBiz;
import com.roncoo.education.util.base.BaseException;
import com.roncoo.education.util.base.Result;
import com.xiaoleilu.hutool.crypto.DigestUtil;
import com.xiaoleilu.hutool.util.ObjectUtil;
import com.xiaoleilu.hutool.util.RandomUtil;

import javax.servlet.http.HttpServletRequest;

/**
 * 用户基本信息
 */
@Component
public class ApiUserInfoBiz extends BaseBiz {

	@Autowired
	private IBossSys bossSys;

	@Autowired
	private PlatformDao platformDao;
	@Autowired
	private UserDao userDao;
	@Autowired
	private UserExtDao userExtDao;
	@Autowired
	private SendSmsLogDao sendSmsLogDao;
	@Autowired
	private UserLogLoginDao userLogLoginDao;

	@Autowired
	private RedisTemplate<String, String> redisTemplate;

	@Transactional
	public Result<UserLoginDTO> register(UserRegisterBO userRegisterBO) {
		if (StringUtils.isEmpty(userRegisterBO.getMobile())) {
			return Result.error("手机号不能为空");
		}
		if (StringUtils.isEmpty(userRegisterBO.getPassword())) {
			return Result.error("密码不能为空");
		}
		if (StringUtils.isEmpty(userRegisterBO.getClientId())) {
			return Result.error("clientId不能为空");
		}

		// 密码校验
		if (!userRegisterBO.getPassword().equals(userRegisterBO.getRepassword())) {
			return Result.error("2次密码不一致");
		}

		Platform platform = platformDao.getByClientId(userRegisterBO.getClientId());
		if (null == platform) {
			return Result.error("该平台不存在");
		}
		if (!StatusIdEnum.YES.getCode().equals(platform.getStatusId())) {
			return Result.error("该平台状态异常，请联系管理员");
		}

		// 验证码校验
		String redisSmsCode = redisTemplate.opsForValue().get(platform.getClientId() + userRegisterBO.getMobile());
		if (StringUtils.isEmpty(redisSmsCode)) {
			return Result.error("请输入验证码");
		}
		if (!redisSmsCode.equals(userRegisterBO.getCode())) {
			return Result.error("验证码不正确，请重新输入");
		}

		// 手机号重复校验
		User user = userDao.getByMobile(userRegisterBO.getMobile());
		if (null != user) {
			return Result.error("该手机号已经注册，请更换手机号");
		}

		// 用户注册
		user = register(userRegisterBO.getMobile(), userRegisterBO.getPassword(), platform.getClientId());

		UserLoginDTO dto = new UserLoginDTO();
		dto.setUserNo(user.getUserNo());
		dto.setMobile(user.getMobile());
		dto.setToken(JWTUtil.create(user.getUserNo(), JWTUtil.DATE));
		return Result.success(dto);
	}

	public Result<UserLoginDTO> loginPassword(UserLoginPasswordBO userLoginPasswordBO) {
		if (StringUtils.isEmpty(userLoginPasswordBO.getClientId())) {
			return Result.error("clientId不能为空");
		}
		if (StringUtils.isEmpty(userLoginPasswordBO.getMobile())) {
			return Result.error("手机号不能为空");
		}
		if (StringUtils.isEmpty(userLoginPasswordBO.getPassword())) {
			return Result.error("密码不能为空");
		}
		Platform platform = platformDao.getByClientId(userLoginPasswordBO.getClientId());
		if (null == platform) {
			return Result.error("该平台不存在");
		}
		if (!StatusIdEnum.YES.getCode().equals(platform.getStatusId())) {
			return Result.error("该平台状态异常，请联系管理员");
		}

		// 密码错误次数校验

		// 用户校验
		User user = userDao.getByMobile(userLoginPasswordBO.getMobile());
		if (null == user) {
			return Result.error("账号或者密码不正确");
		}
		// 密码校验
		if (!DigestUtil.sha1Hex(user.getMobileSalt() + userLoginPasswordBO.getPassword()).equals(user.getMobilePsw())) {
			loginLog(user.getUserNo(), userLoginPasswordBO.getClientId(), LoginStatusEnum.FAIL, userLoginPasswordBO.getIp());
			// 放入缓存，错误次数+1
			return Result.error("账号或者密码不正确");
		}

		// 登录日志
		loginLog(user.getUserNo(), userLoginPasswordBO.getClientId(), LoginStatusEnum.SUCCESS, userLoginPasswordBO.getIp());

		UserLoginDTO dto = new UserLoginDTO();
		dto.setUserNo(user.getUserNo());
		dto.setMobile(user.getMobile());
		dto.setToken(JWTUtil.create(user.getUserNo(), JWTUtil.DATE));

		// 登录成功，存入缓存，单点登录使用
//		 redisTemplate.opsForValue().set(dto.getUserNo().toString(), dto.getToken(),
//		 1, TimeUnit.DAYS);

		return Result.success(dto);
	}

	public Result<UserLoginDTO> loginCode(UserLoginCodeBO userLoginCodeBO) {
		if (StringUtils.isEmpty(userLoginCodeBO.getClientId())) {
			return Result.error("clientId不能为空");
		}
		if (StringUtils.isEmpty(userLoginCodeBO.getMobile())) {
			return Result.error("手机号码不能为空");
		}
		Platform platform = platformDao.getByClientId(userLoginCodeBO.getClientId());
		if (ObjectUtil.isNull(platform)) {
			return Result.error("该平台不存在");
		}
		if (!StatusIdEnum.YES.getCode().equals(platform.getStatusId())) {
			return Result.error("该平台状态异常，请联系管理员");
		}

		// 登录错误次数的校验

		// 验证码校验
		String redisSmsCode = redisTemplate.opsForValue().get(platform.getClientId() + userLoginCodeBO.getMobile());
		if (StringUtils.isEmpty(redisSmsCode)) {
			return Result.error("验证码已经过期，请重新获取");
		}

		User user = userDao.getByMobile(userLoginCodeBO.getMobile());
		if (null == user) {
			return Result.error("该用户不存在");
		}

		if (!redisSmsCode.equals(userLoginCodeBO.getCode())) {
			loginLog(user.getUserNo(), userLoginCodeBO.getClientId(), LoginStatusEnum.FAIL, userLoginCodeBO.getIp());
			// 缓存控制错误次数
			return Result.error("验证码不正确,重新输入");
		}

		// 清空缓存
		redisTemplate.delete(platform.getClientId() + userLoginCodeBO.getMobile());

		// 登录日志
		loginLog(user.getUserNo(), userLoginCodeBO.getClientId(), LoginStatusEnum.SUCCESS, userLoginCodeBO.getIp());

		UserLoginDTO dto = new UserLoginDTO();
		dto.setUserNo(user.getUserNo());
		dto.setMobile(user.getMobile());
		dto.setToken(JWTUtil.create(user.getUserNo(), JWTUtil.DATE));

		// 登录成功，存入缓存，单点登录使用
		// redisTemplate.opsForValue().set(dto.getUserNo().toString(), dto.getToken(),
		// 1, TimeUnit.DAYS);
		return Result.success(dto);
	}

	public Result<String> sendCode(UserSendCodeBO userSendCodeBO) {
		if (StringUtils.isEmpty(userSendCodeBO.getClientId())) {
			return Result.error("clientId不能为空");
		}
		if (!Pattern.compile(Constants.REGEX_MOBILE).matcher(userSendCodeBO.getMobile()).matches()) {
			return Result.error("手机号码格式不正确");
		}

		Platform platform = platformDao.getByClientId(userSendCodeBO.getClientId());
		if (ObjectUtil.isNull(platform)) {
			return Result.error("该平台不存在");
		}
		if (!StatusIdEnum.YES.getCode().equals(platform.getStatusId())) {
			return Result.error("该平台状态异常，请联系管理员");
		}

		SysVO sys = bossSys.getSys();
		if (ObjectUtil.isNull(sys)) {
			return Result.error("找不到系统配置信息");
		}
		// 创建日志实例
		SendSmsLog sendSmsLog = new SendSmsLog();
		sendSmsLog.setMobile(userSendCodeBO.getMobile());
		sendSmsLog.setTemplate(sys.getSmsCode());
		// 随机生成验证码
		sendSmsLog.setSmsCode(RandomUtil.randomNumbers(6));
		try {
			// 发送验证码
			boolean result = false;
			if (sys.getFileType().equals(FileTypeEnum.ALIYUN.getCode())) {
				result = AliyunUtil.sendMsg(userSendCodeBO.getMobile(), sendSmsLog.getSmsCode(), BeanUtil.copyProperties(sys, Aliyun.class));
			} else {
				result = TencentUtil.sendMsg(userSendCodeBO.getMobile(), sendSmsLog.getSmsCode(), BeanUtil.copyProperties(sys, Tencent.class));
			}
			// 发送成功，验证码存入缓存：5分钟有效
			if (result) {
				redisTemplate.opsForValue().set(userSendCodeBO.getClientId() + userSendCodeBO.getMobile(), sendSmsLog.getSmsCode(), 5, TimeUnit.MINUTES);
				sendSmsLog.setIsSuccess(IsSuccessEnum.SUCCESS.getCode());
				sendSmsLogDao.save(sendSmsLog);
				return Result.success("发送成功");
			}
			// 发送失败
			sendSmsLog.setIsSuccess(IsSuccessEnum.FAIL.getCode());
			sendSmsLogDao.save(sendSmsLog);
			return Result.error("发送失败");
		} catch (ClientException e) {
			sendSmsLog.setIsSuccess(IsSuccessEnum.FAIL.getCode());
			sendSmsLogDao.save(sendSmsLog);
			logger.error("发送失败，原因={}", e.getErrMsg());
			return Result.error("发送失败");
		}
	}

	private User register(String mobile, String password, String clientId) {
		// 用户基本信息
		User user = new User();
		user.setUserNo(NOUtil.getUserNo());
		user.setMobile(mobile);
		user.setMobileSalt(StrUtil.get32UUID());
		user.setMobilePsw(DigestUtil.sha1Hex(user.getMobileSalt() + password));
		user.setUserSource(clientId);
		userDao.save(user);

		// 用户其他信息
		UserExt userExt = new UserExt();
		userExt.setUserNo(user.getUserNo());
		userExt.setUserType(UserTypeEnum.USER.getCode());
		userExt.setMobile(user.getMobile());
		userExt.setInviteCode(StrUtil.getCode());
		userExtDao.save(userExt);

		return user;
	}

	private void loginLog(Long userNo, String clientId, LoginStatusEnum status, String ip) {
		UserLogLogin record = new UserLogLogin();
		record.setUserNo(userNo);
		record.setClientId(clientId);
		record.setLoginStatus(status.getCode());
		record.setLoginIp(ip);
		userLogLoginDao.save(record);
	}

	public Result<Integer> updatePassword(UserUpdateBO userUpdateBO) {
		if (StringUtils.isEmpty(userUpdateBO.getMobile())) {
			return Result.error("手机号为空,请重试");
		}
		if (StringUtils.isEmpty(userUpdateBO.getCode())) {
			return Result.error("验证码不能为空");
		}
		if (StringUtils.isEmpty(userUpdateBO.getClientId())) {
			return Result.error("clientId不能为空");
		}

		Platform platform = platformDao.getByClientId(userUpdateBO.getClientId());
		if (null == platform) {
			return Result.error("该平台不存在");
		}
		if (!StatusIdEnum.YES.getCode().equals(platform.getStatusId())) {
			return Result.error("该平台状态异常，请联系管理员");
		}

		String redisCode = redisTemplate.opsForValue().get(platform.getClientId() + userUpdateBO.getMobile());
		if (StringUtils.isEmpty(redisCode)) {
			return Result.error("请输入验证码");
		}
		if (!userUpdateBO.getCode().equals(redisCode)) {
			return Result.error("验证码匹配不正确");
		}
		// 手机号去空处理
		String mobile = userUpdateBO.getMobile().replaceAll(" +", "");

		if (StringUtils.isEmpty(userUpdateBO.getConfirmPassword())) {
			return Result.error("新登录密码为空,请重试");
		}
		if (!userUpdateBO.getNewPassword().equals(userUpdateBO.getConfirmPassword())) {
			return Result.error("密码输入不一致，请重试");
		}

		User user = userDao.getByMobile(mobile);
		if (ObjectUtil.isNull(user)) {
			return Result.error("没找到用户信息,请重试");
		}
		if (DigestUtil.sha1Hex(user.getMobileSalt() + userUpdateBO.getNewPassword()).equals(user.getMobilePsw())) {
			return Result.error("输入的密码与原密码一致,请重试");
		}

		// 更新密码
		User bean = new User();
		bean.setId(user.getId());
		bean.setMobileSalt(StrUtil.get32UUID());
		bean.setMobilePsw(DigestUtil.sha1Hex(bean.getMobileSalt() + userUpdateBO.getNewPassword()));
		int result = userDao.updateById(bean);
		return result == 1 ? Result.success(result) : Result.error(ResultEnum.USER_UPDATE_FAIL.getDesc());
	}

	public Result<UserWXLoginDTO> getAppId() {
		UserWXLoginDTO userWXLoginDTO = new UserWXLoginDTO();
		userWXLoginDTO.setAppid(SystemUtil.APP_ID);
		userWXLoginDTO.setRedirectUri(SystemUtil.REDIRECT_URI);
//		String state = "47CC68A6508DA7038DA8E7EDFD03BE7E3398DA68943D4CFAE2A4A9DFCE3117207037171CAF5D77F789A2260CB831DA66";//测试
		String state = String.valueOf(System.currentTimeMillis());
		redisTemplate.opsForValue().set("state", state);
		userWXLoginDTO.setState(state);
		return Result.success(userWXLoginDTO);
	}

	public Result<JssdkConfigDTO> getConfig(String url) {
//		String url = request.getScheme() +"://" + request.getServerName();

//		String url = "http://bify8t.natappfree.cc";
		if (getJsapiTicket().getCode() == 200) {
			JssdkConfigDTO ret = WeChatUtil.sign(getJsapiTicket().getData().toString(), url);
			System.out.println(ret);
			return Result.success(ret);
		} else {
			return Result.error(getJsapiTicket().getMsg());
		}

	}

	/**
	 * 向外暴露的获取ticket的方法
	 * @return
	 */
	public Result getJsapiTicket() {
		String jt = null;
		if (redisTemplate.hasKey("ticket")) {
			jt = redisTemplate.opsForValue().get("ticket");
		}
		System.out.println("缓存获取ticket：" + jt);
		if (StringUtils.isEmpty(jt)) {
			if (getTicket().getCode() == 200) {
				jt = getTicket().getData().toString();
			} else {
				return Result.error(getTicket().getMsg());
			}
		}
		return Result.success(jt);
	}

	/**
	 * 从微信获取jsapi_ticket并保存
	 */
	private Result getTicket() {
		if (getAccessToken().getCode() == 200) {
			String url = SystemUtil.JSAPI_TICKET_URL.replace("ACCESS_TOKEN", getAccessToken().getData().toString());
			Map<String, Object> resultMap = HttpUtil.doGet(url);
			JsapiTicket jt = new JsapiTicket(resultMap.get("ticket").toString(), resultMap.get("expires_in").toString());
			System.out.println("微信获取ticket：" + jt);
			redisTemplate.opsForValue().set("ticket", jt.getTicket(), Long.valueOf(jt.getExpireIn()), TimeUnit.SECONDS);
			return Result.success(jt.getTicket());
		} else {
			return Result.error(getAccessToken().getMsg());
		}
	}

	/**
	 * 向外暴露的获取token的方法
	 * @return
	 */
	public Result getAccessToken() {
		String at = null;
		if (redisTemplate.hasKey("access_token")) {
			at = redisTemplate.opsForValue().get("access_token");
		}
		System.out.println("缓存获取token：" + at);
		if (StringUtils.isEmpty(at)) {
			if (getToken().getCode() == 200) {
				at = getToken().getData().toString();
			} else {
				return Result.error(getToken().getMsg());
			}
		}
		return Result.success(at);
	}

	/**
	 * 从微信获取普通AccessToken并保存
	 */
	private Result getToken() {
		String url = SystemUtil.ACCESS_TOKEN_URL.replace("APPID", SystemUtil.APP_ID).replace("APPSECRET", SystemUtil.APP_SECRET);
		Map<String, Object> resultMap = HttpUtil.doGet(url);
		if (resultMap.containsKey("access_token")) {
			AccessToken at = new AccessToken(resultMap.get("access_token").toString(), resultMap.get("expires_in").toString());
			System.out.println("微信获取token：" + at.getAccessToken());
			redisTemplate.opsForValue().set("access_token", at.getAccessToken(), Long.valueOf(at.getExpireIn()), TimeUnit.SECONDS);
			return Result.success(at.getAccessToken());
		} else {
			return Result.error(resultMap.get("errmsg").toString());
		}
	}

	public Result<UserLoginDTO> getCode(WeChatCodeBO weChatCodeBO) {
		String storage = redisTemplate.opsForValue().get("state");
		if(weChatCodeBO.getCode() == null){
			return Result.error("请求错误！");
		}
		if(!weChatCodeBO.getState().equals(storage)){
			return Result.error("请求错误！");
		}
		//1、通过openAppid和openAppsecret和微信返回的code，拼接URL
		String accessTokenUrl = SystemUtil.OAUTH_ACCESS_TOKEN_URL.replace("APPID", SystemUtil.APP_ID)
				.replace("SECRET", SystemUtil.APP_SECRET)
				.replace("CODE", weChatCodeBO.getCode());

		//2、通过URL再去回调微信接口 (使用了httpclient和gson工具）
		Map<String ,Object> baseMap =  HttpUtil.doGet(accessTokenUrl);

		//3、回调成功后获取access_token和oppid
		if(baseMap == null || baseMap.isEmpty()){
			return  Result.error("微信登录失败！");
		}
		String accessToken = (String)baseMap.get("access_token");
		String openId  = (String) baseMap.get("openid");

		//4、去数据库查看该用户之前是否已经扫码登陆过（openid是用户唯一标志）
		User user = userDao.findByopenid(openId);
		UserLoginDTO userLoginDTO = new UserLoginDTO();
		if(user!=null) { //如果用户已经存在，直接返回
			userLoginDTO.setUserNo(user.getUserNo());
			userLoginDTO.setMobile(user.getMobile());
			userLoginDTO.setToken(JWTUtil.create(user.getUserNo(), JWTUtil.DATE));
			userLoginDTO.setUserExt(userExtDao.getByUserNo(user.getUserNo()));
			return Result.success(userLoginDTO);
		}

		//4、access_token和openid拼接URL
		String userInfoUrl = SystemUtil.USER_INFO_URL.replace("ACCESS_TOKEN", accessToken)
				.replace("OPENID", openId);

		//5、通过URL再去调微信接口获取用户基本信息
		Map<String ,Object> baseUserMap =  HttpUtil.doGet(userInfoUrl);

		if(baseUserMap == null || baseUserMap.isEmpty()){ return  null; }

		//6、获取用户姓名、性别、城市、头像等基本信息
		String nickname = (String)baseUserMap.get("nickname");
//        Double sexTemp  = (Double) baseUserMap.get("sex");
		int sex = (int) baseUserMap.get("sex");
		String headimgurl = (String)baseUserMap.get("headimgurl");
		try {
			//解决用户名乱码
			nickname = new String(nickname.getBytes("ISO-8859-1"), "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		//7、新用户存入数据库
		UserExt userExt = new UserExt();
		userExt.setNickname(nickname);
		userExt.setHeadImgUrl(headimgurl);
		userExt.setUserNo(NOUtil.getUserNo());
		userExt.setSex(sex);
		userExt.setGmtCreate(new Date());
		userExt.setInviteCode(StrUtil.getCode());
		userExtDao.save(userExt);
		user = new User();
		user.setUserNo(userExt.getUserNo());
		user.setStatusId(StatusIdEnum.YES.getCode());
		user.setOpenId(openId);
		userDao.save(user);

		userLoginDTO.setUserNo(user.getUserNo());
		userLoginDTO.setMobile(user.getMobile());
		userLoginDTO.setToken(JWTUtil.create(user.getUserNo(), JWTUtil.DATE));
		userLoginDTO.setUserExt(userExt);
		return Result.success(userLoginDTO);
	}

	public Result<UserLoginDTO> wechatLogin(WeChatLoginBO weChatLoginBO) {
		if (ObjectUtils.isEmpty(weChatLoginBO)) {
			return Result.error("请求错误！");
		}
		if (1 != weChatLoginBO.getPlatShow()) {
			return Result.error("请求错误！");
		}
		if (!StringUtils.isEmpty(weChatLoginBO.getUserNo())) {
			UserLoginDTO userLoginDTO = new UserLoginDTO();
			UserExt userExt = userExtDao.getByUserNo(weChatLoginBO.getUserNo());
			if(ObjectUtils.isEmpty(userExt)){
				return Result.error("找不到用户信息!");
			}
			userLoginDTO.setUserNo(userExt.getUserNo());
			userLoginDTO.setMobile(userExt.getMobile());
			userLoginDTO.setToken(JWTUtil.create(userExt.getUserNo(), JWTUtil.DATE));
			userLoginDTO.setUserExt(userExt);
			return Result.success(userLoginDTO);
		} else {
			return Result.error("userNo不能为空!");
		}
	}
}
