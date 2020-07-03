package com.roncoo.education.user.service.controller;

import com.roncoo.education.user.common.bean.qo.UserLogInviteSaveQO;
import com.roncoo.education.user.common.bean.qo.UserLogLoginQO;
import com.roncoo.education.user.common.bean.vo.UserLogInviteVO;
import com.roncoo.education.user.common.bean.vo.UserLogLoginVO;
import com.roncoo.education.user.common.interfaces.BossUserLogInvite;
import com.roncoo.education.user.common.interfaces.BossUserLogLogin;
import com.roncoo.education.user.service.controller.biz.BossUserLogInviteBiz;
import com.roncoo.education.user.service.controller.biz.BossUserLogLoginBiz;
import com.roncoo.education.util.base.BaseController;
import com.roncoo.education.util.base.Page;
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
public class BossUserLogInviteController extends BaseController implements BossUserLogInvite {

	@Autowired
	private BossUserLogInviteBiz biz;


	@Override
	public int save(@RequestBody UserLogInviteSaveQO qo) {
		return biz.save(qo);
	}

	@Override
	public UserLogInviteVO getByInvitedNo(@PathVariable(value = "userNo") Long userNo) {
		return biz.getByInvitedNo(userNo);
	}
}
