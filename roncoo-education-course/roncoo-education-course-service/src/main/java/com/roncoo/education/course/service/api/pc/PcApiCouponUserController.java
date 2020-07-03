package com.roncoo.education.course.service.api.pc;

import com.roncoo.education.course.service.biz.pc.PcApiCouponUserBiz;
import com.roncoo.education.course.service.common.req.*;
import com.roncoo.education.course.service.common.resq.CouponUserPageRESQ;
import com.roncoo.education.course.service.common.resq.CouponUserViewRESQ;
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
 * 优惠券发放记录表 前端控制器
 *
 * @author husend
 * @since 2020-05-22
 */
@RestController
@RequestMapping("/course/coupon/user")
    public class PcApiCouponUserController extends BaseController {

        @Autowired
        private PcApiCouponUserBiz biz;

        /**
         * CouponUser分页列表接口
         * @param couponUserPageREQ   分页信息
         * @return
         */
        @ApiOperation(value = "couponUser分页列表接口", notes = "couponUser分页列表接口")
        @RequestMapping(value = "/list", method = RequestMethod.POST)
        public Result<Page<CouponUserPageRESQ>> list(@RequestBody CouponUserPageREQ couponUserPageREQ) {
            return biz.list(couponUserPageREQ);
        }

        /**
        * CouponUser添加接口
        * @param couponUserSaveREQ
        * @return
        */
        @ApiOperation(value = "couponUser添加接口", notes = "couponUser添加接口")
        @RequestMapping(value = "/save", method = RequestMethod.POST)
        public Result<Integer> save(@RequestBody CouponUserSaveREQ couponUserSaveREQ) {
            return biz.save(couponUserSaveREQ);
        }

        /**
         * CouponUser更新接口
         * @param couponUserUpdateREQ
         * @return
         */
        @ApiOperation(value = "couponUser更新接口", notes = "couponUser更新接口")
        @RequestMapping(value = "/update", method = RequestMethod.POST)
        public Result<Integer> update(@RequestBody CouponUserUpdateREQ couponUserUpdateREQ) {
            return biz.update(couponUserUpdateREQ);
        }

        /**
         * CouponUser删除接口
         * @param couponUserDeleteREQ
         * @return
         */
        @ApiOperation(value = "couponUser删除接口", notes = "couponUser删除接口")
        @RequestMapping(value = "/delete", method = RequestMethod.POST)
        public Result<Integer> delete(@RequestBody CouponUserDeleteREQ couponUserDeleteREQ) {
            return biz.delete(couponUserDeleteREQ);
        }

        /**
         * CouponUser查看接口
         * @param couponUserViewREQ
         * @return
         */
        @ApiOperation(value = "couponUser查看接口", notes = "couponUser查看接口")
        @RequestMapping(value = "/view", method = RequestMethod.POST)
        public Result<CouponUserViewRESQ> view(@RequestBody CouponUserViewREQ couponUserViewREQ) {
            return biz.view(couponUserViewREQ);
        }


    }

