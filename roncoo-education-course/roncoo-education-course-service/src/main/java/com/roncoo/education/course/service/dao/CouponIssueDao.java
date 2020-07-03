
package com.roncoo.education.course.service.dao;

import com.roncoo.education.course.service.dao.impl.mapper.entity.CouponIssue;
import com.roncoo.education.course.service.dao.impl.mapper.entity.CouponIssueExample;
import com.roncoo.education.util.base.Page;

/**
 * 优惠券前台领取表 服务类
 *
 * @author husend
 * @since 2020-05-22
 */
public interface CouponIssueDao {
            int save(CouponIssue record);

            int deleteById(Long id);

            int updateById(CouponIssue record);

            CouponIssue getById(Long id);

            Page<CouponIssue> listForPage(int pageCurrent, int pageSize, CouponIssueExample example);

        }
