package com.roncoo.education.course.service.api.pc;

import com.roncoo.education.course.service.biz.pc.PcApiCouponIssueUserBiz;
import com.roncoo.education.course.service.common.req.*;
import com.roncoo.education.course.service.common.resq.CouponIssueUserPageRESQ;
import com.roncoo.education.course.service.common.resq.CouponIssueUserViewRESQ;
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
 * 优惠券前台用户领取记录表 前端控制器
 *
 * @author husend
 * @since 2020-05-22
 */
@RestController
@RequestMapping("/course/coupon/issue/user")
    public class PcApiCouponIssueUserController extends BaseController {

        @Autowired
        private PcApiCouponIssueUserBiz biz;

        /**
         * CouponIssueUser分页列表接口
         * @param couponIssueUserPageREQ   分页信息
         * @return
         */
        @ApiOperation(value = "couponIssueUser分页列表接口", notes = "couponIssueUser分页列表接口")
        @RequestMapping(value = "/list", method = RequestMethod.POST)
        public Result<Page<CouponIssueUserPageRESQ>> list(@RequestBody CouponIssueUserPageREQ couponIssueUserPageREQ) {
            return biz.list(couponIssueUserPageREQ);
        }

        /**
        * CouponIssueUser添加接口
        * @param couponIssueUserSaveREQ
        * @return
        */
        @ApiOperation(value = "couponIssueUser添加接口", notes = "couponIssueUser添加接口")
        @RequestMapping(value = "/save", method = RequestMethod.POST)
        public Result<Integer> save(@RequestBody CouponIssueUserSaveREQ couponIssueUserSaveREQ) {
            return biz.save(couponIssueUserSaveREQ);
        }

        /**
         * CouponIssueUser更新接口
         * @param couponIssueUserUpdateREQ
         * @return
         */
        @ApiOperation(value = "couponIssueUser更新接口", notes = "couponIssueUser更新接口")
        @RequestMapping(value = "/update", method = RequestMethod.POST)
        public Result<Integer> update(@RequestBody CouponIssueUserUpdateREQ couponIssueUserUpdateREQ) {
            return biz.update(couponIssueUserUpdateREQ);
        }

        /**
         * CouponIssueUser删除接口
         * @param couponIssueUserDeleteREQ
         * @return
         */
        @ApiOperation(value = "couponIssueUser删除接口", notes = "couponIssueUser删除接口")
        @RequestMapping(value = "/delete", method = RequestMethod.POST)
        public Result<Integer> delete(@RequestBody CouponIssueUserDeleteREQ couponIssueUserDeleteREQ) {
            return biz.delete(couponIssueUserDeleteREQ);
        }

        /**
         * CouponIssueUser查看接口
         * @param couponIssueUserViewREQ
         * @return
         */
        @ApiOperation(value = "couponIssueUser查看接口", notes = "couponIssueUser查看接口")
        @RequestMapping(value = "/view", method = RequestMethod.POST)
        public Result<CouponIssueUserViewRESQ> view(@RequestBody CouponIssueUserViewREQ couponIssueUserViewREQ) {
            return biz.view(couponIssueUserViewREQ);
        }


    }

