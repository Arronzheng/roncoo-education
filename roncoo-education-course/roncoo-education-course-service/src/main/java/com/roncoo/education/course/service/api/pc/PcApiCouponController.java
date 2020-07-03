package com.roncoo.education.course.service.api.pc;

import com.roncoo.education.course.service.biz.pc.PcApiCouponBiz;
import com.roncoo.education.course.service.common.req.*;
import com.roncoo.education.course.service.common.resq.CouponPageRESQ;
import com.roncoo.education.course.service.common.resq.CouponViewRESQ;
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
 * 优惠券表 前端控制器
 *
 * @author husend
 * @since 2020-05-22
 */
@RestController
@RequestMapping("/course/pc/coupon")
    public class PcApiCouponController extends BaseController {

        @Autowired
        private PcApiCouponBiz biz;

        /**
         * Coupon分页列表接口
         * @param couponPageREQ   分页信息
         * @return
         */
        @ApiOperation(value = "coupon分页列表接口", notes = "coupon分页列表接口")
        @RequestMapping(value = "/list", method = RequestMethod.POST)
        public Result<Page<CouponPageRESQ>> list(@RequestBody CouponPageREQ couponPageREQ) {
            return biz.list(couponPageREQ);
        }

        /**
        * Coupon添加接口
        * @param couponSaveREQ
        * @return
        */
        @ApiOperation(value = "coupon添加接口", notes = "coupon添加接口")
        @RequestMapping(value = "/save", method = RequestMethod.POST)
        public Result<Integer> save(@RequestBody CouponSaveREQ couponSaveREQ) {
            return biz.save(couponSaveREQ);
        }

        /**
         * Coupon更新接口
         * @param couponUpdateREQ
         * @return
         */
        @ApiOperation(value = "coupon更新接口", notes = "coupon更新接口")
        @RequestMapping(value = "/update", method = RequestMethod.POST)
        public Result<Integer> update(@RequestBody CouponUpdateREQ couponUpdateREQ) {
            return biz.update(couponUpdateREQ);
        }

        /**
         * Coupon删除接口
         * @param couponDeleteREQ
         * @return
         */
        @ApiOperation(value = "coupon删除接口", notes = "coupon删除接口")
        @RequestMapping(value = "/delete", method = RequestMethod.POST)
        public Result<Integer> delete(@RequestBody CouponDeleteREQ couponDeleteREQ) {
            return biz.delete(couponDeleteREQ);
        }

        /**
         * Coupon查看接口
         * @param couponViewREQ
         * @return
         */
        @ApiOperation(value = "coupon查看接口", notes = "coupon查看接口")
        @RequestMapping(value = "/view", method = RequestMethod.POST)
        public Result<CouponViewRESQ> view(@RequestBody CouponViewREQ couponViewREQ) {
            return biz.view(couponViewREQ);
        }


    }

