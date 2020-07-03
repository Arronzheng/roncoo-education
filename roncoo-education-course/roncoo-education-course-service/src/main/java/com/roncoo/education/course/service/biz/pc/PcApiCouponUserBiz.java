package com.roncoo.education.course.service.biz.pc;

import com.roncoo.education.course.service.common.req.*;
import com.roncoo.education.course.service.common.resq.CouponUserPageRESQ;
import com.roncoo.education.course.service.common.resq.CouponUserViewRESQ;
import com.roncoo.education.course.service.dao.CouponUserDao;
import com.roncoo.education.util.base.Page;
import com.roncoo.education.util.base.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 广告信息
 *
 */
@Component
public class PcApiCouponUserBiz {

    @Autowired
    private CouponUserDao dao;

    public Result<Page<CouponUserPageRESQ>> list(CouponUserPageREQ req) {

        return null;
    }

    /**
     *
     * 添加CouponUser
     *
     * @param req
     * @return
     */
    public Result<Integer> save(CouponUserSaveREQ req) {

        return null;
    }

    /**
     * 更新CouponUser信息
     *
     * @param req
     * @return
     */
    public Result<Integer> update(CouponUserUpdateREQ req) {

        return null;
    }

    /**
     * 删除CouponUser信息
     *
     * @param req
     * @return
     */
    public Result<Integer> delete(CouponUserDeleteREQ req) {

        return null;
    }

    /**
     * CouponUser查看
     *
     * @param req
     * @return
     */
    public Result<CouponUserViewRESQ> view(CouponUserViewREQ req) {

        return null;
    }

}
