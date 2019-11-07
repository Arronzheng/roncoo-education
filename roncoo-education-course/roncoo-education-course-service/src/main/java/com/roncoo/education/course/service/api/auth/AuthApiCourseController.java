package com.roncoo.education.course.service.api.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.roncoo.education.course.service.biz.auth.AuthApiCourseBiz;
import com.roncoo.education.course.service.common.bo.auth.AuthCourseSignBO;
import com.roncoo.education.course.service.common.bo.auth.AuthCourseViewBO;
import com.roncoo.education.course.service.common.dto.auth.AuthCourseSignDTO;
import com.roncoo.education.course.service.common.dto.auth.AuthCourseViewDTO;
import com.roncoo.education.util.base.BaseController;
import com.roncoo.education.util.base.Result;

import io.swagger.annotations.ApiOperation;

/**
 * 课程信息-审核
 *
 * @author wujing
 */
@RestController
@RequestMapping(value = "/course/auth/course")
public class AuthApiCourseController extends BaseController {

	@Autowired
	private AuthApiCourseBiz biz;

	/**
	 * 课程详情接口
	 */
	@ApiOperation(value = "课程详情接口", notes = "用户登录后获取课程详情信息接口")
	@RequestMapping(value = "/view", method = RequestMethod.POST)
	public Result<AuthCourseViewDTO> view(@RequestBody AuthCourseViewBO authCourseViewBO) {
		return biz.view(authCourseViewBO);
	}

	/**
	 * 课时播放获取sign值接口
	 */
	@ApiOperation(value = "课时播放获取sign值接口", notes = "课时播放获取sign值接口")
	@RequestMapping(value = "/sign", method = RequestMethod.POST)
	public Result<AuthCourseSignDTO> sign(@RequestBody AuthCourseSignBO authCourseSignBO) {
		return biz.sign(authCourseSignBO);
	}

}
