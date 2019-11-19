package com.roncoo.education.course.service.biz.pc;

import com.roncoo.education.course.service.common.req.*;
import com.roncoo.education.course.service.common.resq.ChapterPeriodAuditPageRESQ;
import com.roncoo.education.course.service.common.resq.ChapterPeriodPageRESQ;
import com.roncoo.education.course.service.common.resq.CourseCategoryPageRESQ;
import com.roncoo.education.course.service.dao.CourseChapterPeriodAuditDao;
import com.roncoo.education.course.service.dao.CourseChapterPeriodDao;
import com.roncoo.education.course.service.dao.CourseVideoDao;
import com.roncoo.education.course.service.dao.impl.mapper.entity.*;
import com.roncoo.education.system.common.bean.vo.SysVO;
import com.roncoo.education.system.feign.IBossSys;
import com.roncoo.education.util.base.Page;
import com.roncoo.education.util.base.PageUtil;
import com.roncoo.education.util.base.Result;
import com.roncoo.education.util.enums.AuditStatusEnum;
import com.roncoo.education.util.enums.ResultEnum;
import com.roncoo.education.util.tools.BeanUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 课程分类
 *
 */
@Component
public class PcApiChapterPeriodBiz {

	@Autowired
	private CourseChapterPeriodDao dao;
	@Autowired
	private IBossSys bossSys;
	@Autowired
	private CourseVideoDao courseVideoDao;

//	@Autowired
//	private CourseChapterPeriodDao courseChapterPeriodDao;

	/**
	 * 课时分类-分页列出
	 * 
	 * @param req
	 * @return
	 */
	public Result<Page<ChapterPeriodPageRESQ>> listForPage(ChapterPeriodPageREQ req) {
		CourseChapterPeriodExample example = new CourseChapterPeriodExample();
		CourseChapterPeriodExample.Criteria c = example.createCriteria();
		if (req.getStatusId() != null) {
			c.andStatusIdEqualTo(req.getStatusId());
		}
		if (req.getPeriodName() != null) {
			c.andPeriodNameEqualTo(req.getPeriodName());
		}
		if(req.getChapterId() != null){
			c.andChapterIdEqualTo(req.getChapterId());
		} else {
			c.andChapterIdEqualTo(null);
			return Result.error(ResultEnum.COURSE_SAVE_FAIL);
		}
		example.setOrderByClause(" status_id desc, sort desc, id desc ");
		Page<CourseChapterPeriod> page = dao.listForPage(req.getPageCurrent(), req.getPageSize(), example);
		Page<ChapterPeriodPageRESQ> listForPage = PageUtil.transform(page, ChapterPeriodPageRESQ.class);
		return Result.success(listForPage);
	}

	public Result<Integer> save(ChapterPeriodSaveREQ req) {
//		if (req.getParentId() == 0 && req.getFloor() == 1) {
//			req.setFloor(1);
//		} else {
//			CourseCategory parentCategory = dao.getById(req.getParentId());
//			if (ObjectUtil.isNull(parentCategory)) {
//				return Result.error("找不到父分类信息");
//			}
//			req.setFloor(req.getFloor() + 1);
//		}
		if(StringUtils.isEmpty(req.getVideoNo())){
			return Result.error(ResultEnum.COURSE_SAVE_FAIL);
		}
		SysVO sys = bossSys.getSys();
		String videoNo = req.getVideoNo().replaceAll(sys.getAliyunOssUrl(), "");
		CourseVideo courseVideo = courseVideoDao.getByVideoNo(Long.valueOf(videoNo));

		CourseChapterPeriod record = BeanUtil.copyProperties(req, CourseChapterPeriod.class);
		if(!ObjectUtils.isEmpty(courseVideo)){
			record.setVideoLength(courseVideo.getVideoLength());
			record.setVideoNo(Long.valueOf(videoNo));
		}
		int results = dao.save(record);
		if (results > 0) {
			return Result.success(results);
		}
		return Result.error(ResultEnum.COURSE_SAVE_FAIL);
	}

	public Result<Integer> delete(ChapterPeriodAuditDeleteREQ req) {
		if (StringUtils.isEmpty(req.getId())) {
			return Result.error("ID不能为空");
		}
//		List<CourseChapterPeriod> list = courseChapterPeriodDao.listByChapterId(req.getId());
//		if (CollectionUtils.isNotEmpty(list)) {
//			return Result.error("请先删除下级分类");
//		}
		int results = dao.deleteById(req.getId());
		if (results > 0) {
			return Result.success(results);
		}
		return Result.error(ResultEnum.COURSE_DELETE_FAIL);
	}

	public Result<Integer> update(ChapterPeriodUpdateREQ req) {
		if (StringUtils.isEmpty(req.getId())) {
			return Result.error("ID不能为空");
		}
		CourseChapterPeriod record = BeanUtil.copyProperties(req, CourseChapterPeriod.class);
		int results = dao.updateById(record);
		if (results > 0) {
			return Result.success(results);
		}
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
