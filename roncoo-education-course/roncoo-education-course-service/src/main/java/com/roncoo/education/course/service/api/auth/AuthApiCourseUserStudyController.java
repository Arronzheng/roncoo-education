package com.roncoo.education.course.service.api.auth;

import com.roncoo.education.course.service.biz.auth.AuthApiCourseUserStudyBiz;
import com.roncoo.education.course.service.biz.auth.AuthApiCourseUserStudyLogBiz;
import com.roncoo.education.course.service.common.bo.auth.AuthCourseUserStudyLogPageBO;
import com.roncoo.education.course.service.common.bo.auth.AuthCourseUserStudyPageBO;
import com.roncoo.education.course.service.common.dto.auth.AuthCourseUserStudyLogPageDTO;
import com.roncoo.education.course.service.common.dto.auth.AuthCourseUserStudyPageDTO;
import com.roncoo.education.util.base.BaseController;
import com.roncoo.education.util.base.Page;
import com.roncoo.education.util.base.Result;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * 我的课程信息
 */
@RestController
@RequestMapping(value = "/course/auth/course/user/study")
public class AuthApiCourseUserStudyController extends BaseController {

	@Autowired
	private AuthApiCourseUserStudyBiz biz;

	/**
	 * 我的课程分页列出接口
	 * 
	 * @param authCourseUserStudyPageBO
	 * @return
	 * @author wuyun
	 */
	@ApiOperation(value = "分页列出接口", notes = "我的课程分页列出接口")
	@RequestMapping(value = "/list", method = RequestMethod.POST)
	public Result<Page<AuthCourseUserStudyPageDTO>> list(@RequestBody AuthCourseUserStudyPageBO authCourseUserStudyPageBO) {
		return biz.list(authCourseUserStudyPageBO);
	}

}
