package com.roncoo.education.course.service.biz.pc;

import com.roncoo.education.course.service.common.req.ChapterPeriodAuditDeleteREQ;
import com.roncoo.education.course.service.common.req.ChapterPeriodAuditPageREQ;
import com.roncoo.education.course.service.common.req.ChapterPeriodAuditSaveREQ;
import com.roncoo.education.course.service.common.req.ChapterPeriodAuditUpdateREQ;
import com.roncoo.education.course.service.common.resq.ChapterPeriodAuditPageRESQ;
import com.roncoo.education.course.service.common.resq.CourseCategoryPageRESQ;
import com.roncoo.education.course.service.dao.CourseAuditDao;
import com.roncoo.education.course.service.dao.CourseChapterAuditDao;
import com.roncoo.education.course.service.dao.CourseChapterPeriodAuditDao;
import com.roncoo.education.course.service.dao.CourseVideoDao;
import com.roncoo.education.course.service.dao.impl.mapper.entity.*;
import com.roncoo.education.system.common.bean.vo.SysVO;
import com.roncoo.education.system.feign.IBossSys;
import com.roncoo.education.util.base.Page;
import com.roncoo.education.util.base.PageUtil;
import com.roncoo.education.util.base.Result;
import com.roncoo.education.util.enums.AuditStatusEnum;
import com.roncoo.education.util.enums.CatalogueEnum;
import com.roncoo.education.util.enums.ResultEnum;
import com.roncoo.education.util.enums.VideoTypeEnum;
import com.roncoo.education.util.tencentcloud.Tencent;
import com.roncoo.education.util.tencentcloud.TencentUtil;
import com.roncoo.education.util.tools.BeanUtil;
import com.roncoo.education.util.tools.StrUtil;
import com.roncoo.education.util.tools.UrlUtil;
import org.apache.commons.collections.CollectionUtils;
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
public class PcApiChapterPeriodAuditBiz {

	@Autowired
	private CourseChapterPeriodAuditDao dao;
	@Autowired
	private IBossSys bossSys;
	@Autowired
	private CourseVideoDao courseVideoDao;
	@Autowired
	private CourseAuditDao courseAuditDao;
	@Autowired
	private CourseChapterAuditDao courseChapterAuditDao;

//	@Autowired
//	private CourseChapterPeriodDao courseChapterPeriodDao;

	/**
	 * 课时分类-分页列出
	 * 
	 * @param req
	 * @return
	 */
	public Result<Page<ChapterPeriodAuditPageRESQ>> listForPage(ChapterPeriodAuditPageREQ req) {
		CourseChapterPeriodAuditExample example = new CourseChapterPeriodAuditExample();
		CourseChapterPeriodAuditExample.Criteria c = example.createCriteria();
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
			return Result.error("章节Id过时，请重新打开页面！");
		}
		example.setOrderByClause(" status_id desc, sort desc, id desc ");
		Page<CourseChapterPeriodAudit> page = dao.listForPage(req.getPageCurrent(), req.getPageSize(), example);
		Page<ChapterPeriodAuditPageRESQ> listForPage = PageUtil.transform(page, ChapterPeriodAuditPageRESQ.class);
		SysVO sys = bossSys.getSys();
		//数据库没有videoURL字段，只好这样解决了╮(╯﹏╰）╭
		for (ChapterPeriodAuditPageRESQ chapterPeriodAuditPageRESQ : listForPage.getList()) {
			chapterPeriodAuditPageRESQ.setVideoUrl(sys.getAliyunOssUrl()+chapterPeriodAuditPageRESQ.getVideoNo()+"."+ StrUtil.getSuffix(chapterPeriodAuditPageRESQ.getVideoName()));
		}
		return Result.success(listForPage);
	}

	public Result<Integer> save(ChapterPeriodAuditSaveREQ req) {
//		if (req.getParentId() == 0 && req.getFloor() == 1) {
//			req.setFloor(1);
//		} else {
//			CourseCategory parentCategory = dao.getById(req.getParentId());
//			if (ObjectUtil.isNull(parentCategory)) {
//				return Result.error("找不到父分类信息");
//			}
//			req.setFloor(req.getFloor() + 1);
//		}
		CourseChapterPeriodAudit record = BeanUtil.copyProperties(req, CourseChapterPeriodAudit.class);
		record.setIsDoc(0);
		CourseVideo courseVideo = new CourseVideo();
		//补充视频相关的子段
		if(!StringUtils.isEmpty(req.getVideoNo())){
//			return Result.error(ResultEnum.COURSE_SAVE_FAIL);
			SysVO sys = bossSys.getSys();
			Long videoNo = UrlUtil.UrlToKey(req.getVideoNo(), sys.getAliyunOssUrl());
			courseVideo = courseVideoDao.getByVideoNo(Long.valueOf(videoNo));

			if(!ObjectUtils.isEmpty(courseVideo)){
				record.setVideoLength(courseVideo.getVideoLength());
				record.setVideoNo(Long.valueOf(videoNo));
				record.setIsVideo("1");
				record.setVideoName(req.getVideoName());
			}
		}
		//补充文档相关的子段
		if(!StringUtils.isEmpty(req.getDocUrl())){
			record.setIsDoc(1);
			record.setDocName(req.getDocName());
			record.setDocUrl(req.getDocUrl());
		}
		record.setAuditStatus(AuditStatusEnum.WAIT.getCode());
		int results = dao.save(record);
		if (results > 0) {
			//同步修改课程的审核状态
			CourseAudit courseAudit = new CourseAudit();
			courseAudit.setId(record.getCourseId());
			courseAudit.setAuditStatus(AuditStatusEnum.WAIT.getCode());
			courseAuditDao.updateById(courseAudit);
			//同步修改章节的审核状态
			CourseChapterAudit courseChapterAudit = new CourseChapterAudit();
			courseChapterAudit.setId(record.getChapterId());
			courseChapterAudit.setAuditStatus(AuditStatusEnum.WAIT.getCode());
			courseChapterAuditDao.updateById(courseChapterAudit);
			//同步修改课程视频信息
			if(!ObjectUtils.isEmpty(courseVideo)){
				courseVideo.setCourseId(record.getCourseId());
				courseVideo.setChapterId(record.getChapterId());
				courseVideo.setPeriodId(record.getId());
				courseVideoDao.updateById(courseVideo);
			}
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
			SysVO sys = bossSys.getSys();
			List<CourseVideo> courseVideos = courseVideoDao.getByPeriodId(req.getId());
			if(CollectionUtils.isNotEmpty(courseVideos)){
				for (CourseVideo courseVideo : courseVideos) {
					String key = CatalogueEnum.VIDEO.name().toLowerCase()+"/"+courseVideo.getVideoNo()+courseVideo.getVideoName().substring(courseVideo.getVideoName().lastIndexOf("."));
					//同步删除视频
					if(sys.getVideoType().equals(VideoTypeEnum.TENCENT.getCode())){
						TencentUtil.deleteFile((sys.getAliyunOssUrl()+key),BeanUtil.copyProperties(sys, Tencent.class));
					}
					//同步删除视频记录
					courseVideoDao.deleteByVideoNo(courseVideo.getVideoNo());
				}
			}
			return Result.success(results);
		}
		return Result.error(ResultEnum.COURSE_DELETE_FAIL);
	}

	public Result<Integer> update(ChapterPeriodAuditUpdateREQ req) {
		if (StringUtils.isEmpty(req.getId())) {
			return Result.error("ID不能为空");
		}
		CourseChapterPeriodAudit record = BeanUtil.copyProperties(req, CourseChapterPeriodAudit.class);
		CourseVideo courseVideo = new CourseVideo();
		//补充视频相关字段
		if(!StringUtils.isEmpty(req.getVideoUrl())){
			SysVO sys = bossSys.getSys();
			Long videoNo = UrlUtil.UrlToKey(req.getVideoUrl(), sys.getAliyunOssUrl());
//			String key = req.getVideoNo().replaceAll(sys.getAliyunOssUrl(), "");
//			String videoNo = key.indexOf("/") > 0 ? key.substring((key.lastIndexOf("/") + 1), key.indexOf(".")) : key.substring(0, key.indexOf("."));
			courseVideo = courseVideoDao.getByVideoNo(videoNo);

			if(!ObjectUtils.isEmpty(courseVideo)){
				record.setVideoLength(courseVideo.getVideoLength());
				record.setVideoNo(Long.valueOf(videoNo));
				record.setIsVideo("1");
				record.setVideoName(req.getVideoName());
			}
		}
		//补充文档相关字段
		if(!StringUtils.isEmpty(req.getDocUrl())){
			record.setIsDoc(1);
			record.setDocName(req.getDocName());
			record.setDocUrl(req.getDocUrl());
		}
		int results = dao.updateById(record);
		if (results > 0) {
			//同步修改课程的审核状态
			CourseAudit courseAudit = new CourseAudit();
			courseAudit.setId(record.getCourseId());
			courseAudit.setAuditStatus(AuditStatusEnum.WAIT.getCode());
			courseAuditDao.updateById(courseAudit);
			//同步修改章节的审核状态
			CourseChapterAudit courseChapterAudit = new CourseChapterAudit();
			courseChapterAudit.setId(record.getChapterId());
			courseChapterAudit.setAuditStatus(AuditStatusEnum.WAIT.getCode());
			courseChapterAuditDao.updateById(courseChapterAudit);
			//同步修改课程视频信息
			if(!ObjectUtils.isEmpty(courseVideo)){
				courseVideo.setCourseId(record.getCourseId());
				courseVideo.setChapterId(record.getChapterId());
				courseVideo.setPeriodId(record.getId());
				courseVideoDao.updateById(courseVideo);
			}
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
