package com.roncoo.education.course.service.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;

import com.roncoo.education.course.common.bean.qo.CourseChapterPeriodQO;
import com.roncoo.education.course.common.bean.vo.CourseChapterPeriodVO;
import com.roncoo.education.course.common.interfaces.BossCourseChapterPeriod;
import com.roncoo.education.course.service.controller.biz.BossCourseChapterPeriodBiz;
import com.roncoo.education.util.base.BaseController;
import com.roncoo.education.util.base.Page;

/**
 * 课时信息
 *
 * @author wujing
 */
@RestController
public class BossCourseChapterPeriodController extends BaseController implements BossCourseChapterPeriod {

	@Autowired
	private BossCourseChapterPeriodBiz biz;

	@Override
	public Page<CourseChapterPeriodVO> listForPage(@RequestBody CourseChapterPeriodQO qo) {
		return biz.listForPage(qo);
	}

	@Override
	public int save(@RequestBody CourseChapterPeriodQO qo) {
		return biz.save(qo);
	}

	@Override
	public int deleteById(@PathVariable(value = "id") Long id) {
		return biz.deleteById(id);
	}

	@Override
	public int updateById(@RequestBody CourseChapterPeriodQO qo) {
		return biz.updateById(qo);
	}

	@Override
	public CourseChapterPeriodVO getById(@PathVariable(value = "id") Long id) {
		return biz.getById(id);
	}

	@Override
	public CourseChapterPeriodVO getByVideoNo(@RequestBody Long videoNo) {
		return biz.getByVideoNo(videoNo);
	}

}
