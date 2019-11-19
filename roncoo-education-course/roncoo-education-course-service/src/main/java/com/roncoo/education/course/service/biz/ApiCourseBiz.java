package com.roncoo.education.course.service.biz;

import java.util.List;

import com.roncoo.education.course.service.dao.*;
import com.roncoo.education.course.service.dao.impl.mapper.entity.*;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.MultiMatchQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder.Field;
import org.elasticsearch.search.sort.FieldSortBuilder;
import org.elasticsearch.search.sort.SortBuilders;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.roncoo.education.course.service.common.bo.CourseInfoPageBO;
import com.roncoo.education.course.service.common.bo.CourseInfoSearchBO;
import com.roncoo.education.course.service.common.bo.CourseVideoBO;
import com.roncoo.education.course.service.common.dto.CourseChapterDTO;
import com.roncoo.education.course.service.common.dto.CourseChapterPeriodDTO;
import com.roncoo.education.course.service.common.dto.CourseInfoPageDTO;
import com.roncoo.education.course.service.common.dto.CourseInfoSearchPageDTO;
import com.roncoo.education.course.service.common.dto.CourseIntroduceDTO;
import com.roncoo.education.course.service.common.dto.CourseViewDTO;
import com.roncoo.education.course.service.common.dto.LecturerDTO;
import com.roncoo.education.course.service.common.es.EsCourse;
import com.roncoo.education.course.service.common.es.EsPageUtil;
import com.roncoo.education.course.service.common.es.ResultMapperExt;
import com.roncoo.education.course.service.dao.impl.mapper.entity.CourseExample.Criteria;
import com.roncoo.education.user.common.bean.vo.LecturerVO;
import com.roncoo.education.user.feign.IBossLecturer;
import com.roncoo.education.util.base.Page;
import com.roncoo.education.util.base.PageUtil;
import com.roncoo.education.util.base.Result;
import com.roncoo.education.util.enums.IsHfield;
import com.roncoo.education.util.enums.IsPutawayEnum;
import com.roncoo.education.util.enums.StatusIdEnum;
import com.roncoo.education.util.tools.BeanUtil;
import com.roncoo.education.util.tools.SqlUtil;
import com.xiaoleilu.hutool.util.CollectionUtil;

/**
 * 课程信息
 *
 * @author wujing
 */
@Component
public class ApiCourseBiz {

	@Autowired
	private CourseAuditDao courseAuditDao;
	@Autowired
	private CourseIntroduceDao courseIntroduceDao;
	@Autowired
	private CourseChapterAuditDao courseChapterAuditDao;
	@Autowired
	private CourseChapterPeriodAuditDao courseChapterPeriodAuditDao;
	@Autowired
	private IBossLecturer bossLecturer;

	@Autowired(required = false)
	private ElasticsearchTemplate elasticsearchTemplate;

	@Autowired
	private ResultMapperExt resultMapperExt;

	/**
	 * 课程详情接口
	 * 
	 * @param courseVideoBO
	 * @return
	 */
	public Result<CourseViewDTO> view(CourseVideoBO courseVideoBO) {
		if (courseVideoBO.getCourseId() == null) {
			return Result.error("课程ID不能为空");
		}
		// 课程信息
		CourseAudit courseAudit = courseAuditDao.getById(courseVideoBO.getCourseId());
		if (courseAudit == null) {
			return Result.error("找不到该课程信息");
		}
		CourseViewDTO data = BeanUtil.copyProperties(courseAudit, CourseViewDTO.class);

		// 课程介绍
		CourseIntroduce courseIntroduce = courseIntroduceDao.getById(data.getIntroduceId());
		if (!StringUtils.isEmpty(courseIntroduce)) {
			data.setIntroduce(BeanUtil.copyProperties(courseIntroduce, CourseIntroduceDTO.class).getIntroduce());
		}

		// 讲师信息
		LecturerVO lecturerVO = bossLecturer.getByLecturerUserNo(data.getLecturerUserNo());
		if (StringUtils.isEmpty(lecturerVO)) {
			return Result.error("根据讲师用户编号没找到对应的讲师信息!");
		}
		data.setLecturer(BeanUtil.copyProperties(lecturerVO, LecturerDTO.class));

		// 章节信息
		List<CourseChapterAudit> courseChapterAuditList = courseChapterAuditDao.listByCourseIdAndStatusId(courseVideoBO.getCourseId(), StatusIdEnum.YES.getCode());
		if (CollectionUtil.isNotEmpty(courseChapterAuditList)) {
			data.setChapterList(PageUtil.copyList(courseChapterAuditList, CourseChapterDTO.class));
		}

		// 课时信息
		if (CollectionUtil.isNotEmpty(data.getChapterList())) {
			for (CourseChapterDTO courseChapterDTO : data.getChapterList()) {
				List<CourseChapterPeriodAudit> courseChapterPeriodAuditList = courseChapterPeriodAuditDao.listByChapterIdAndStatusId(courseChapterDTO.getId(), StatusIdEnum.YES.getCode());
				courseChapterDTO.setPeriodList(PageUtil.copyList(courseChapterPeriodAuditList, CourseChapterPeriodDTO.class));
			}
		}
		return Result.success(data);
	}

	/**
	 * 课程信息列表接口
	 * 
	 * @param courseInfoPageBO
	 * @return
	 * @author wuyun
	 */
	public Result<Page<CourseInfoPageDTO>> list(CourseInfoPageBO courseInfoPageBO) {
		CourseAuditExample example = new CourseAuditExample();
		CourseAuditExample.Criteria c = example.createCriteria();
		c.andStatusIdEqualTo(StatusIdEnum.YES.getCode());
		c.andIsPutawayEqualTo(IsPutawayEnum.YES.getCode());
		if (!StringUtils.isEmpty(courseInfoPageBO.getCategoryId1())) {
			c.andCategoryId1EqualTo(courseInfoPageBO.getCategoryId1());
		}
		if (!StringUtils.isEmpty(courseInfoPageBO.getCategoryId2())) {
			c.andCategoryId2EqualTo(courseInfoPageBO.getCategoryId2());
		}
		if (!StringUtils.isEmpty(courseInfoPageBO.getCategoryId3())) {
			c.andCategoryId3EqualTo(courseInfoPageBO.getCategoryId3());
		}
		if (!StringUtils.isEmpty(courseInfoPageBO.getIsFree())) {
			c.andIsFreeEqualTo(courseInfoPageBO.getIsFree());
		}
		if (!StringUtils.isEmpty(courseInfoPageBO.getCourseName())) {
			c.andCourseNameLike(PageUtil.rightLike(SqlUtil.checkSql(courseInfoPageBO.getCourseName())));
		}
		example.setOrderByClause(" course_sort desc, id desc ");
		Page<CourseAudit> page = courseAuditDao.listForPage(courseInfoPageBO.getPageCurrent(), courseInfoPageBO.getPageSize(), example);
		return Result.success(PageUtil.transform(page, CourseInfoPageDTO.class));
	}

	/**
	 * 课程搜索列表接口
	 * 
	 * @param bo
	 * @author wuyun
	 */
	public Result<Page<CourseInfoSearchPageDTO>> searchList(CourseInfoSearchBO bo) {
		if (StringUtils.isEmpty(bo.getOrgNo())) {
			return Result.error("orgNo不能为空");
		}
		if (bo.getPageCurrent() <= 0) {
			bo.setPageCurrent(1);
		}
		if (bo.getPageSize() <= 0) {
			bo.setPageSize(20);
		}

		if (StringUtils.isEmpty(bo.getCourseName())) {
			return Result.success(new Page<CourseInfoSearchPageDTO>());
		}

		String heightField = "courseName";

		Field hfield = null;
		if (bo.getIsHfield() != null && bo.getIsHfield().equals(IsHfield.YES.getCode())) {
			hfield = new HighlightBuilder.Field(heightField).preTags("<mark>").postTags("</mark>");
		}

		NativeSearchQueryBuilder nsb = new NativeSearchQueryBuilder();
		if (bo.getIsHfield() != null && bo.getIsHfield().equals(IsHfield.YES.getCode())) {
			nsb.withHighlightFields(hfield);// 高亮字段
		}
		nsb.withSort(SortBuilders.scoreSort().order(SortOrder.DESC));// 评分排序（_source）
		nsb.withSort(new FieldSortBuilder("courseSort").order(SortOrder.DESC));// 课程排序（courseSort）
		nsb.withPageable(PageRequest.of(bo.getPageCurrent() - 1, bo.getPageSize()));
		// 复合查询，外套boolQuery
		BoolQueryBuilder qb = QueryBuilders.boolQuery();
		// 精确查询termQuery不分词，must参数等价于AND
		qb.must(QueryBuilders.termQuery("orgNo", bo.getOrgNo()));
		// 模糊查询multiMatchQuery，最佳字段best_fields
		qb.must(QueryBuilders.multiMatchQuery(bo.getCourseName(), "courseName", "lecturerName").type(MultiMatchQueryBuilder.Type.BEST_FIELDS));

		nsb.withQuery(qb);

		org.springframework.data.domain.Page<EsCourse> page = elasticsearchTemplate.queryForPage(nsb.build(), EsCourse.class, resultMapperExt);
		return Result.success(EsPageUtil.transform(page, CourseInfoSearchPageDTO.class));
	}
}
