
package com.roncoo.education.course.service.dao;

import com.roncoo.education.course.service.common.bo.auth.AuthCouponUserListBO;
import com.roncoo.education.course.service.dao.impl.mapper.entity.CouponUser;
import com.roncoo.education.course.service.dao.impl.mapper.entity.CouponUserExample;
import com.roncoo.education.util.base.Page;

import java.util.List;

/**
 * 优惠券发放记录表 服务类
 *
 * @author husend
 * @since 2020-05-22
 */
public interface CouponUserDao {
            int save(CouponUser record);

            int deleteById(Long id);

            int updateById(CouponUser record);

            CouponUser getById(Long id);

            Page<CouponUser> listForPage(int pageCurrent, int pageSize, CouponUserExample example);

            List<CouponUser> getByUserNo(Long userNo);

            int getMyCouponCount(AuthCouponUserListBO authCouponUserListBO);
}
