
package com.roncoo.education.course.service.dao;

import com.roncoo.education.course.service.dao.impl.mapper.entity.Coupon;
import com.roncoo.education.course.service.dao.impl.mapper.entity.CouponExample;
import com.roncoo.education.util.base.Page;

/**
 * 优惠券表 服务类
 *
 * @author husend
 * @since 2020-05-22
 */
public interface CouponDao {
            int save(Coupon record);

            int deleteById(Long id);

            int updateById(Coupon record);

            Coupon getById(Long id);

            Page<Coupon> listForPage(int pageCurrent, int pageSize, CouponExample example);

            Coupon getByIsConvert(Integer yes);
}
