package com.roncoo.education.course.service.biz.pc;

import com.roncoo.education.course.service.common.req.*;
import com.roncoo.education.course.service.common.resq.CouponIssueUserPageRESQ;
import com.roncoo.education.course.service.common.resq.CouponIssueUserViewRESQ;
import com.roncoo.education.course.service.dao.CouponIssueUserDao;
import com.roncoo.education.util.base.Page;
import com.roncoo.education.util.base.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 广告信息
 *
 */
@Component
public class PcApiCouponIssueUserBiz {

    @Autowired
    private CouponIssueUserDao dao;

    public Result<Page<CouponIssueUserPageRESQ>> list(CouponIssueUserPageREQ req) {

        return null;
    }

    /**
     *
     * 添加CouponIssueUser
     *
     * @param req
     * @return
     */
    public Result<Integer> save(CouponIssueUserSaveREQ req) {

        return null;
    }

    /**
     * 更新CouponIssueUser信息
     *
     * @param req
     * @return
     */
    public Result<Integer> update(CouponIssueUserUpdateREQ req) {

        return null;
    }

    /**
     * 删除CouponIssueUser信息
     *
     * @param req
     * @return
     */
    public Result<Integer> delete(CouponIssueUserDeleteREQ req) {

        return null;
    }

    /**
     * CouponIssueUser查看
     *
     * @param req
     * @return
     */
    public Result<CouponIssueUserViewRESQ> view(CouponIssueUserViewREQ req) {

        return null;
    }

}
