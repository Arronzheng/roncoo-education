package com.roncoo.education.course.service.biz.auth;

import java.util.ArrayList;
import java.util.List;

import com.roncoo.education.course.service.common.dto.auth.AuthCourseCommentDTO;
import com.roncoo.education.course.service.dao.*;
import com.roncoo.education.course.service.dao.impl.mapper.entity.*;
import com.roncoo.education.user.feign.IBossUserExt;
import com.roncoo.education.util.enums.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.roncoo.education.course.service.common.bo.auth.AuthCourseSignBO;
import com.roncoo.education.course.service.common.bo.auth.AuthCourseViewBO;
import com.roncoo.education.course.service.common.dto.CourseChapterDTO;
import com.roncoo.education.course.service.common.dto.CourseChapterPeriodDTO;
import com.roncoo.education.course.service.common.dto.CourseIntroduceDTO;
import com.roncoo.education.course.service.common.dto.auth.AuthCourseSignDTO;
import com.roncoo.education.course.service.common.dto.auth.AuthCourseViewDTO;
import com.roncoo.education.course.service.common.dto.auth.AuthLecturerDTO;
import com.roncoo.education.system.common.bean.vo.SysVO;
import com.roncoo.education.system.feign.IBossSys;
import com.roncoo.education.user.common.bean.vo.LecturerVO;
import com.roncoo.education.user.feign.IBossLecturer;
import com.roncoo.education.util.base.BaseBiz;
import com.roncoo.education.util.base.PageUtil;
import com.roncoo.education.util.base.Result;
import com.roncoo.education.util.config.SystemUtil;
import com.roncoo.education.util.polyv.PolyvCode;
import com.roncoo.education.util.polyv.PolyvSign;
import com.roncoo.education.util.polyv.PolyvSignResult;
import com.roncoo.education.util.polyv.PolyvUtil;
import com.roncoo.education.util.tools.BeanUtil;
import com.xiaoleilu.hutool.util.ObjectUtil;

/**
 * 课程信息
 *
 * @author wujing
 */
@Component
public class AuthApiCourseBiz extends BaseBiz {

	@Autowired
	private CourseAuditDao courseAuditDao;
	@Autowired
	private CourseChapterAuditDao courseChapterAuditDao;
	@Autowired
	private CourseChapterPeriodAuditDao courseChapterPeriodAuditDao;
	@Autowired
	private CourseIntroduceAuditDao courseIntroduceAuditDao;
	@Autowired
	private CourseUserStudyDao courseUserStudyDao;
	@Autowired
	private CourseUserStudyLogDao courseUserStudyLogDao;
	@Autowired
	private IBossLecturer bossLecturer;
	@Autowired
	private OrderInfoDao orderInfoDao;
	@Autowired
	private CourseCommentDao courseCommentDao;
	@Autowired
	private IBossUserExt bossUserExt;

	@Autowired
	private IBossSys bossSys;

	public Result<AuthCourseSignDTO> sign(AuthCourseSignBO authCourseSignBO) {
		if (ObjectUtil.isNull(authCourseSignBO.getUserNo())) {
			return Result.error("userNo不能为空");
		}
		if (ObjectUtil.isNull(authCourseSignBO.getPeriodId())) {
			return Result.error("periodId不能为空");
		}
//		if (StringUtils.isEmpty(authCourseSignBO.getVideoVid())) {
//			return Result.error("videoVid不能为空");
//		}

		// 课时信息
		CourseChapterPeriodAudit courseChapterPeriodAudit = courseChapterPeriodAuditDao.getById(authCourseSignBO.getPeriodId());
		if (StringUtils.isEmpty(courseChapterPeriodAudit)) {
			return Result.error("找不到课时信息");
		}
		// 章节信息
		CourseChapterAudit courseChapterAudit = courseChapterAuditDao.getById(courseChapterPeriodAudit.getChapterId());
		if (ObjectUtil.isNull(courseChapterAudit)) {
			return Result.error("找不到章节信息");
		}
		// 课程信息
		CourseAudit courseAudit = courseAuditDao.getById(courseChapterPeriodAudit.getCourseId());
		if (StringUtils.isEmpty(courseAudit)) {
			return Result.error("找不到课程信息");
		}

		// 免费：课时免费，章节免费，课程免费
		if (IsFreeEnum.FREE.getCode().equals(courseChapterPeriodAudit.getIsFree()) || IsFreeEnum.FREE.getCode().equals(courseChapterAudit.getIsFree()) || IsFreeEnum.FREE.getCode().equals(courseAudit.getIsFree())) {
			AuthCourseSignDTO authCourseSignDTO = getSgin(authCourseSignBO);
			callbackExecutor.execute(new StudyLog(authCourseSignBO, courseChapterPeriodAudit, courseAudit, courseChapterAudit));
			return Result.success(authCourseSignDTO);
		}

		// 收费：订单是否存在并且判断订单是否支付成功
		OrderInfo orderInfo = orderInfoDao.getByUserNoAndCourseId(authCourseSignBO.getUserNo(), courseChapterPeriodAudit.getCourseId());
		if (orderInfo == null || !OrderStatusEnum.SUCCESS.getCode().equals(orderInfo.getOrderStatus())) {
			return Result.error("收费课程，请先购买");
		}

		// 成功
		AuthCourseSignDTO authCourseSignDTO = getSgin(authCourseSignBO);
		callbackExecutor.execute(new StudyLog(authCourseSignBO, courseChapterPeriodAudit, courseAudit, courseChapterAudit));
		return Result.success(authCourseSignDTO);
	}

	public Result<AuthCourseViewDTO> view(AuthCourseViewBO authCourseViewBO) {
		if (StringUtils.isEmpty(authCourseViewBO.getCourseId())) {
			return Result.error("courseId不能为空");
		}
		if (StringUtils.isEmpty(authCourseViewBO.getUserNo())) {
			return Result.error("userNo不能为空");
		}

		// 查询课程信息
		CourseAudit courseAudit = courseAuditDao.getById(authCourseViewBO.getCourseId());
		if (courseAudit == null) {
			return Result.error("找不到该课程");
		}
		AuthCourseViewDTO dto = BeanUtil.copyProperties(courseAudit, AuthCourseViewDTO.class);

		// 查询课程介绍
		CourseIntroduceAudit courseIntroduce = courseIntroduceAuditDao.getById(courseAudit.getIntroduceId());
		dto.setIntroduce(BeanUtil.copyProperties(courseIntroduce, CourseIntroduceDTO.class).getIntroduce());

		// 先假设为收费课程且用户未付款
		dto.setIsPay(IsPayEnum.NO.getCode());
		// 查询订单号，查看用户是否购买了课程，是否存在订单号
		OrderInfo orderInfo = orderInfoDao.getByUserNoAndCourseId(authCourseViewBO.getUserNo(), authCourseViewBO.getCourseId());
		if (ObjectUtil.isNull(orderInfo)) {
			// 未购买或者没支付情况
			dto.setIsPay(IsPayEnum.NO.getCode());
		} else if (OrderStatusEnum.SUCCESS.getCode().equals(orderInfo.getOrderStatus())) {
			// 订单状态为已支付
			dto.setIsPay(IsPayEnum.YES.getCode());
		}
		// 如果课程为免费课程则设置为已付费
		if (IsFreeEnum.FREE.getCode().equals(courseAudit.getIsFree())) {
			dto.setIsPay(IsPayEnum.YES.getCode());
		}

		// 此处用于测试支付课程，无论怎样都是未购买
		if (SystemUtil.TEST_COURSE.equals(courseAudit.getId().toString())) {
			dto.setIsPay(IsPayEnum.NO.getCode());
		}

		// 查询讲师信息
		LecturerVO lecturerVO = bossLecturer.getByLecturerUserNo(dto.getLecturerUserNo());
		dto.setLecturer(BeanUtil.copyProperties(lecturerVO, AuthLecturerDTO.class));

		// 查询章节信息
		List<CourseChapterAudit> courseChapterAuditList = courseChapterAuditDao.listByCourseIdAndStatusId(authCourseViewBO.getCourseId(), StatusIdEnum.YES.getCode());
		// 如果为空就直接返回
		if (courseChapterAuditList.isEmpty()) {
			return Result.success(dto);
		}
		dto.setChapterList(PageUtil.copyList(courseChapterAuditList, CourseChapterDTO.class));
		//评论信息
		List<CourseComment> courseCommentList = courseCommentDao.getByCourseIdAndPid(authCourseViewBO.getCourseId());
		List<AuthCourseCommentDTO> courseCommentDTOList = PageUtil.copyList(courseCommentList, AuthCourseCommentDTO.class);
		for (AuthCourseCommentDTO courseCommentDTO : courseCommentDTOList) {
			courseCommentDTO.setCourseAudit(courseAuditDao.getById(courseCommentDTO.getCourseId()));
			courseCommentDTO.setUserExt(bossUserExt.getByUserNo(courseCommentDTO.getUserId()));
			courseCommentDTO.setIsPackup(1);
			List<CourseComment> childList = courseCommentDao.listByParentId(courseCommentDTO.getId());
			List<AuthCourseCommentDTO> childDTOList = PageUtil.copyList(childList, AuthCourseCommentDTO.class);
			for (AuthCourseCommentDTO childDTO: childDTOList) {
				childDTO.setCourseAudit(courseAuditDao.getById(childDTO.getCourseId()));
				childDTO.setUserExt(bossUserExt.getByUserNo(childDTO.getUserId()));
				childDTO.setParentUserExt(bossUserExt.getByUserNo(childDTO.getParentNo()));
			}
			courseCommentDTO.setChildren(childDTOList);
		}
		dto.setCourseCommentList(courseCommentDTOList);
		// 课时信息
		for (CourseChapterDTO courseChapterDTO : dto.getChapterList()) {
			List<CourseChapterPeriodAudit> courseChapterPeriodList = courseChapterPeriodAuditDao.listByChapterId(courseChapterDTO.getId());
			courseChapterDTO.setPeriodList(PageUtil.copyList(courseChapterPeriodList, CourseChapterPeriodDTO.class));
		}
		SysVO sys = bossSys.getSys();
		dto.setVideoType(sys.getVideoType());
		return Result.success(dto);
	}

	/**
	 * 获取播放sign值
	 * 
	 * @param authCourseSignBO
	 * @return
	 */
	private AuthCourseSignDTO getSgin(AuthCourseSignBO authCourseSignBO) {
		SysVO sys = bossSys.getSys();
		if (ObjectUtil.isNull(sys)) {
			try {
				throw new Exception("找不到系统配置信息");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		if (StringUtils.isEmpty(sys.getPolyvUseid()) || StringUtils.isEmpty(sys.getPolyvSecretkey())) {
			try {
				throw new Exception("useid或secretkey未配置");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		PolyvSign polyvSign = new PolyvSign();
		polyvSign.setIp(authCourseSignBO.getIp());
		polyvSign.setUserNo(authCourseSignBO.getUserNo());
		polyvSign.setVid(authCourseSignBO.getVideoVid());
		PolyvSignResult signResult = PolyvUtil.getSignForH5(polyvSign, sys.getPolyvUseid(), sys.getPolyvSecretkey());
		AuthCourseSignDTO dto = BeanUtil.copyProperties(signResult, AuthCourseSignDTO.class);
		PolyvCode polyvCode = new PolyvCode();
		polyvCode.setPeriodNo(authCourseSignBO.getPeriodId());
		polyvCode.setUserNo(authCourseSignBO.getUserNo());
		dto.setCode(PolyvUtil.getPolyvCode(polyvCode));
		return dto;
	}

	public Result<AuthCourseSignDTO> playUrl(AuthCourseSignBO authCourseSignBO) {
		SysVO sys = bossSys.getSys();
		if (ObjectUtil.isNull(sys)) {
			try {
				throw new Exception("找不到系统配置信息");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		CourseChapterPeriodAudit courseChapterPeriodAudit = courseChapterPeriodAuditDao.getById(authCourseSignBO.getPeriodId());
		if (ObjectUtil.isNull(courseChapterPeriodAudit)) {
			try {
				throw new Exception("找不到该课时信息");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		AuthCourseSignDTO dto = new AuthCourseSignDTO();
		List<String> urls = new ArrayList<String>();
		String suffix = courseChapterPeriodAudit.getVideoName().substring(courseChapterPeriodAudit.getVideoName().lastIndexOf("."));
		String FHD = sys.getAliyunOssUrl()+CatalogueEnum.VIDEO.name().toLowerCase()+"/"+courseChapterPeriodAudit.getVideoNo()+suffix;//全高清
		String HD = sys.getAliyunOssUrl()+CatalogueEnum.VIDEO.name().toLowerCase()+"/"+CatalogueEnum.TRANSCODE.name().toLowerCase()+"/"+courseChapterPeriodAudit.getVideoNo()+suffix;//高清
		urls.add(HD);
		urls.add(FHD);
		dto.setUrl(urls);
		return Result.success(dto);
	}

	class StudyLog implements Runnable {
		private AuthCourseSignBO authCourseSignBO;
		private CourseChapterPeriodAudit courseChapterPeriodAudit;
		private CourseAudit courseAudit;
		private CourseChapterAudit courseChapterAudit;

		public StudyLog(AuthCourseSignBO authCourseSignBO, CourseChapterPeriodAudit courseChapterPeriodAudit, CourseAudit courseAudit, CourseChapterAudit courseChapterAudit) {
			this.authCourseSignBO = authCourseSignBO;
			this.courseChapterPeriodAudit = courseChapterPeriodAudit;
			this.courseAudit = courseAudit;
			this.courseChapterAudit = courseChapterAudit;
		}

		@Override
		public void run() {
			// 更新学习人数
			updateCount(courseAudit);

			// 学习日志与统计
			studyCount(authCourseSignBO, courseChapterPeriodAudit, courseAudit, courseChapterAudit);
		}

		/**
		 * 更新学习人数
		 */
		private Integer updateCount(CourseAudit courseAudit) {
			CourseAudit record = new CourseAudit();
			record.setId(courseAudit.getId());
			record.setCountStudy(courseAudit.getCountStudy() + 1);
			return courseAuditDao.updateById(record);
		}

		private void studyCount(AuthCourseSignBO authCourseSignBO, CourseChapterPeriodAudit courseChapterPeriodAudit, CourseAudit courseAudit, CourseChapterAudit courseChapterAudit) {
			// 查找课程用户关联表
			CourseUserStudy courseUserStudy = courseUserStudyDao.getByUserNoAndCourseId(authCourseSignBO.getUserNo(), courseChapterPeriodAudit.getCourseId());
			// 如果不存在记录
			if (null == courseUserStudy) {
				courseUserStudy = new CourseUserStudy();
				courseUserStudy.setCourseId(courseAudit.getId());
				courseUserStudy.setUserNo(authCourseSignBO.getUserNo());
				courseUserStudy.setPeriodTotal(courseAudit.getPeriodTotal());
				courseUserStudy.setPeriodStudy(1);
				courseUserStudyDao.save(courseUserStudy);

				CourseUserStudyLog courseUserStudyLog = new CourseUserStudyLog();
				courseUserStudyLog.setPeriodId(courseChapterPeriodAudit.getId());
				courseUserStudyLog.setPeriodName(courseChapterPeriodAudit.getPeriodName());
				courseUserStudyLog.setChapterId(courseChapterPeriodAudit.getChapterId());
				courseUserStudyLog.setChapterName(courseChapterAudit.getChapterName());
				courseUserStudyLog.setCourseId(courseChapterPeriodAudit.getCourseId());
				courseUserStudyLog.setCourseName(courseAudit.getCourseName());
				courseUserStudyLog.setUserNo(authCourseSignBO.getUserNo());
				courseUserStudyLogDao.save(courseUserStudyLog);
			} else {
				CourseUserStudyLog courseUserStudyLog = courseUserStudyLogDao.getByUserNoAndPeriodId(authCourseSignBO.getUserNo(), courseChapterPeriodAudit.getId());
				if (courseUserStudyLog == null) {
					// 记录
					courseUserStudyLog = new CourseUserStudyLog();
					courseUserStudyLog.setPeriodId(courseChapterPeriodAudit.getId());
					courseUserStudyLog.setPeriodName(courseChapterPeriodAudit.getPeriodName());
					courseUserStudyLog.setChapterId(courseChapterPeriodAudit.getChapterId());
					courseUserStudyLog.setChapterName(courseChapterAudit.getChapterName());
					courseUserStudyLog.setCourseId(courseChapterPeriodAudit.getCourseId());
					courseUserStudyLog.setCourseName(courseAudit.getCourseName());
					courseUserStudyLog.setUserNo(authCourseSignBO.getUserNo());
					courseUserStudyLogDao.save(courseUserStudyLog);

					courseUserStudy.setPeriodTotal(courseAudit.getPeriodTotal());
					courseUserStudy.setPeriodStudy(courseUserStudy.getPeriodStudy() + 1);
					courseUserStudyDao.updateById(courseUserStudy);
				} else {
					courseUserStudy.setPeriodTotal(courseAudit.getPeriodTotal());
					courseUserStudy.setPeriodStudy(courseUserStudy.getPeriodStudy() + 1);
					courseUserStudyDao.updateById(courseUserStudy);
				}
			}
		}
	}

}
