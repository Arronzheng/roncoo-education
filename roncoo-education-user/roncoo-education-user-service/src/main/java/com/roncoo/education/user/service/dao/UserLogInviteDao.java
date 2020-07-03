
package com.roncoo.education.user.service.dao;

import com.roncoo.education.user.service.common.resq.UserExtViewRESQ;
import com.roncoo.education.user.service.dao.impl.mapper.entity.UserLogInvite;
import com.roncoo.education.user.service.dao.impl.mapper.entity.UserLogInviteExample;
import com.roncoo.education.util.base.Page;
import com.roncoo.education.util.base.Result;

import java.util.List;

/**
 * 用户邀请记录表 服务类
 *
 * @author husend
 * @since 2020-05-19
 */
public interface UserLogInviteDao {
            int save(UserLogInvite record);

            int deleteById(Long id);

            int updateById(UserLogInvite record);

            UserLogInvite getById(Long id);

            Page<UserLogInvite> listForPage(int pageCurrent, int pageSize, UserLogInviteExample example);

            UserLogInvite getByInvitedUserNo(Long userNo);

            List<UserLogInvite> getByInviteUserNo(Long userNo);
}
