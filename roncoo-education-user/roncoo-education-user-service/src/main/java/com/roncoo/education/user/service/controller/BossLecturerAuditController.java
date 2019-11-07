package com.roncoo.education.user.service.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.roncoo.education.user.common.bean.qo.LecturerAuditQO;
import com.roncoo.education.user.common.bean.vo.LecturerAuditVO;
import com.roncoo.education.user.common.interfaces.BossLecturerAudit;
import com.roncoo.education.user.service.controller.biz.BossLecturerAuditBiz;
import com.roncoo.education.util.base.BaseController;
import com.roncoo.education.util.base.Page;

/**
 * 讲师信息-审核
 *
 * @author wujing
 */
@RestController
public class BossLecturerAuditController extends BaseController implements BossLecturerAudit {

	@Autowired
	private BossLecturerAuditBiz biz;

	@Override
	public Page<LecturerAuditVO> listForPage(@RequestBody LecturerAuditQO qo) {
		return biz.listForPage(qo);
	}

	@Override
	public int save(@RequestBody LecturerAuditQO qo) {
		return biz.save(qo);
	}

	@Override
	public int deleteById(@PathVariable(value = "id") Long id) {
		return biz.deleteById(id);
	}

	@Override
	public int updateById(@RequestBody LecturerAuditQO qo) {
		return biz.updateById(qo);
	}

	@Override
	public LecturerAuditVO getById(@PathVariable(value = "id") Long id) {
		return biz.getById(id);
	}

	@Override
	public int audit(@RequestBody LecturerAuditQO qo) {
		return biz.audit(qo);
	}

	@Override
	public LecturerAuditVO checkUserAndLecturer(@RequestBody LecturerAuditQO qo) {
		return biz.checkUserAndLecturer(qo);
	}

}
