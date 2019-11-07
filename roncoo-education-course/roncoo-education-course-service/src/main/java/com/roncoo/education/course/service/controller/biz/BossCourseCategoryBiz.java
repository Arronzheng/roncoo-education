package com.roncoo.education.course.service.controller.biz;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.roncoo.education.course.common.bean.qo.CourseCategoryQO;
import com.roncoo.education.course.common.bean.vo.CourseCategoryVO;
import com.roncoo.education.course.service.dao.CourseCategoryDao;
import com.roncoo.education.course.service.dao.impl.mapper.entity.CourseCategory;
import com.roncoo.education.course.service.dao.impl.mapper.entity.CourseCategoryExample;
import com.roncoo.education.course.service.dao.impl.mapper.entity.CourseCategoryExample.Criteria;
import com.roncoo.education.util.base.Page;
import com.roncoo.education.util.base.PageUtil;
import com.roncoo.education.util.tools.BeanUtil;

/**
 * 课程分类
 *
 * @author wujing
 */
@Component
public class BossCourseCategoryBiz {

	@Autowired
	private CourseCategoryDao dao;

	public Page<CourseCategoryVO> listForPage(CourseCategoryQO qo) {
		CourseCategoryExample example = new CourseCategoryExample();
		Criteria c = example.createCriteria();
		if (qo.getStatusId() != null) {
			c.andStatusIdEqualTo(qo.getStatusId());
		}
		if (qo.getCategoryType() != null) {
			c.andCategoryTypeEqualTo(qo.getCategoryType());
		}
		if (!StringUtils.isEmpty(qo.getCategoryName())) {
			c.andCategoryNameLike(PageUtil.rightLike(qo.getCategoryName()));
		} else {
			c.andFloorEqualTo(1);
		}
		example.setOrderByClause(" status_id desc, sort desc, id desc ");
		Page<CourseCategory> page = dao.listForPage(qo.getPageCurrent(), qo.getPageSize(), example);
		Page<CourseCategoryVO> vo = PageUtil.transform(page, CourseCategoryVO.class);
		for (CourseCategoryVO courseCategoryVO : vo.getList()) {
			courseCategoryVO.setList(recursionList(courseCategoryVO.getId()));
		}
		return vo;
	}

	/**
	 * 递归展示分类
	 * 
	 * @author WY
	 */
	private List<CourseCategoryVO> recursionList(Long parentId) {
		List<CourseCategoryVO> list = new ArrayList<>();
		List<CourseCategory> CourseCategoryList = dao.listByParentId(parentId);
		if (CollectionUtils.isNotEmpty(CourseCategoryList)) {
			for (CourseCategory courseCategory : CourseCategoryList) {
				CourseCategoryVO vo = BeanUtil.copyProperties(courseCategory, CourseCategoryVO.class);
				vo.setList(recursionList(courseCategory.getId()));
				list.add(vo);
			}
		}
		return list;
	}

	public int save(CourseCategoryQO qo) {
		CourseCategory record = BeanUtil.copyProperties(qo, CourseCategory.class);
		if (qo.getParentId() == 0 && qo.getFloor() == 1) {
			record.setFloor(1);
		} else {
			record.setFloor(qo.getFloor() + 1);
		}
		return dao.save(record);
	}

	public int deleteById(Long id) {
		List<CourseCategory> list = dao.listByParentId(id);
		if (list.size() > 0) {
			return -1;
		}
		return dao.deleteById(id);
	}

	public CourseCategoryVO getById(Long id) {
		CourseCategory record = dao.getById(id);
		return BeanUtil.copyProperties(record, CourseCategoryVO.class);
	}

	public int updateById(CourseCategoryQO qo) {
		CourseCategory record = BeanUtil.copyProperties(qo, CourseCategory.class);
		return dao.updateById(record);
	}

	public List<CourseCategoryVO> listByFloor(Integer floor) {
		List<CourseCategory> courseCategory = dao.listByFloor(floor);
		return BeanUtil.copyProperties(courseCategory, CourseCategoryVO.class);
	}

	public List<CourseCategoryVO> listByParentId(Long parentId) {
		List<CourseCategory> list = dao.listByParentId(parentId);
		return PageUtil.copyList(list, CourseCategoryVO.class);
	}

	public List<CourseCategoryVO> listByFloorAndCategoryId(CourseCategoryQO qo) {
		List<CourseCategory> courseCategory = dao.listByFloorAndCategoryId(qo.getFloor(), qo.getParentId());
		return BeanUtil.copyProperties(courseCategory, CourseCategoryVO.class);
	}

}
