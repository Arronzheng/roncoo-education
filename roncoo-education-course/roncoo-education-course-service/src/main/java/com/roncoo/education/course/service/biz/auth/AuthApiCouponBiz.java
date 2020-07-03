package com.roncoo.education.course.service.biz.auth;

import com.roncoo.education.course.service.common.bo.auth.AuthCouponConvertBO;
import com.roncoo.education.course.service.dao.CouponDao;
import com.roncoo.education.course.service.dao.CouponUserDao;
import com.roncoo.education.course.service.dao.impl.mapper.entity.Coupon;
import com.roncoo.education.course.service.dao.impl.mapper.entity.CouponUser;
import com.roncoo.education.user.common.bean.qo.UserExtInviteQO;
import com.roncoo.education.user.common.bean.qo.UserLogInviteSaveQO;
import com.roncoo.education.user.common.bean.vo.UserExtVO;
import com.roncoo.education.user.feign.IBossUserExt;
import com.roncoo.education.user.feign.IBossUserLogInvite;
import com.roncoo.education.util.base.BaseBiz;
import com.roncoo.education.util.base.Result;
import com.roncoo.education.util.enums.ConvertEnum;
import com.roncoo.education.util.enums.ResultEnum;
import com.roncoo.education.util.tools.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

/**
 * @description:
 * @author: husend
 * @time: 2020/5/25
 */
@Component
public class AuthApiCouponBiz extends BaseBiz {

    @Autowired
    private CouponDao dao;
    @Autowired
    private IBossUserExt bossUserExt;
    @Autowired
    private CouponUserDao couponUserDao;
    @Autowired
    private IBossUserLogInvite bossUserLogInvite;


    @Transactional
    public Result<Integer> convert(AuthCouponConvertBO authCouponConvertBO) {
        UserExtInviteQO userExtInviteQO = new UserExtInviteQO();
        userExtInviteQO.setInviteCode(authCouponConvertBO.getCode());
        UserExtVO userExtVO = bossUserExt.getByInviteCode(userExtInviteQO);
        if (StringUtils.isEmpty(userExtVO)) {
            return Result.error(ResultEnum.ERROR);
        }
        // 兑换成功，用户名下添加通用优惠券
        Coupon coupon = dao.getByIsConvert(ConvertEnum.YES.getCode());
        CouponUser couponUser = new CouponUser();
        couponUser.setCid(coupon.getId());
        couponUser.setUid(authCouponConvertBO.getUserNo());
        couponUser.setCouponTitle(coupon.getTitle());
        couponUser.setCouponPrice(coupon.getCouponPrice());
        couponUser.setUseMinPrice(coupon.getUseMinPrice());
        couponUser.setAddTime(coupon.getAddTime());
        couponUser.setEndTime(DateUtil.addDateToLocalDate(Date.from( coupon.getAddTime().atZone( ZoneId.systemDefault()).toInstant()), coupon.getCouponTime()));
        couponUser.setStatus(0);
        couponUser.setIsFail(1);
        couponUserDao.save(couponUser);

        CouponUser cUser = new CouponUser();
        cUser.setCid(coupon.getId());
        cUser.setUid(userExtVO.getUserNo());
        cUser.setCouponTitle(coupon.getTitle());
        cUser.setCouponPrice(coupon.getCouponPrice());
        cUser.setUseMinPrice(coupon.getUseMinPrice());
        cUser.setAddTime(coupon.getAddTime());
        cUser.setEndTime(DateUtil.addDateToLocalDate(Date.from( coupon.getAddTime().atZone( ZoneId.systemDefault()).toInstant()), coupon.getCouponTime()));
        cUser.setStatus(0);
        cUser.setIsFail(1);
        couponUserDao.save(cUser);

        UserLogInviteSaveQO userLogInviteSaveQO = new UserLogInviteSaveQO();
        userLogInviteSaveQO.setAddTime(LocalDateTime.now());
        userLogInviteSaveQO.setStatus(2);
        userLogInviteSaveQO.setInviteUserNo(userExtVO.getUserNo());
        userLogInviteSaveQO.setInvitedUserNo(authCouponConvertBO.getUserNo());
        bossUserLogInvite.save(userLogInviteSaveQO);
        return Result.success(ResultEnum.SUCCESS.getCode());
    }
}
