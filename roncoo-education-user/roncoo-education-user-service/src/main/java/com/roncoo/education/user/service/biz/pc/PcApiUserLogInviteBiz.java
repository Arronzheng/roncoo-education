package com.roncoo.education.user.service.biz.pc;

import com.roncoo.education.user.service.common.req.*;
import com.roncoo.education.user.service.common.resq.UserLogInvitePageRESQ;
import com.roncoo.education.user.service.common.resq.UserLogInviteViewRESQ;
import com.roncoo.education.user.service.dao.UserLogInviteDao;
import com.roncoo.education.util.base.Page;
import com.roncoo.education.util.base.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 广告信息
 *
 */
@Component
public class PcApiUserLogInviteBiz {

    @Autowired
    private UserLogInviteDao dao;

    public Result<Page<UserLogInvitePageRESQ>> list(UserLogInvitePageREQ req) {

        return null;
    }

    /**
     *
     * 添加UserLogInvite
     *
     * @param req
     * @return
     */
    public Result<Integer> save(UserLogInviteSaveREQ req) {

        return null;
    }

    /**
     * 更新UserLogInvite信息
     *
     * @param req
     * @return
     */
    public Result<Integer> update(UserLogInviteUpdateREQ req) {

        return null;
    }

    /**
     * 删除UserLogInvite信息
     *
     * @param req
     * @return
     */
    public Result<Integer> delete(UserLogInviteDeleteREQ req) {

        return null;
    }

    /**
     * UserLogInvite查看
     *
     * @param req
     * @return
     */
    public Result<UserLogInviteViewRESQ> view(UserLogInviteViewREQ req) {

        return null;
    }

}
