package com.roncoo.education.course.service.api.pc;

import com.roncoo.education.course.service.common.req.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.roncoo.education.course.service.biz.pc.PcApiCourseAuditBiz;
import com.roncoo.education.course.service.common.resq.CourseAuditGetRESQ;
import com.roncoo.education.course.service.common.resq.CourseAuditPageRESQ;
import com.roncoo.education.course.service.common.resq.CourseAuditViewRESQ;
import com.roncoo.education.util.base.BaseController;
import com.roncoo.education.util.base.Page;
import com.roncoo.education.util.base.Result;

import io.swagger.annotations.ApiOperation;

/**
 * 课程信息-审核
 */
@RestController
@RequestMapping(value = "/course/pc/course/audit")
public class PcApiCourseAuditController extends BaseController {

	@Autowired
	private PcApiCourseAuditBiz biz;

	/**
	 * 课程-审核分页列出接口
	 * 
	 * @param courseAuditPageREQ
	 * @return
	 */
	@ApiOperation(value = "课程-审核分页列出接口", notes = "课程-审核分页列出接口")
	@RequestMapping(value = "/list", method = RequestMethod.POST)
	public Result<Page<CourseAuditPageRESQ>> list(@RequestBody CourseAuditPageREQ courseAuditPageREQ) {
		return biz.list(courseAuditPageREQ);
	}

	/**
	 * 课程-审核更新接口
	 * 
	 * @param courseAuditUpdateREQ
	 * @return
	 */
	@ApiOperation(value = "课程-审核更新接口", notes = "课程-审核更新接口")
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public Result<Integer> update(@RequestBody CourseAuditUpdateREQ courseAuditUpdateREQ) {
		return biz.update(courseAuditUpdateREQ);
	}

	/**
	 * 课程-审核接口
	 * 
	 * @param courseAuditAuditStatusREQ
	 * @return
	 */
	@ApiOperation(value = "课程-审核接口", notes = "课程-审核接口")
	@RequestMapping(value = "/audit", method = RequestMethod.POST)
	public Result<Integer> audit(@RequestBody CourseAuditAuditStatusREQ courseAuditAuditStatusREQ) {
		return biz.audit(courseAuditAuditStatusREQ);
	}

	/**
	 * 课程查看接口(课程修改使用)
	 * 
	 * @param courseAuditGetREQ
	 * @return
	 */
	@ApiOperation(value = "课程-审核查看接口(课程修改使用)", notes = "课程-审核查看接口(课程修改使用)")
	@RequestMapping(value = "/get", method = RequestMethod.POST)
	public Result<CourseAuditGetRESQ> get(@RequestBody CourseAuditGetREQ courseAuditGetREQ) {
		return biz.get(courseAuditGetREQ);
	}

	/**
	 * 课程-审核查看接口
	 * 
	 * @param courseAuditViewREQ
	 * @return
	 */
	@ApiOperation(value = "课程-审核查看详情接口", notes = "课程-审核查看详情接口")
	@RequestMapping(value = "/view", method = RequestMethod.POST)
	public Result<CourseAuditViewRESQ> view(@RequestBody CourseAuditViewREQ courseAuditViewREQ) {
		return biz.view(courseAuditViewREQ);
	}

	/**
	 * 课程-审核查看接口
	 *
	 * @param courseAuditSaveREQ
	 * @return
	 */
	@ApiOperation(value = "课程-审核保存接口", notes = "课程-审核保存接口")
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public Result<Integer> save(@RequestBody CourseAuditSaveREQ courseAuditSaveREQ) {
		return biz.save(courseAuditSaveREQ);
	}
	/**
	 * 课程-审核删除接口
	 *
	 * @param courseAuditDeleteREQ
	 * @return
	 */
	@ApiOperation(value = "课程-审核删除接口", notes = "课程-审核删除接口")
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public Result<Integer> delete(@RequestBody CourseAuditDeleteREQ courseAuditDeleteREQ) {
		return biz.delete(courseAuditDeleteREQ);
	}

}
