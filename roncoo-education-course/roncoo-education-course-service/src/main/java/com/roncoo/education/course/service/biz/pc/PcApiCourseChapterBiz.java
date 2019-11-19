package com.roncoo.education.course.service.biz.pc;

import com.qiniu.common.QiniuException;
import com.roncoo.education.course.service.common.req.*;
import com.roncoo.education.course.service.common.resq.CourseCategoryPageRESQ;
import com.roncoo.education.course.service.common.resq.CourseCategoryViewRESQ;
import com.roncoo.education.course.service.common.resq.CourseChapterAuditPageRESQ;
import com.roncoo.education.course.service.common.resq.CourseChapterPageRESQ;
import com.roncoo.education.course.service.common.req.CourseChapterUpdateREQ;
import com.roncoo.education.course.service.dao.CourseChapterAuditDao;
import com.roncoo.education.course.service.dao.CourseChapterDao;
import com.roncoo.education.course.service.dao.CourseChapterPeriodDao;
import com.roncoo.education.course.service.dao.impl.mapper.entity.*;
import com.roncoo.education.system.common.bean.vo.SysVO;
import com.roncoo.education.system.feign.IBossSys;
import com.roncoo.education.util.aliyun.Aliyun;
import com.roncoo.education.util.aliyun.AliyunUtil;
import com.roncoo.education.util.base.Page;
import com.roncoo.education.util.base.PageUtil;
import com.roncoo.education.util.base.Result;
import com.roncoo.education.util.enums.AuditStatusEnum;
import com.roncoo.education.util.enums.FileTypeEnum;
import com.roncoo.education.util.enums.ResultEnum;
import com.roncoo.education.util.enums.StatusIdEnum;
import com.roncoo.education.util.qiniu.Qiniu;
import com.roncoo.education.util.qiniu.QiniuUtil;
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
public class PcApiCourseChapterBiz {

	@Autowired
	private CourseChapterDao dao;

	@Autowired
	private CourseChapterPeriodDao courseChapterPeriodDao;
	@Autowired
	private IBossSys bossSys;

	/**
	 * 章节分类-分页列出
	 * 
	 * @param req
	 * @return
	 */
	public Result<Page<CourseChapterPageRESQ>> listForPage(CourseChapterPageREQ req) {
		CourseChapterExample example = new CourseChapterExample();
		CourseChapterExample.Criteria c = example.createCriteria();
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
		Page<CourseChapter> page = dao.listForPage(req.getPageCurrent(), req.getPageSize(), example);
		Page<CourseChapterPageRESQ> listForPage = PageUtil.transform(page, CourseChapterPageRESQ.class);
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
		CourseChapter record = BeanUtil.copyProperties(req, CourseChapter.class);
		int results = dao.save(record);
		if (results > 0) {
			return Result.success(results);
		}
		return Result.error(ResultEnum.COURSE_SAVE_FAIL);
	}

	public Result<Integer> delete(CourseChapterDeleteREQ req) {
		if (StringUtils.isEmpty(req.getId())) {
			return Result.error("ID不能为空");
		}
		CourseChapter chapter = dao.getById(req.getId());
		if (ObjectUtil.isNull(chapter)) {
			return Result.error("找不到章节信息");
		}else{
			List<CourseChapterPeriod> list = courseChapterPeriodDao.listByChapterIdAndStatusId(req.getId(), StatusIdEnum.YES.getCode());
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
		CourseChapter record = BeanUtil.copyProperties(req, CourseChapter.class);
		int results = dao.updateById(record);
		if (results > 0) {
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
