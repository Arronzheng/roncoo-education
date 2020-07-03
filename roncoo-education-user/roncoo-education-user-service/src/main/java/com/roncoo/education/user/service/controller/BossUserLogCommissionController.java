package com.roncoo.education.user.service.controller;

import com.roncoo.education.user.common.bean.qo.UserLogCommissionSaveQO;
import com.roncoo.education.user.common.bean.qo.UserLogInviteSaveQO;
import com.roncoo.education.user.common.bean.vo.UserLogInviteVO;
import com.roncoo.education.user.common.interfaces.BossUserLogCommission;
import com.roncoo.education.user.common.interfaces.BossUserLogInvite;
import com.roncoo.education.user.service.controller.biz.BossUserLogCommissionBiz;
import com.roncoo.education.user.service.controller.biz.BossUserLogInviteBiz;
import com.roncoo.education.util.base.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * 用户邀请日志
 *
 * @author
 */
@RestController
public class BossUserLogCommissionController extends BaseController implements BossUserLogCommission {

	@Autowired
	private BossUserLogCommissionBiz biz;


	@Override
	public int save(@RequestBody UserLogCommissionSaveQO qo) {
		return biz.save(qo);
	}

}
