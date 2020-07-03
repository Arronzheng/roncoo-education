package com.roncoo.education.course.service.biz.auth;

import com.roncoo.education.course.service.common.bo.auth.AuthCouponConvertBO;
import com.roncoo.education.course.service.common.bo.auth.AuthCouponUserListBO;
import com.roncoo.education.course.service.common.dto.auth.AuthCouponUserListDTO;
import com.roncoo.education.course.service.common.dto.auth.AuthCouponUserViewDTO;
import com.roncoo.education.course.service.dao.CouponDao;
import com.roncoo.education.course.service.dao.CouponUserDao;
import com.roncoo.education.course.service.dao.impl.mapper.entity.Coupon;
import com.roncoo.education.course.service.dao.impl.mapper.entity.CouponUser;
import com.roncoo.education.user.common.bean.vo.UserExtVO;
import com.roncoo.education.user.feign.IBossUserExt;
import com.roncoo.education.util.base.BaseBiz;
import com.roncoo.education.util.base.Result;
import com.roncoo.education.util.enums.ConvertEnum;
import com.roncoo.education.util.enums.ResultEnum;
import com.roncoo.education.util.tools.BeanUtil;
import com.roncoo.education.util.tools.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.time.ZoneId;
import java.util.Date;
import java.util.List;

/**
 * @description:
 * @author: husend
 * @time: 2020/5/25
 */
@Component
public class AuthApiCouponUserBiz extends BaseBiz {

    @Autowired
    private CouponUserDao dao;
    @Autowired
    private IBossUserExt bossUserExt;

    public Result<AuthCouponUserListDTO> getMyCoupon(AuthCouponUserListBO authCouponUserListBO) {
        List<CouponUser> couponUsers = dao.getByUserNo(authCouponUserListBO.getUserNo());
        AuthCouponUserListDTO authCouponUserListDTO = new AuthCouponUserListDTO();
        List<AuthCouponUserViewDTO> authCouponUserViewDTOS = BeanUtil.copyProperties(couponUsers, AuthCouponUserViewDTO.class);
        authCouponUserListDTO.setAuthCouponUserViewDTOs(authCouponUserViewDTOS);
        return Result.success(authCouponUserListDTO);
    }

    public Result<Integer> getMyCouponCount(AuthCouponUserListBO authCouponUserListBO) {
        return Result.success(dao.getMyCouponCount(authCouponUserListBO));
    }
}
