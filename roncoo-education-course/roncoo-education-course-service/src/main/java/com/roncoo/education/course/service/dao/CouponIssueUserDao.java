
package com.roncoo.education.course.service.dao;

import com.roncoo.education.course.service.dao.impl.mapper.entity.CouponIssueUser;
import com.roncoo.education.course.service.dao.impl.mapper.entity.CouponIssueUserExample;
import com.roncoo.education.util.base.Page;

/**
 * 优惠券前台用户领取记录表 服务类
 *
 * @author husend
 * @since 2020-05-22
 */
public interface CouponIssueUserDao {
            int save(CouponIssueUser record);

            int deleteById(Long id);

            int updateById(CouponIssueUser record);

            CouponIssueUser getById(Long id);

            Page<CouponIssueUser> listForPage(int pageCurrent, int pageSize, CouponIssueUserExample example);

        }
