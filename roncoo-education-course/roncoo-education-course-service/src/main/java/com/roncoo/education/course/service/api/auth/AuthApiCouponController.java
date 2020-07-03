package com.roncoo.education.course.service.api.auth;

import com.roncoo.education.course.service.biz.auth.AuthApiCouponBiz;
import com.roncoo.education.course.service.common.bo.auth.AuthCouponConvertBO;
import com.roncoo.education.util.base.BaseController;
import com.roncoo.education.util.base.Result;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @description:
 * @author: husend
 * @time: 2020/5/23
 */
@RestController
@RequestMapping("/course/auth/coupon")
public class AuthApiCouponController extends BaseController {

    @Autowired
    private AuthApiCouponBiz biz;

    @ApiOperation(value = "兑换优惠券", notes = "兑换优惠券")
    @PostMapping("/convert")
    public Result<Integer> convert(@RequestBody AuthCouponConvertBO authCouponConvertBO) {
        return biz.convert(authCouponConvertBO);
    }
}
