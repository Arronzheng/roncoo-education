package com.roncoo.education.course.service.api.auth;

import com.roncoo.education.course.service.biz.auth.AuthApiCouponBiz;
import com.roncoo.education.course.service.biz.auth.AuthApiCouponUserBiz;
import com.roncoo.education.course.service.common.bo.auth.AuthCouponConvertBO;
import com.roncoo.education.course.service.common.bo.auth.AuthCouponUserListBO;
import com.roncoo.education.course.service.common.dto.auth.AuthCouponUserListDTO;
import com.roncoo.education.util.base.BaseController;
import com.roncoo.education.util.base.Result;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description:
 * @author: husend
 * @time: 2020/5/23
 */
@RestController
@RequestMapping("/course/auth/coupon/user")
public class AuthApiCouponUserController extends BaseController {

    @Autowired
    private AuthApiCouponUserBiz biz;

    @ApiOperation(value = "我的优惠券", notes = "我的优惠券")
    @PostMapping("/getMyCoupon")
    public Result<AuthCouponUserListDTO> getMyCoupon(@RequestBody AuthCouponUserListBO authCouponUserListBO) {
        return biz.getMyCoupon(authCouponUserListBO);
    }

    @ApiOperation(value = "统计我的优惠券", notes = "统计我的优惠券")
    @PostMapping("/getMyCouponCount")
    public Result<Integer> getMyCouponCount(@RequestBody AuthCouponUserListBO authCouponUserListBO) {
        return biz.getMyCouponCount(authCouponUserListBO);
    }
}
