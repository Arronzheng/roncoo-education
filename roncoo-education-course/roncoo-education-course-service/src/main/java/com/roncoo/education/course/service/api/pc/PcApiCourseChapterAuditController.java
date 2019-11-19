package com.roncoo.education.course.service.api.pc;

import com.roncoo.education.course.service.biz.pc.PcApiCourseChapterAuditBiz;
import com.roncoo.education.course.service.biz.pc.PcApiCourseChapterBiz;
import com.roncoo.education.course.service.common.req.*;
import com.roncoo.education.course.service.common.resq.CourseCategoryViewRESQ;
import com.roncoo.education.course.service.common.resq.CourseChapterAuditPageRESQ;
import com.roncoo.education.course.service.common.resq.CourseChapterPageRESQ;
import com.roncoo.education.course.service.common.req.CourseChapterUpdateREQ;
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
 * 章节管理
 *
 */
@RestController
@RequestMapping(value = "/course/pc/course/chapter/audit")
public class PcApiCourseChapterAuditController extends BaseController {

	@Autowired
	private PcApiCourseChapterAuditBiz biz;

	/**
	 * 分页列出课程章节信息
	 * 
	 * @param courseChapterAuditPageREQ
	 * @return
	 */
	@ApiOperation(value = "分页列出课程章节信息", notes = "分页列出课程章节信息")
	@RequestMapping(value = "/list", method = RequestMethod.POST)
	public Result<Page<CourseChapterAuditPageRESQ>> listForPage(@RequestBody CourseChapterAuditPageREQ courseChapterAuditPageREQ) {
		return biz.listForPage(courseChapterAuditPageREQ);
	}

	/**
	 * 添加课程章节信息
	 * 
	 * @param courseChapterUpdateREQ
	 * @return
	 */
	@ApiOperation(value = "添加课程章节信息", notes = "添加课程章节信息")
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public Result<Integer> save(@RequestBody CourseChapterUpdateREQ courseChapterUpdateREQ) {
		return biz.save(courseChapterUpdateREQ);
	}

	/**
	 * 删除课程章节信息
	 * 
	 * @param courseChapterDeleteREQ
	 * @return
	 */
	@ApiOperation(value = "删除课程章节信息", notes = "删除课程章节信息")
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public Result<Integer> delete(@RequestBody CourseChapterDeleteREQ courseChapterDeleteREQ) {
		return biz.delete(courseChapterDeleteREQ);
	}

	/**
	 * 更新课程章节信息
	 * 
	 * @param courseChapterUpdateRESQ
	 * @return
	 */
	@ApiOperation(value = "更新课程章节信息", notes = "更新课程章节信息")
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public Result<Integer> update(@RequestBody CourseChapterUpdateREQ courseChapterUpdateRESQ) {
		return biz.update(courseChapterUpdateRESQ);
	}

}
