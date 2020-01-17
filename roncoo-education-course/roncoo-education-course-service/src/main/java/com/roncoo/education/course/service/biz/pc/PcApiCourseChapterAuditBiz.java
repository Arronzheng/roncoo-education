package com.roncoo.education.course.service.biz.pc;

import com.roncoo.education.course.service.common.req.CourseCategoryViewREQ;
import com.roncoo.education.course.service.common.req.CourseChapterAuditPageREQ;
import com.roncoo.education.course.service.common.req.CourseChapterDeleteREQ;
import com.roncoo.education.course.service.common.req.CourseChapterUpdateREQ;
import com.roncoo.education.course.service.common.resq.CourseCategoryPageRESQ;
import com.roncoo.education.course.service.common.resq.CourseCategoryViewRESQ;
import com.roncoo.education.course.service.common.resq.CourseChapterAuditPageRESQ;
import com.roncoo.education.course.service.dao.CourseAuditDao;
import com.roncoo.education.course.service.dao.CourseChapterAuditDao;
import com.roncoo.education.course.service.dao.CourseChapterPeriodAuditDao;
import com.roncoo.education.course.service.dao.CourseChapterPeriodDao;
import com.roncoo.education.course.service.dao.impl.mapper.entity.*;
import com.roncoo.education.system.feign.IBossSys;
import com.roncoo.education.util.base.Page;
import com.roncoo.education.util.base.PageUtil;
import com.roncoo.education.util.base.Result;
import com.roncoo.education.util.enums.AuditStatusEnum;
import com.roncoo.education.util.enums.ResultEnum;
import com.roncoo.education.util.enums.StatusIdEnum;
import com.roncoo.education.util.tools.BeanUtil;
import com.xiaoleilu.hutool.util.ObjectUtil;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 课程分类
 *
 */
@Component
public class PcApiCourseChapterAuditBiz {

	@Autowired
	private CourseChapterAuditDao dao;

	@Autowired
	private CourseChapterPeriodAuditDao courseChapterPeriodAuditDao;
	@Autowired
	private IBossSys bossSys;
	@Autowired
	private CourseAuditDao courseAuditDao;

	/**
	 * 章节分类-分页列出
	 * 
	 * @param req
	 * @return
	 */
	public Result<Page<CourseChapterAuditPageRESQ>> listForPage(CourseChapterAuditPageREQ req) {
		CourseChapterAuditExample example = new CourseChapterAuditExample();
		CourseChapterAuditExample.Criteria c = example.createCriteria();
		if (req.getStatusId() != null) {
			c.andStatusIdEqualTo(req.getStatusId());
		}
		if (req.getChapterName() != null) {
			c.andChapterNameEqualTo(req.getChapterName());
		}
		if(req.getCourseId() != null){
			c.andCourseIdEqualTo(req.getCourseId());
		} else {
			c.andCourseIdEqualTo(null);
			return Result.error(ResultEnum.COURSE_SAVE_FAIL);
		}
		example.setOrderByClause(" status_id desc, sort desc, id desc ");
		Page<CourseChapterAudit> page = dao.listForPage(req.getPageCurrent(), req.getPageSize(), example);
		Page<CourseChapterAuditPageRESQ> listForPage = PageUtil.transform(page, CourseChapterAuditPageRESQ.class);
		return Result.success(listForPage);
	}

	public Result<Integer> save(CourseChapterUpdateREQ req) {
//		if (req.getParentId() == 0 && req.getFloor() == 1) {
//			req.setFloor(1);
//		} else {
//			CourseCategory parentCategory = dao.getById(req.getParentId());
//			if (ObjectUtil.isNull(parentCategory)) {
//				return Result.error("找不到父分类信息");
//			}
//			req.setFloor(req.getFloor() + 1);
//		}
		CourseChapterAudit record = BeanUtil.copyProperties(req, CourseChapterAudit.class);
		record.setAuditStatus(AuditStatusEnum.WAIT.getCode());
		int results = dao.save(record);
		if (results > 0) {
			//同步更新课程信息
			CourseAudit courseAudit = new CourseAudit();
			courseAudit.setId(record.getCourseId());
			courseAudit.setAuditStatus(AuditStatusEnum.WAIT.getCode());
			courseAuditDao.updateById(courseAudit);
			return Result.success(results);
		}
		return Result.error(ResultEnum.COURSE_SAVE_FAIL);
	}

	public Result<Integer> delete(CourseChapterDeleteREQ req) {
		if (StringUtils.isEmpty(req.getId())) {
			return Result.error("ID不能为空");
		}
		CourseChapterAudit chapterAudit = dao.getById(req.getId());
		if (ObjectUtil.isNull(chapterAudit)) {
			return Result.error("找不到章节信息");
		}else{
			List<CourseChapterPeriodAudit> list = courseChapterPeriodAuditDao.listByChapterIdAndStatusId(req.getId(), StatusIdEnum.YES.getCode());
			if (CollectionUtils.isNotEmpty(list)) {
				return Result.error("请先删除下级分类");
			}
		}
		int results = dao.deleteById(req.getId());
		if (results > 0) {
			return Result.success(results);
		}
		return Result.error(ResultEnum.COURSE_DELETE_FAIL);
	}

	public Result<Integer> update(CourseChapterUpdateREQ req) {
		if (StringUtils.isEmpty(req.getId())) {
			return Result.error("ID不能为空");
		}
		if (StringUtils.isEmpty(req.getChapterName())) {
			return Result.error("章节名称不能为空");
		}
		CourseChapterAudit record = BeanUtil.copyProperties(req, CourseChapterAudit.class);
		int results = dao.updateById(record);
		if (results > 0) {
			//同步更新课程信息
			CourseAudit courseAudit = new CourseAudit();
			courseAudit.setId(record.getCourseId());
			courseAudit.setAuditStatus(AuditStatusEnum.WAIT.getCode());
			courseAuditDao.updateById(courseAudit);
			return Result.success(results);
		}
		return Result.error(ResultEnum.COURSE_DELETE_FAIL);
	}

	public Result<CourseCategoryViewRESQ> view(CourseCategoryViewREQ req) {
//		if (StringUtils.isEmpty(req.getId())) {
//			return Result.error("ID不能为空");
//		}
//		CourseCategory parentCategory = dao.getById(req.getId());
//		if (ObjectUtil.isNull(parentCategory)) {
//			return Result.error("找不到父分类信息");
//		}
//		return Result.success(BeanUtil.copyProperties(parentCategory, CourseCategoryViewRESQ.class));
		return Result.error(ResultEnum.COURSE_DELETE_FAIL);
	}

	/**
	 * 递归展示分类
	 * 
	 * @author WY
	 */
	private List<CourseCategoryPageRESQ> recursionList(Long parentId) {
		List<CourseCategoryPageRESQ> list = new ArrayList<>();
//		List<CourseCategory> CourseCategoryList = dao.listByParentId(parentId);
//		if (CollectionUtils.isNotEmpty(CourseCategoryList)) {
//			for (CourseCategory courseCategory : CourseCategoryList) {
//				CourseCategoryPageRESQ resq = BeanUtil.copyProperties(courseCategory, CourseCategoryPageRESQ.class);
//				resq.setChildren(recursionList(courseCategory.getId()));
//				list.add(resq);
//			}
//		}
		return list;
	}
}
