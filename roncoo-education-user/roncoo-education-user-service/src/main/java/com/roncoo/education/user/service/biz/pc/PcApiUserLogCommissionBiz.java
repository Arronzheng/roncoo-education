package com.roncoo.education.user.service.biz.pc;

import com.roncoo.education.user.service.common.req.*;
import com.roncoo.education.user.service.common.resq.UserLogCommissionPageRESQ;
import com.roncoo.education.user.service.common.resq.UserLogCommissionViewRESQ;
import com.roncoo.education.user.service.dao.UserLogCommissionDao;
import com.roncoo.education.util.base.Page;
import com.roncoo.education.util.base.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 广告信息
 *
 */
@Component
public class PcApiUserLogCommissionBiz {

    @Autowired
    private UserLogCommissionDao dao;

    public Result<Page<UserLogCommissionPageRESQ>> list(UserLogCommissionPageREQ req) {

        return null;
    }

    /**
     *
     * 添加UserLogCommission
     *
     * @param req
     * @return
     */
    public Result<Integer> save(UserLogCommissionSaveREQ req) {

        return null;
    }

    /**
     * 更新UserLogCommission信息
     *
     * @param req
     * @return
     */
    public Result<Integer> update(UserLogCommissionUpdateREQ req) {

        return null;
    }

    /**
     * 删除UserLogCommission信息
     *
     * @param req
     * @return
     */
    public Result<Integer> delete(UserLogCommissionDeleteREQ req) {

        return null;
    }

    /**
     * UserLogCommission查看
     *
     * @param req
     * @return
     */
    public Result<UserLogCommissionViewRESQ> view(UserLogCommissionViewREQ req) {

        return null;
    }

}
