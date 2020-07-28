package com.roncoo.education.course.service.biz.pc;

import com.qiniu.common.QiniuException;
import com.roncoo.education.system.common.bean.vo.SysVO;
import com.roncoo.education.util.enums.FileTypeEnum;
import com.roncoo.education.util.qiniu.Qiniu;
import com.roncoo.education.util.qiniu.QiniuUtil;
import com.roncoo.education.util.tencentcloud.Tencent;
import com.roncoo.education.util.tencentcloud.TencentUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.roncoo.education.course.service.common.req.AdvDeleteREQ;
import com.roncoo.education.course.service.common.req.AdvPageREQ;
import com.roncoo.education.course.service.common.req.AdvSaveREQ;
import com.roncoo.education.course.service.common.req.AdvUpdateREQ;
import com.roncoo.education.course.service.common.req.AdvViewREQ;
import com.roncoo.education.course.service.common.resq.AdvPageRESQ;
import com.roncoo.education.course.service.common.resq.AdvViewRESQ;
import com.roncoo.education.course.service.dao.AdvDao;
import com.roncoo.education.course.service.dao.impl.mapper.entity.Adv;
import com.roncoo.education.course.service.dao.impl.mapper.entity.AdvExample;
import com.roncoo.education.course.service.dao.impl.mapper.entity.AdvExample.Criteria;
import com.roncoo.education.system.feign.IBossSys;
import com.roncoo.education.util.aliyun.Aliyun;
import com.roncoo.education.util.aliyun.AliyunUtil;
import com.roncoo.education.util.base.Page;
import com.roncoo.education.util.base.PageUtil;
import com.roncoo.education.util.base.Result;
import com.roncoo.education.util.enums.ResultEnum;
import com.roncoo.education.util.tools.BeanUtil;
import com.roncoo.education.util.tools.DateUtil;
import com.xiaoleilu.hutool.util.ObjectUtil;

/**
 * 广告信息
 *
 */
@Component
public class PcApiAdvBiz {

	@Autowired
	private IBossSys bossSys;

	@Autowired
	private AdvDao dao;

	public Result<Page<AdvPageRESQ>> list(AdvPageREQ req) {
		AdvExample example = new AdvExample();
		Criteria c = example.createCriteria();
		if (req.getPlatShow() != null) {
			c.andPlatShowEqualTo(req.getPlatShow());
		}
		if (StringUtils.hasText(req.getAdvTitle())) {
			c.andAdvTitleEqualTo(req.getAdvTitle());
		}
		if (req.getStatusId() != null) {
			c.andStatusIdEqualTo(req.getStatusId());
		}
		example.setOrderByClause(" status_id desc, sort desc, id desc ");
		Page<Adv> page = dao.listForPage(req.getPageCurrent(), req.getPageSize(), example);
		return Result.success(PageUtil.transform(page, AdvPageRESQ.class));
	}

	/**
	 *
	 * 添加轮播广告
	 *
	 * @param req
	 * @return
	 */
	public Result<Integer> save(AdvSaveREQ req) {
		Adv record = BeanUtil.copyProperties(req, Adv.class);
		record.setBeginTime(DateUtil.parseDate(req.getBeginTime(), "yyyy-MM-dd HH:mm:ss"));
		record.setEndTime(DateUtil.parseDate(req.getEndTime(), "yyyy-MM-dd HH:mm:ss"));
		int results = dao.save(record);
		if (results > 0) {
			return Result.success(results);
		}
		return Result.error(ResultEnum.COURSE_SAVE_FAIL);
	}

	/**
	 * 更新广告信息
	 *
	 * @param req
	 * @return
	 */
	public Result<Integer> update(AdvUpdateREQ req) {
		if (StringUtils.isEmpty(req.getId())) {
			return Result.error("ID不能为空");
		}
		Adv adv = dao.getById(req.getId());
		if (ObjectUtil.isNull(adv)) {
			return Result.error("找不到广告信息");
		}
		if (StringUtils.hasText(req.getAdvImg()) && !adv.getAdvImg().equals(req.getAdvImg())) {
			SysVO sys = bossSys.getSys();
			if(sys.getFileType().equals(FileTypeEnum.ALIYUN.getCode())){
				AliyunUtil.delete(adv.getAdvImg(), BeanUtil.copyProperties(sys, Aliyun.class));
			}else if(sys.getFileType().equals(FileTypeEnum.QINIU.getCode())){
				try {
					QiniuUtil.deletePic(adv.getAdvImg(), BeanUtil.copyProperties(sys, Qiniu.class));
				} catch (QiniuException e) {
					return Result.error(e.code(),e.response.toString());
				}
			}else{
				TencentUtil.deleteFile(adv.getAdvImg(), BeanUtil.copyProperties(sys, Tencent.class));
			}
		}
		Adv record = BeanUtil.copyProperties(req, Adv.class);
		record.setBeginTime(DateUtil.parseDate(req.getBeginTime(), "yyyy-MM-dd HH:mm:ss"));
		record.setEndTime(DateUtil.parseDate(req.getEndTime(), "yyyy-MM-dd HH:mm:ss"));
		int results = dao.updateById(record);
		if (results > 0) {
			return Result.success(results);
		}
		return Result.error(ResultEnum.COURSE_UPDATE_FAIL);
	}

	/**
	 * 删除广告信息
	 *
	 * @param req
	 * @return
	 */
	public Result<Integer> delete(AdvDeleteREQ req) {
		if (StringUtils.isEmpty(req.getId())) {
			return Result.error("ID不能为空");
		}
		Adv adv = dao.getById(req.getId());
		if (ObjectUtil.isNull(adv)) {
			return Result.error("找不到广告信息");
		}
		if (!StringUtils.isEmpty(adv.getAdvImg())) {
			SysVO sys = bossSys.getSys();
			if(sys.getFileType().equals(FileTypeEnum.ALIYUN.getCode())){
				AliyunUtil.delete(adv.getAdvImg(), BeanUtil.copyProperties(sys, Aliyun.class));
			}else if(sys.getFileType().equals(FileTypeEnum.QINIU.getCode())){
				try {
					QiniuUtil.deletePic(adv.getAdvImg(), BeanUtil.copyProperties(sys, Qiniu.class));
				} catch (QiniuException e) {
					return Result.error(e.code(),e.response.toString());
				}
			}else{
				TencentUtil.deleteFile(adv.getAdvImg(), BeanUtil.copyProperties(sys, Tencent.class));
			}
		}
		int results = dao.deleteById(req.getId());
		if (results > 0) {
			return Result.success(results);
		}
		return Result.error(ResultEnum.SYSTEM_DELETE_FAIL);
	}

	/**
	 * 广告信息查看
	 *
	 * @param req
	 * @return
	 */
	public Result<AdvViewRESQ> view(AdvViewREQ req) {
		if (StringUtils.isEmpty(req.getId())) {
			return Result.error("ID不能为空");
		}
		Adv record = dao.getById(req.getId());
		return Result.success(BeanUtil.copyProperties(record, AdvViewRESQ.class));
	}

}
