package com.roncoo.education.user.service.controller;

import com.roncoo.education.user.common.bean.qo.SvipQO;
import com.roncoo.education.user.common.bean.qo.UserQO;
import com.roncoo.education.user.common.bean.vo.SvipVO;
import com.roncoo.education.user.common.bean.vo.UserVO;
import com.roncoo.education.user.common.interfaces.BossVip;
import com.roncoo.education.user.service.controller.biz.BossUserBiz;
import com.roncoo.education.user.service.controller.biz.BossVipBiz;
import com.roncoo.education.util.base.BaseController;
import com.roncoo.education.util.base.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * 用户基本信息
 *
 *
 */
@RestController
public class BossVipController extends BaseController implements BossVip {

	@Autowired
	private BossVipBiz biz;

	@Override
	public int save(@RequestBody SvipQO record) {
		return biz.save(record);
	}

	@Override
	public int deleteById(@PathVariable(value = "id") Long id) {
		return biz.delete(id);
	}

	@Override
	public int updateById(@RequestBody SvipQO record) {
		return biz.updateById(record);
	}

	@Override
	public SvipVO getById(@PathVariable(value = "id") Long id) {
		return null;
	}

	@Override
	public SvipVO getByUserNo(@PathVariable(value = "userNo") Long userNo) {
		return biz.getByUserNo(userNo);
	}

	@Override
	public Page<SvipVO> listForPage(@RequestBody SvipQO record) {
		return null;
	}
}
