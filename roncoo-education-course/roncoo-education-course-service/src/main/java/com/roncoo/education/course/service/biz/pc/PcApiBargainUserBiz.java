package com.roncoo.education.course.service.biz.pc;

import com.roncoo.education.course.service.common.req.*;
import com.roncoo.education.course.service.common.resq.BargainUserPageRESQ;
import com.roncoo.education.course.service.common.resq.BargainUserViewRESQ;
import com.roncoo.education.course.service.dao.BargainUserDao;
import com.roncoo.education.util.base.Page;
import com.roncoo.education.util.base.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 广告信息
 *
 */
@Component
public class PcApiBargainUserBiz {

    @Autowired
    private BargainUserDao dao;

    public Result<Page<BargainUserPageRESQ>> list(BargainUserPageREQ req) {

        return null;
    }

    /**
     *
     * 添加BargainUser
     *
     * @param req
     * @return
     */
    public Result<Integer> save(BargainUserSaveREQ req) {

        return null;
    }

    /**
     * 更新BargainUser信息
     *
     * @param req
     * @return
     */
    public Result<Integer> update(BargainUserUpdateREQ req) {

        return null;
    }

    /**
     * 删除BargainUser信息
     *
     * @param req
     * @return
     */
    public Result<Integer> delete(BargainUserDeleteREQ req) {

        return null;
    }

    /**
     * BargainUser查看
     *
     * @param req
     * @return
     */
    public Result<BargainUserViewRESQ> view(BargainUserViewREQ req) {

        return null;
    }

}
