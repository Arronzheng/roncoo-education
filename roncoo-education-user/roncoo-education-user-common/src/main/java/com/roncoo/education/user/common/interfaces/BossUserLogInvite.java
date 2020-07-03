package com.roncoo.education.user.common.interfaces;

import com.roncoo.education.user.common.bean.qo.UserLogInviteSaveQO;
import com.roncoo.education.user.common.bean.qo.UserLogLoginQO;
import com.roncoo.education.user.common.bean.vo.UserLogInviteVO;
import com.roncoo.education.user.common.bean.vo.UserLogLoginVO;
import com.roncoo.education.util.base.Page;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 用户错误登录日志
 *
 * @author wujing
 */
public interface BossUserLogInvite {

	@RequestMapping(value = "/boss/user/userLogInvite/save", method = RequestMethod.POST)
	int save(@RequestBody UserLogInviteSaveQO qo);

	@RequestMapping(value = "/boss/user/userLogInvite/getByInvitedNo", method = RequestMethod.POST)
	UserLogInviteVO getByInvitedNo(@PathVariable(value = "userNo") Long userNo);

}
