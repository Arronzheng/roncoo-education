package com.roncoo.education.course.service.biz.pc;

import com.roncoo.education.course.service.common.req.*;
import com.roncoo.education.course.service.common.resq.CouponIssuePageRESQ;
import com.roncoo.education.course.service.common.resq.CouponIssueViewRESQ;
import com.roncoo.education.course.service.dao.CouponIssueDao;
import com.roncoo.education.util.base.Page;
import com.roncoo.education.util.base.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 广告信息
 *
 */
@Component
public class PcApiCouponIssueBiz {

    @Autowired
    private CouponIssueDao dao;

    public Result<Page<CouponIssuePageRESQ>> list(CouponIssuePageREQ req) {

        return null;
    }

    /**
     *
     * 添加CouponIssue
     *
     * @param req
     * @return
     */
    public Result<Integer> save(CouponIssueSaveREQ req) {

        return null;
    }

    /**
     * 更新CouponIssue信息
     *
     * @param req
     * @return
     */
    public Result<Integer> update(CouponIssueUpdateREQ req) {

        return null;
    }

    /**
     * 删除CouponIssue信息
     *
     * @param req
     * @return
     */
    public Result<Integer> delete(CouponIssueDeleteREQ req) {

        return null;
    }

    /**
     * CouponIssue查看
     *
     * @param req
     * @return
     */
    public Result<CouponIssueViewRESQ> view(CouponIssueViewREQ req) {

        return null;
    }

}
