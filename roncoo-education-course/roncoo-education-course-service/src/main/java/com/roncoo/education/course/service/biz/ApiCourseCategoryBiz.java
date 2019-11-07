package com.roncoo.education.course.service.biz;

import java.util.ArrayList;
import java.util.List;

import com.roncoo.education.course.service.common.dto.*;
import com.roncoo.education.util.base.Tree;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.roncoo.education.course.service.dao.CourseCategoryDao;
import com.roncoo.education.course.service.dao.impl.mapper.entity.CourseCategory;
import com.roncoo.education.util.enums.CategoryTypeEnum;
import com.roncoo.education.util.enums.StatusIdEnum;
import com.roncoo.education.util.tools.ArrayListUtil;
import com.roncoo.education.util.tools.BeanUtil;
import com.roncoo.education.util.base.Result;

/**
 * 课程分类
 *
 * @author wujing
 */
@Component
public class ApiCourseCategoryBiz {

	@Autowired
	private CourseCategoryDao dao;

	/**
	 * 获取课程分类列表
	 * 
	 * @return
	 * @author wuyun
	 */
	public Result<CourseCategoryListDTO> list() {
		CourseCategoryListDTO dto = new CourseCategoryListDTO();
		// 根据分类类型、层级查询可用状态的课程分类集合
		List<CourseCategory> oneCategoryList = dao.listByCategoryTypeAndFloorAndStatusId(CategoryTypeEnum.COURSE.getCode(), 1, StatusIdEnum.YES.getCode());
		if (StringUtils.isEmpty(oneCategoryList)) {
			return Result.success(dto);
		}
		List<CourseCategoryDTO> oneList = new ArrayList<>();
		// 查找一级分类下的二级分类
		for (CourseCategory courseCategory : oneCategoryList) {
			// 设置一级分类
			CourseCategoryDTO oneCategory = BeanUtil.copyProperties(courseCategory, CourseCategoryDTO.class);

			// 查找一级分类下的二级分类
			List<CourseCategory> twoCategoryList = dao.listByParentId(courseCategory.getId());
			if (StringUtils.isEmpty(twoCategoryList)) {
				oneList.add(oneCategory);
				dto.setCourseCategoryList(oneList);
				continue;
			}
			List<CourseCategoryTwoDTO> twoList = new ArrayList<>();
			List<CourseCategoryThreeDTO> threeList = new ArrayList<>();
			for (CourseCategory twoCategory : twoCategoryList) {
				CourseCategoryTwoDTO twoDto = new CourseCategoryTwoDTO();
				twoDto = BeanUtil.copyProperties(twoCategory, CourseCategoryTwoDTO.class);
				List<CourseCategory> threeCategoryList = dao.listByParentId(twoCategory.getId());
				if (CollectionUtils.isNotEmpty(threeCategoryList)) {
					// 复制三级分类信息返回
					threeList = ArrayListUtil.copy(threeCategoryList, CourseCategoryThreeDTO.class);
					twoDto.setThreeList(threeList);
				}
				twoList.add(twoDto);
			}
			oneCategory.setTwoList(twoList);
			// 复制二级分类信息返回
			oneList.add(oneCategory);
			dto.setCourseCategoryList(oneList);
		}
		return Result.success(dto);
	}

    public Result<Tree<CourseCategoryTreeDTO>> listForTree() {
		Tree<CourseCategoryTreeDTO> listTree = new Tree<CourseCategoryTreeDTO>();
		List<CourseCategoryTreeDTO> list = new ArrayList<CourseCategoryTreeDTO>();
		Long parentId = 0L;
		List<CourseCategory> CourseCategoryList = dao.listByParentId(parentId);
		if (CollectionUtils.isNotEmpty(CourseCategoryList)) {
			for (CourseCategory courseCategory : CourseCategoryList) {
				//CourseCategoryPageRESQ resq = BeanUtil.copyProperties(courseCategory, CourseCategoryPageRESQ.class);
				CourseCategoryTreeDTO tree = new CourseCategoryTreeDTO();
				tree.setLabel(courseCategory.getCategoryName());
				tree.setValue(courseCategory.getId());
				List<CourseCategoryTreeDTO> courseCategoryTreeDTOList = recurrenceList(courseCategory.getId());
				if(CollectionUtils.isNotEmpty(courseCategoryTreeDTOList)){
					tree.setChildren(courseCategoryTreeDTOList);
				}else{
					tree.setChildren(null);
				}
				list.add(tree);
				listTree.setList(list);
			}
		}
		return Result.success(listTree);
    }

	private List<CourseCategoryTreeDTO> recurrenceList(Long parentId) {
		List<CourseCategoryTreeDTO> list = new ArrayList<>();
		List<CourseCategory> CourseCategoryList = dao.listByParentId(parentId);
		if (CollectionUtils.isNotEmpty(CourseCategoryList)) {
			for (CourseCategory courseCategory : CourseCategoryList) {
				CourseCategoryTreeDTO tree = new CourseCategoryTreeDTO();
				tree.setLabel(courseCategory.getCategoryName());
				tree.setValue(courseCategory.getId());
				List<CourseCategoryTreeDTO> courseCategoryTreeDTOList = recurrenceList(courseCategory.getId());
				if(CollectionUtils.isNotEmpty(courseCategoryTreeDTOList)){
					tree.setChildren(courseCategoryTreeDTOList);
				}else{
					tree.setChildren(null);
				}
				list.add(tree);
			}
		}
		return list;
	}
}
