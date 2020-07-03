package com.roncoo.education.user.common.interfaces;

import com.roncoo.education.user.common.bean.qo.UserLogCommissionSaveQO;
import com.roncoo.education.user.common.bean.qo.UserLogInviteSaveQO;
import com.roncoo.education.user.common.bean.vo.UserLogInviteVO;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 用户佣金日志
 *
 * @author
 */
public interface BossUserLogCommission {

	@RequestMapping(value = "/boss/user/userLogCommission/save", method = RequestMethod.POST)
	int save(@RequestBody UserLogCommissionSaveQO qo);

}
