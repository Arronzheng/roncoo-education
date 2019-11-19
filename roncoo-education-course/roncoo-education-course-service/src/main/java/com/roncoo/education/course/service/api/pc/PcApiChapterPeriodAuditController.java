package com.roncoo.education.course.service.api.pc;

import com.roncoo.education.course.service.biz.pc.PcApiChapterPeriodAuditBiz;
import com.roncoo.education.course.service.biz.pc.PcApiChapterPeriodBiz;
import com.roncoo.education.course.service.common.req.*;
import com.roncoo.education.course.service.common.resq.ChapterPeriodAuditPageRESQ;
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
 * 课时管理
 *
 */
@RestController
@RequestMapping(value = "/course/pc/course/period/audit")
public class PcApiChapterPeriodAuditController extends BaseController {

	@Autowired
	private PcApiChapterPeriodAuditBiz biz;

	/**
	 * 分页列出课时信息
	 * 
	 * @param chapterPeriodPageRESQ
	 * @return
	 */
	@ApiOperation(value = "分页列出课时信息", notes = "分页列出课时信息")
	@RequestMapping(value = "/list", method = RequestMethod.POST)
	public Result<Page<ChapterPeriodAuditPageRESQ>> listForPage(@RequestBody ChapterPeriodAuditPageREQ chapterPeriodPageRESQ) {
		return biz.listForPage(chapterPeriodPageRESQ);
	}

	/**
	 * 添加课时信息
	 * 
	 * @param chapterPeriodSaveREQ
	 * @return
	 */
	@ApiOperation(value = "添加课时信息", notes = "添加课时信息")
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public Result<Integer> save(@RequestBody ChapterPeriodAuditSaveREQ chapterPeriodSaveREQ) {
		return biz.save(chapterPeriodSaveREQ);
	}

	/**
	 * 删除课时信息
	 * 
	 * @param chapterPeriodDeleteREQ
	 * @return
	 */
	@ApiOperation(value = "删除课时信息", notes = "删除课时信息")
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public Result<Integer> delete(@RequestBody ChapterPeriodAuditDeleteREQ chapterPeriodDeleteREQ) {
		return biz.delete(chapterPeriodDeleteREQ);
	}

	/**
	 * 更新课时分类信息
	 * 
	 * @param chapterPeriodAuditUpdateREQ
	 * @return
	 */
	@ApiOperation(value = "更新课时信息", notes = "更新课时信息")
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public Result<Integer> update(@RequestBody ChapterPeriodAuditUpdateREQ chapterPeriodAuditUpdateREQ) {
		return biz.update(chapterPeriodAuditUpdateREQ);
	}
}
