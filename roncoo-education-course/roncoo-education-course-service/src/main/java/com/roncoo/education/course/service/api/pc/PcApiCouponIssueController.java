package com.roncoo.education.course.service.api.pc;

import com.roncoo.education.course.service.biz.pc.PcApiCouponIssueBiz;
import com.roncoo.education.course.service.common.req.*;
import com.roncoo.education.course.service.common.resq.CouponIssuePageRESQ;
import com.roncoo.education.course.service.common.resq.CouponIssueViewRESQ;
import com.roncoo.education.util.base.BaseController;
import com.roncoo.education.util.base.Page;
import com.roncoo.education.util.base.Result;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * 优惠券前台领取表 前端控制器
 *
 * @author husend
 * @since 2020-05-22
 */
@RestController
@RequestMapping("/course/coupon/issue")
    public class PcApiCouponIssueController extends BaseController {

        @Autowired
        private PcApiCouponIssueBiz biz;

        /**
         * CouponIssue分页列表接口
         * @param couponIssuePageREQ   分页信息
         * @return
         */
        @ApiOperation(value = "couponIssue分页列表接口", notes = "couponIssue分页列表接口")
        @RequestMapping(value = "/list", method = RequestMethod.POST)
        public Result<Page<CouponIssuePageRESQ>> list(@RequestBody CouponIssuePageREQ couponIssuePageREQ) {
            return biz.list(couponIssuePageREQ);
        }

        /**
        * CouponIssue添加接口
        * @param couponIssueSaveREQ
        * @return
        */
        @ApiOperation(value = "couponIssue添加接口", notes = "couponIssue添加接口")
        @RequestMapping(value = "/save", method = RequestMethod.POST)
        public Result<Integer> save(@RequestBody CouponIssueSaveREQ couponIssueSaveREQ) {
            return biz.save(couponIssueSaveREQ);
        }

        /**
         * CouponIssue更新接口
         * @param couponIssueUpdateREQ
         * @return
         */
        @ApiOperation(value = "couponIssue更新接口", notes = "couponIssue更新接口")
        @RequestMapping(value = "/update", method = RequestMethod.POST)
        public Result<Integer> update(@RequestBody CouponIssueUpdateREQ couponIssueUpdateREQ) {
            return biz.update(couponIssueUpdateREQ);
        }

        /**
         * CouponIssue删除接口
         * @param couponIssueDeleteREQ
         * @return
         */
        @ApiOperation(value = "couponIssue删除接口", notes = "couponIssue删除接口")
        @RequestMapping(value = "/delete", method = RequestMethod.POST)
        public Result<Integer> delete(@RequestBody CouponIssueDeleteREQ couponIssueDeleteREQ) {
            return biz.delete(couponIssueDeleteREQ);
        }

        /**
         * CouponIssue查看接口
         * @param couponIssueViewREQ
         * @return
         */
        @ApiOperation(value = "couponIssue查看接口", notes = "couponIssue查看接口")
        @RequestMapping(value = "/view", method = RequestMethod.POST)
        public Result<CouponIssueViewRESQ> view(@RequestBody CouponIssueViewREQ couponIssueViewREQ) {
            return biz.view(couponIssueViewREQ);
        }


    }

