package com.roncoo.education.course.service.biz.pc;

import java.math.BigDecimal;
import java.util.List;

import com.qiniu.common.QiniuException;
import com.roncoo.education.course.service.common.dto.auth.AuthCourseAuditSaveDTO;
import com.roncoo.education.course.service.common.req.*;
import com.roncoo.education.course.service.dao.impl.mapper.entity.*;
import com.roncoo.education.system.common.bean.vo.SysVO;
import com.roncoo.education.system.feign.IBossSys;
import com.roncoo.education.util.aliyun.Aliyun;
import com.roncoo.education.util.aliyun.AliyunUtil;
import com.roncoo.education.util.enums.*;
import com.roncoo.education.util.qiniu.Qiniu;
import com.roncoo.education.util.qiniu.QiniuUtil;
import com.roncoo.education.util.tencentcloud.Tencent;
import com.roncoo.education.util.tencentcloud.TencentUtil;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.roncoo.education.course.service.common.resq.CourseChapterPeriodViewRESQ;
import com.roncoo.education.course.service.common.resq.CourseChapterViewRESQ;
import com.roncoo.education.course.service.common.resq.CourseGetRESQ;
import com.roncoo.education.course.service.common.resq.CoursePageRESQ;
import com.roncoo.education.course.service.common.resq.CourseViewRESQ;
import com.roncoo.education.course.service.dao.CourseAuditDao;
import com.roncoo.education.course.service.dao.CourseCategoryDao;
import com.roncoo.education.course.service.dao.CourseChapterDao;
import com.roncoo.education.course.service.dao.CourseChapterPeriodDao;
import com.roncoo.education.course.service.dao.CourseDao;
import com.roncoo.education.course.service.dao.CourseIntroduceAuditDao;
import com.roncoo.education.course.service.dao.CourseIntroduceDao;
import com.roncoo.education.course.service.dao.ZoneCourseDao;
import com.roncoo.education.course.service.dao.impl.mapper.entity.CourseExample.Criteria;
import com.roncoo.education.user.common.bean.vo.LecturerVO;
import com.roncoo.education.user.feign.IBossLecturer;
import com.roncoo.education.util.base.Page;
import com.roncoo.education.util.base.PageUtil;
import com.roncoo.education.util.base.Result;
import com.roncoo.education.util.tools.BeanUtil;
import com.xiaoleilu.hutool.util.ObjectUtil;

/**
 * 课程信息
 */
@Component
public class PcApiCourseBiz {

	@Autowired
	private IBossLecturer bossLecturer;

	@Autowired
	private ZoneCourseDao zoneCourseDao;
	@Autowired
	private CourseDao dao;
	@Autowired
	private CourseAuditDao courseAuditDao;
	@Autowired
	private CourseChapterDao courseChapterDao;
	@Autowired
	private CourseIntroduceDao courseIntroduceDao;
	@Autowired
	private CourseIntroduceAuditDao courseIntroduceAuditDao;
	@Autowired
	private CourseCategoryDao courseCategoryDao;
	@Autowired
	private CourseChapterPeriodDao courseChapterPeriodDao;
	@Autowired
	private IBossSys bossSys;

	/**
	 * 分页列出
	 * 
	 * @param req
	 * @return
	 */
	public Result<Page<CoursePageRESQ>> list(CoursePageREQ req) {
		CourseExample example = new CourseExample();
		Criteria c = example.createCriteria();
		if (req.getCategoryId1() != null) {
			c.andCategoryId1EqualTo(req.getCategoryId1());
		}
		if (!StringUtils.isEmpty(req.getCourseName())) {
			c.andCourseNameLike(PageUtil.rightLike(req.getCourseName()));
		}
		if (req.getStatusId() != null) {
			c.andStatusIdEqualTo(req.getStatusId());
		}
		if (req.getIsFree() != null) {
			c.andIsFreeEqualTo(req.getIsFree());
		}
		if (req.getIsPutaway() != null) {
			c.andIsPutawayEqualTo(req.getIsPutaway());
		}
		example.setOrderByClause(" status_id desc, is_putaway desc, course_sort desc, id desc ");
		Page<Course> page = dao.listForPage(req.getPageCurrent(), req.getPageSize(), example);
		Page<CoursePageRESQ> listForPage = PageUtil.transform(page, CoursePageRESQ.class);
		// 获取分类名称
		for (CoursePageRESQ resq : listForPage.getList()) {
			if (req.getZoneId() != null) {
				// 校验专区是否存在课程
				ZoneCourse zoneCourse = zoneCourseDao.getZoneIdAndCourseId(resq.getId(), req.getZoneId());
				if (ObjectUtil.isNull(zoneCourse)) {
					// 不存在
					resq.setIsAddZoneCourse(0);
				} else {
					// 存在
					resq.setIsAddZoneCourse(1);
				}
			}
			LecturerVO lecturer = bossLecturer.getByLecturerUserNo(resq.getLecturerUserNo());
			if (ObjectUtil.isNotNull(lecturer)) {
				resq.setLecturerName(lecturer.getLecturerName());
			}
			if (resq.getCategoryId1() != null && resq.getCategoryId1() != 0) {
				CourseCategory courseCategory = courseCategoryDao.getById(resq.getCategoryId1());
				if (!StringUtils.isEmpty(courseCategory)) {
					resq.setCategoryName1(courseCategory.getCategoryName());
				}
			}
			if (resq.getCategoryId2() != null && resq.getCategoryId2() != 0) {
				CourseCategory courseCategory = courseCategoryDao.getById(resq.getCategoryId2());
				if (!StringUtils.isEmpty(courseCategory)) {
					resq.setCategoryName2(courseCategory.getCategoryName());
				}
			}
			if (resq.getCategoryId3() != null && resq.getCategoryId3() != 0) {
				CourseCategory courseCategory = courseCategoryDao.getById(resq.getCategoryId3());
				if (!StringUtils.isEmpty(courseCategory)) {
					resq.setCategoryName3(courseCategory.getCategoryName());
				}
			}
		}
		return Result.success(listForPage);
	}

	@Transactional
	public Result<Integer> update(CourseUpdateREQ req) {
		if (req.getId() == null) {
			return Result.error("ID不能为空");
		}
		Course course = dao.getById(req.getId());
		if (ObjectUtil.isNull(course)) {
			return Result.error("找不到课程信息");
		}
		if (IsFreeEnum.FREE.getCode().equals(req.getIsFree())) {
			req.setCourseOriginal(BigDecimal.ZERO);
			req.setCourseDiscount(BigDecimal.ZERO);
		}
		Course record = BeanUtil.copyProperties(req, Course.class);
		int result = dao.updateById(record);
		if (result > 0) {
			// 同步更新审核表
			CourseAudit courseAudit = BeanUtil.copyProperties(req, CourseAudit.class);
			courseAudit.setGmtCreate(null);
			courseAudit.setGmtModified(null);
			int recordAudit = courseAuditDao.updateById(courseAudit);
			if (recordAudit < 0) {
				return Result.error(ResultEnum.COURSE_UPDATE_FAIL);
			}
		}

		if (StringUtils.hasText(req.getIntroduce())) {
			// 更新课程介绍
			CourseIntroduce courseIntroduce = courseIntroduceDao.getById(course.getIntroduceId());
			if (ObjectUtil.isNull(courseIntroduce)) {
				return Result.error("找不到课程简介信息");
			}
			courseIntroduce.setId(course.getIntroduceId());
			courseIntroduce.setIntroduce(req.getIntroduce());
			int results = courseIntroduceDao.updateById(courseIntroduce);
			if (results < 0) {
				return Result.error(ResultEnum.COURSE_UPDATE_FAIL);
			}
			// 同步更新审核表
			CourseIntroduceAudit courseIntroduceAudit = courseIntroduceAuditDao.getById(course.getIntroduceId());
			if (ObjectUtil.isNull(courseIntroduceAudit)) {
				return Result.error("找不到课程简介信息");
			}
			courseIntroduceAudit.setGmtCreate(null);
			courseIntroduceAudit.setGmtModified(null);
			courseIntroduceAudit.setId(course.getIntroduceId());
			courseIntroduceAudit.setIntroduce(req.getIntroduce());
			courseIntroduceAuditDao.updateById(courseIntroduceAudit);
		}
		return Result.success(result);
	}

	/**
	 * 查看(课程修改使用)
	 * 
	 * @param req
	 * @return
	 */
	public Result<CourseGetRESQ> get(CourseGetREQ req) {
		if (req.getId() == null) {
			return Result.error("ID不能为空");
		}
		// 根据id查找课程信息
		Course record = dao.getById(req.getId());
		CourseGetRESQ resq = BeanUtil.copyProperties(record, CourseGetRESQ.class);
		if (ObjectUtil.isNotNull(resq)) {
			// 获取分类名称
			if (resq.getCategoryId1() != null && resq.getCategoryId1() != 0) {
				CourseCategory courseCategory = courseCategoryDao.getById(resq.getCategoryId1());
				resq.setCategoryName1(courseCategory.getCategoryName());
			}
			if (resq.getCategoryId2() != null && resq.getCategoryId2() != 0) {
				CourseCategory courseCategory = courseCategoryDao.getById(resq.getCategoryId2());
				resq.setCategoryName2(courseCategory.getCategoryName());
			}
			if (resq.getCategoryId3() != null && resq.getCategoryId3() != 0) {
				CourseCategory courseCategory = courseCategoryDao.getById(resq.getCategoryId3());
				resq.setCategoryName3(courseCategory.getCategoryName());
			}
			// 根据id查找课程简介信息
			CourseIntroduce courseIntroduce = courseIntroduceDao.getById(record.getIntroduceId());
			// 把课程简介带回课程信息
			if (ObjectUtil.isNotNull(courseIntroduce)) {
				resq.setIntroduce(courseIntroduce.getIntroduce());
			}
		}
		return Result.success(resq);
	}

	/**
	 * 查看详情
	 * 
	 * @param req
	 * @return
	 */
	public Result<CourseViewRESQ> view(CourseViewREQ req) {
		if (req.getId() == null) {
			return Result.error("ID不能为空");
		}
		Course record = dao.getById(req.getId());
		CourseViewRESQ resq = BeanUtil.copyProperties(record, CourseViewRESQ.class);
		// 获取分类名称
		if (resq.getCategoryId1() != null && resq.getCategoryId1() != 0) {
			CourseCategory courseCategory = courseCategoryDao.getById(resq.getCategoryId1());
			resq.setCategoryName1(courseCategory.getCategoryName());
		}
		if (resq.getCategoryId2() != null && resq.getCategoryId2() != 0) {
			CourseCategory courseCategory = courseCategoryDao.getById(resq.getCategoryId2());
			resq.setCategoryName2(courseCategory.getCategoryName());
		}
		if (resq.getCategoryId3() != null && resq.getCategoryId3() != 0) {
			CourseCategory courseCategory = courseCategoryDao.getById(resq.getCategoryId3());
			resq.setCategoryName3(courseCategory.getCategoryName());
		}
		// 章节
		List<CourseChapter> ChapterList = courseChapterDao.listByCourseIdAndStatusId(resq.getId(),
				StatusIdEnum.YES.getCode());
		if (CollectionUtils.isNotEmpty(ChapterList)) {
			List<CourseChapterViewRESQ> courseChapterVOList = PageUtil.copyList(ChapterList,
					CourseChapterViewRESQ.class);
			for (CourseChapterViewRESQ courseChapter : courseChapterVOList) {
				// 课时
				List<CourseChapterPeriod> periodList = courseChapterPeriodDao
						.listByChapterIdAndStatusId(courseChapter.getId(), StatusIdEnum.YES.getCode());
				courseChapter
						.setCourseChapterPeriodList(PageUtil.copyList(periodList, CourseChapterPeriodViewRESQ.class));
			}
			resq.setCourseChapterList(courseChapterVOList);
		}
		return Result.success(resq);
	}

	public Result<Integer> save(CourseSaveREQ courseSaveREQ) {
//		Course record = BeanUtil.copyProperties(courseSaveREQ,Course.class);
//		for (int i = 0; i < courseSaveREQ.getCategorys().size(); i++) {
//			record.transferSet(i,Long.valueOf(courseSaveREQ.getCategorys().get(i)));
//		}
//		record.setStatusId(1);
//		if(record.getIsFree() == 1){
//			record.setCourseOriginal(BigDecimal.ZERO);
//		}
//		record.setIsPutaway(1);

		// 原价小于0
		if (courseSaveREQ.getCourseOriginal().compareTo(BigDecimal.valueOf(0)) == -1) {
			return Result.error("售价不能小于0");
		}
		// 课程收费但价格为空
		if (IsFreeEnum.CHARGE.getCode().equals(courseSaveREQ.getIsFree())) {
			if (courseSaveREQ.getCourseOriginal() == null) {
				return Result.error("价格不能为空");
			}
		}

		// 课程介绍
		CourseIntroduceAudit courseIntroduceAudit = new CourseIntroduceAudit();
		courseIntroduceAudit.setIntroduce(courseSaveREQ.getCourseDesc());
		courseIntroduceAuditDao.save(courseIntroduceAudit);

		// 课程
		CourseAudit record = BeanUtil.copyProperties(courseSaveREQ, CourseAudit.class);
		if (IsFreeEnum.FREE.getCode().equals(courseSaveREQ.getIsFree())) {
			// 课程免费就设置价格为0(原价、优惠价)
			record.setCourseOriginal(BigDecimal.valueOf(0));
			record.setCourseDiscount(BigDecimal.valueOf(0));
		}
		record.setStatusId(StatusIdEnum.YES.getCode());
		record.setIsPutaway(IsPutawayEnum.YES.getCode());
		record.setAuditStatus(AuditStatusEnum.WAIT.getCode());
		record.setIntroduceId(courseIntroduceAudit.getId());
		record.setCourseDiscount(courseSaveREQ.getCourseOriginal());
		record.setLecturerUserNo(courseSaveREQ.getLecturerUserNo());
		for (int i = 0; i < courseSaveREQ.getCategorys().size(); i++) {
			record.transferSet(i,Long.valueOf(courseSaveREQ.getCategorys().get(i)));
		}
		// 查询更新后的课程审核信息
		int i = courseAuditDao.save(record);
		if (i > 0) {
			return Result.success(i);
		}
		return Result.error(ResultEnum.COURSE_SAVE_FAIL);
	}

    public Result<Integer> delete(CourseDeleteREQ req) {
		if (StringUtils.isEmpty(req.getId())) {
			return Result.error("ID不能为空");
		}
		Course course = dao.getById(req.getId());
		if (ObjectUtil.isNull(course)) {
			return Result.error("找不到课程信息");
		}else{
			List<CourseChapter> ChapterList = courseChapterDao.listByCourseIdAndStatusId(req.getId(),
					StatusIdEnum.YES.getCode());
			if (CollectionUtils.isNotEmpty(ChapterList)) {
				return Result.error("请先删除下级分类");
			}
		}

		SysVO sys = bossSys.getSys();
		if(sys.getFileType().equals(FileTypeEnum.TENCENT)){
			TencentUtil.deleteFile(course.getCourseLogo(), BeanUtil.copyProperties(sys, Tencent.class));
		}else if(sys.getFileType().equals(FileTypeEnum.QINIU)){
			try {
				QiniuUtil.deletePic(course.getCourseLogo(), BeanUtil.copyProperties(sys, Qiniu.class));
			} catch (QiniuException e) {
				return Result.error(e.code(),e.response.toString());
			}
		}else{
			AliyunUtil.delete(course.getCourseLogo(), BeanUtil.copyProperties(sys, Aliyun.class));
		}
		int results = dao.deleteById(req.getId());
		if (results > 0) {
			return Result.success(results);
		}
		return Result.error(ResultEnum.COURSE_DELETE_FAIL);
    }
}
