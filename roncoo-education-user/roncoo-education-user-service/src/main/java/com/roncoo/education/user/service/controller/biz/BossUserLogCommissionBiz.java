package com.roncoo.education.user.service.controller.biz;

import com.roncoo.education.user.common.bean.qo.UserLogCommissionSaveQO;
import com.roncoo.education.user.common.bean.qo.UserLogInviteSaveQO;
import com.roncoo.education.user.common.bean.vo.UserLogInviteVO;
import com.roncoo.education.user.service.dao.UserLogCommissionDao;
import com.roncoo.education.user.service.dao.UserLogInviteDao;
import com.roncoo.education.user.service.dao.impl.mapper.entity.UserLogCommission;
import com.roncoo.education.user.service.dao.impl.mapper.entity.UserLogInvite;
import com.roncoo.education.util.tools.BeanUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 用户邀请日志
 *
 * @author
 */
@Component
public class BossUserLogCommissionBiz {

	@Autowired
	private UserLogCommissionDao dao;

	public int save(UserLogCommissionSaveQO qo) {
		UserLogCommission record = BeanUtil.copyProperties(qo, UserLogCommission.class);
		return dao.save(record);
	}
}
