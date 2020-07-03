package com.roncoo.education.user.service.biz.auth;

import com.roncoo.education.user.service.common.bo.auth.AuthApiUserInviteLogBO;
import com.roncoo.education.user.service.common.resq.UserExtViewRESQ;
import com.roncoo.education.user.service.dao.UserExtDao;
import com.roncoo.education.user.service.dao.UserLogInviteDao;
import com.roncoo.education.user.service.dao.impl.mapper.entity.UserExt;
import com.roncoo.education.user.service.dao.impl.mapper.entity.UserLogInvite;
import com.roncoo.education.util.base.BaseBiz;
import com.roncoo.education.util.base.Result;
import com.roncoo.education.util.tools.BeanUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

/**
 * @description:
 * @author: husend
 * @time: 2020/5/19
 */
@Component
public class AuthApiUserInviteLogBiz extends BaseBiz {

    @Autowired
    private UserLogInviteDao dao;
    @Autowired
    private UserExtDao userExtDao;

    public Result<UserExtViewRESQ> getInviteUser(AuthApiUserInviteLogBO authApiUserInviteLogBO) {
        UserLogInvite userLogInvite = dao.getByInvitedUserNo(authApiUserInviteLogBO.getUserNo());
        if (!StringUtils.isEmpty(userLogInvite)) {
            UserExt userExt = userExtDao.getByUserNo(userLogInvite.getInviteUserNo());
            UserExtViewRESQ userExtViewRESQ = BeanUtil.copyProperties(userExt, UserExtViewRESQ.class);
            return Result.success(userExtViewRESQ);
        }
        return Result.success(null);
    }
}
