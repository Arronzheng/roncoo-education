package com.roncoo.education.course.service.biz.pc;

import java.util.List;

import com.roncoo.education.course.service.dao.*;
import com.roncoo.education.course.service.dao.impl.mapper.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.roncoo.education.course.service.common.req.ZoneCourseDeleteREQ;
import com.roncoo.education.course.service.common.req.ZoneCoursePageREQ;
import com.roncoo.education.course.service.common.req.ZoneCourseSaveREQ;
import com.roncoo.education.course.service.common.req.ZoneCourseUpdateREQ;
import com.roncoo.education.course.service.common.req.ZoneCourseViewREQ;
import com.roncoo.education.course.service.common.resq.ZoneCoursePageRESQ;
import com.roncoo.education.course.service.common.resq.ZoneCourseViewRESQ;
import com.roncoo.education.course.service.dao.impl.mapper.entity.ZoneCourseExample.Criteria;
import com.roncoo.education.util.base.Page;
import com.roncoo.education.util.base.PageUtil;
import com.roncoo.education.util.base.Result;
import com.roncoo.education.util.enums.ResultEnum;
import com.roncoo.education.util.enums.StatusIdEnum;
import com.roncoo.education.util.tools.BeanUtil;
import com.xiaoleilu.hutool.util.ObjectUtil;

/**
 *
 * 专区课程关联表
 *
 */
@Component
public class PcApiZoneCourseBiz {
	@Autowired
	private ZoneDao zoneDao;
	@Autowired
	private ZoneCourseDao dao;
	@Autowired
	private CourseCategoryDao courseCategoryDao;
	@Autowired
	private CourseAuditDao courseAuditDao;

	/**
	 * 分页列出
	 *
	 * @param req
	 * @return
	 */
	public Result<Page<ZoneCoursePageRESQ>> listForPage(ZoneCoursePageREQ req) {
		if (StringUtils.isEmpty(req.getZoneId())) {
			return Result.error("专区ID不能为空");
		}
		ZoneCourseExample example = new ZoneCourseExample();
		Criteria c = example.createCriteria();
		c.andZoneIdEqualTo(req.getZoneId());
		if (!StringUtils.isEmpty(req.getCourseName())) {
			CourseAudit courseAudit = courseAuditDao.getByCourseName(req.getCourseName());
			if (ObjectUtil.isNull(courseAudit)) {
				c.andCourseIdEqualTo(0L);
			} else {
				c.andCourseIdEqualTo(courseAudit.getId());
			}
		}
		example.setOrderByClause("status_id desc, sort desc, id desc");
		Page<ZoneCourse> listForPage = dao.listForPage(req.getPageCurrent(), req.getPageSize(), example);
		Page<ZoneCoursePageRESQ> page = PageUtil.transform(listForPage, ZoneCoursePageRESQ.class);
		// 根据课程ID获取课程信息
		for (ZoneCoursePageRESQ resq : page.getList()) {
			List<CourseAudit> courseAudit = courseAuditDao.listByCourseId(resq.getCourseId());
			for (CourseAudit courseAuditInfo : courseAudit) {
				// 获取课程所属分类
				if (courseAuditInfo.getCategoryId1() != null && courseAuditInfo.getCategoryId1() != 0) {
					CourseCategory courseCategory = courseCategoryDao.getById(courseAuditInfo.getCategoryId1());
					if (courseCategory != null) {
						resq.setCategoryName1(courseCategory.getCategoryName());
					}
				}
				if (courseAuditInfo.getCategoryId2() != null && courseAuditInfo.getCategoryId2() != 0) {
					CourseCategory courseCategory = courseCategoryDao.getById(courseAuditInfo.getCategoryId2());
					if (courseCategory != null) {
						resq.setCategoryName2(courseCategory.getCategoryName());
					}
				}
				if (courseAuditInfo.getCategoryId3() != null && courseAuditInfo.getCategoryId3() != 0) {
					CourseCategory courseCategory = courseCategoryDao.getById(courseAuditInfo.getCategoryId3());
					if (courseCategory != null) {
						resq.setCategoryName3(courseCategory.getCategoryName());
					}
				}
				resq.setCourseName(courseAuditInfo.getCourseName());
			}

		}
		return Result.success(page);
	}

	/**
	 * 专区课程保存
	 *
	 * @param req
	 * @return
	 */
	public Result<Integer> save(ZoneCourseSaveREQ req) {
		if (ObjectUtil.isNull(req.getCourseId())) {
			return Result.error("课程ID不能为空");
		}
		if (ObjectUtil.isNull(req.getZoneId())) {
			return Result.error("专区ID不能为空");
		}

		Zone zone = zoneDao.getById(req.getZoneId());
		if (ObjectUtil.isNull(zone) && !StatusIdEnum.YES.getCode().equals(zone.getStatusId())) {
			return Result.error("找不到专区信息");
		}

		// 根据课程编号获取课程信息
		CourseAudit courseAudit = courseAuditDao.getByCourseIdAndStatusId(req.getCourseId(), StatusIdEnum.YES.getCode());
		if (ObjectUtil.isNull(courseAudit) && !StatusIdEnum.YES.getCode().equals(courseAudit.getStatusId())) {
			return Result.error("找不到课程信息");
		}

		ZoneCourse zoneCourse = dao.getZoneIdAndCourseId(req.getCourseId(), req.getZoneId());
		if (ObjectUtil.isNotNull(zoneCourse)) {
			return Result.error("已存在专区课程");
		}

		// 保存分区关联课程信息
		ZoneCourse result = new ZoneCourse();
		result.setCourseId(courseAudit.getId());
		result.setZoneId(req.getZoneId());
		result.setSort(1);
		int results = dao.save(result);
		if (results > 0) {
			return Result.success(results);
		}
		return Result.error(ResultEnum.COURSE_SAVE_FAIL);
	}

	/**
	 * 专区课程删除
	 *
	 * @param zoneCourseDeleteREQ
	 * @return
	 */
	public Result<Integer> delete(ZoneCourseDeleteREQ zoneCourseDeleteREQ) {
		if (StringUtils.isEmpty(zoneCourseDeleteREQ.getId())) {
			return Result.error("ID不能为空");
		}
		int results = dao.deleteById(zoneCourseDeleteREQ.getId());
		if (results > 0) {
			return Result.success(results);
		}
		return Result.error(ResultEnum.COURSE_DELETE_FAIL);
	}

	/**
	 * 专区课程更新
	 *
	 * @param zoneCourseUpdateREQ
	 * @return
	 */
	public Result<Integer> update(ZoneCourseUpdateREQ zoneCourseUpdateREQ) {
		if (StringUtils.isEmpty(zoneCourseUpdateREQ.getId())) {
			return Result.error("ID不能为空");
		}
		ZoneCourse record = BeanUtil.copyProperties(zoneCourseUpdateREQ, ZoneCourse.class);
		int results = dao.updateById(record);
		if (results > 0) {
			return Result.success(results);
		}
		return Result.error(ResultEnum.COURSE_DELETE_FAIL);
	}

	/**
	 * 专区课程查看
	 *
	 * @param zoneCourseViewREQ
	 * @return
	 */
	public Result<ZoneCourseViewRESQ> view(ZoneCourseViewREQ zoneCourseViewREQ) {
		if (StringUtils.isEmpty(zoneCourseViewREQ.getId())) {
			return Result.error("ID不能为空");
		}
		ZoneCourse record = dao.getById(zoneCourseViewREQ.getId());
		if (ObjectUtil.isNull(record)) {
			return Result.error("找不到专区信息");
		}
		return Result.success(BeanUtil.copyProperties(record, ZoneCourseViewRESQ.class));
	}

}
