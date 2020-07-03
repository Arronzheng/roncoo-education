package com.roncoo.education.user.service.controller.biz;

import com.roncoo.education.user.common.bean.qo.UserLogInviteSaveQO;
import com.roncoo.education.user.common.bean.qo.UserLogLoginQO;
import com.roncoo.education.user.common.bean.vo.UserLogInviteVO;
import com.roncoo.education.user.common.bean.vo.UserLogLoginVO;
import com.roncoo.education.user.service.dao.UserLogInviteDao;
import com.roncoo.education.user.service.dao.UserLogLoginDao;
import com.roncoo.education.user.service.dao.impl.mapper.entity.UserLogInvite;
import com.roncoo.education.user.service.dao.impl.mapper.entity.UserLogLogin;
import com.roncoo.education.user.service.dao.impl.mapper.entity.UserLogLoginExample;
import com.roncoo.education.util.base.Page;
import com.roncoo.education.util.base.PageUtil;
import com.roncoo.education.util.tools.BeanUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 用户邀请日志
 *
 * @author
 */
@Component
public class BossUserLogInviteBiz {

	@Autowired
	private UserLogInviteDao dao;

	public int save(UserLogInviteSaveQO qo) {
		UserLogInvite record = BeanUtil.copyProperties(qo, UserLogInvite.class);
		return dao.save(record);
	}

	public UserLogInviteVO getByInvitedNo(Long userNo) {
		UserLogInvite userLogInvite = dao.getByInvitedUserNo(userNo);
		UserLogInviteVO userLogInviteVO = BeanUtil.copyProperties(userLogInvite, UserLogInviteVO.class);
		return userLogInviteVO;
	}
}
