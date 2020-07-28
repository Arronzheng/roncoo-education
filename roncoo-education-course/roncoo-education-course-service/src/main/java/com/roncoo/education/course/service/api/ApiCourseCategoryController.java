package com.roncoo.education.course.service.api;

import com.roncoo.education.course.service.common.bo.CourseInfoPageBO;
import com.roncoo.education.course.service.common.dto.CourseCategoryTreeDTO;
import com.roncoo.education.util.base.Tree;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.roncoo.education.course.service.biz.ApiCourseCategoryBiz;
import com.roncoo.education.course.service.common.dto.CourseCategoryListDTO;
import com.roncoo.education.util.base.BaseController;
import com.roncoo.education.util.base.Result;

import io.swagger.annotations.ApiOperation;

/**
 * 课程分类
 */
@RestController
@RequestMapping(value = "/course/api/course/category")
public class ApiCourseCategoryController extends BaseController {

	@Autowired
	private ApiCourseCategoryBiz biz;

	/**
	 * 普通课程分类列表接口
	 *
	 *
	 */
	@ApiOperation(value = "课程分类列表接口", notes = "课程分类列表")
	@RequestMapping(value = "/list", method = RequestMethod.POST)
	public Result<CourseCategoryListDTO> list(@RequestBody CourseInfoPageBO courseInfoPageBO) {
		return biz.list(courseInfoPageBO);
	}

	/**
	 * 课程分类信息树
	 *
	 * @return
	 */
	@ApiOperation(value = "课程分类信息树", notes = "课程分类信息树")
	@RequestMapping(value = "/listForTree", method = RequestMethod.POST)
	public Result<Tree<CourseCategoryTreeDTO>> listForTree() {
		return biz.listForTree();
	}

}
