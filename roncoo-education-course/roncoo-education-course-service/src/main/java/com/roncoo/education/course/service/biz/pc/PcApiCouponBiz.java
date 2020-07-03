package com.roncoo.education.course.service.biz.pc;

import com.roncoo.education.course.service.common.req.*;
import com.roncoo.education.course.service.common.resq.CouponPageRESQ;
import com.roncoo.education.course.service.common.resq.CouponViewRESQ;
import com.roncoo.education.course.service.dao.CouponDao;
import com.roncoo.education.course.service.dao.impl.mapper.entity.Coupon;
import com.roncoo.education.course.service.dao.impl.mapper.entity.CouponExample;
import com.roncoo.education.util.base.Page;
import com.roncoo.education.util.base.PageUtil;
import com.roncoo.education.util.base.Result;
import com.roncoo.education.util.enums.ResultEnum;
import com.roncoo.education.util.tools.BeanUtil;
import com.xiaoleilu.hutool.util.ObjectUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.util.Date;

/**
 * 广告信息
 *
 */
@Component
public class PcApiCouponBiz {

    @Autowired
    private CouponDao dao;

    public Result<Page<CouponPageRESQ>> list(CouponPageREQ req) {
        CouponExample example = new CouponExample();
        CouponExample.Criteria c = example.createCriteria();
        if (!StringUtils.isEmpty(req.getTitle())) {
            c.andTitleLike(PageUtil.like(req.getTitle()));
        }
        if (req.getStatus() != null) {
            c.andStatusEqualTo(req.getStatus());
        }
        example.setOrderByClause(" status desc, id desc ");
        Page<Coupon> page = dao.listForPage(req.getPageCurrent(), req.getPageSize(), example);
        Page<CouponPageRESQ> listForPage = PageUtil.transform(page, CouponPageRESQ.class);
        return Result.success(listForPage);
    }

    /**
     *
     * 添加Coupon
     *
     * @param req
     * @return
     */
    @Transactional
    public Result<Integer> save(CouponSaveREQ req) {
        Coupon record = BeanUtil.copyProperties(req, Coupon.class);
        record.setAddTime(LocalDateTime.now());
        record.setStatus(1);
        int results = dao.save(record);
        if (results > 0) {
            return Result.success(results);
        }
        return Result.error(ResultEnum.ERROR);
    }

    /**
     * 更新Coupon信息
     *
     * @param req
     * @return
     */
    @Transactional
    public Result<Integer> update(CouponUpdateREQ req) {
        if (StringUtils.isEmpty(req.getId())) {
            return Result.error("ID不能为空");
        }
        Coupon coupon = BeanUtil.copyProperties(req, Coupon.class);
        int results = dao.updateById(coupon);
        if (results > 0) {
            return Result.success(results);
        }
        return Result.error(ResultEnum.ERROR);
    }

    /**
     * 删除Coupon信息
     *
     * @param req
     * @return
     */
    public Result<Integer> delete(CouponDeleteREQ req) {
        if (StringUtils.isEmpty(req.getId())) {
            return Result.error("ID不能为空");
        }
        int results = dao.deleteById(req.getId());
        if (results > 0) {
            return Result.success(results);
        }
        return Result.error(ResultEnum.ERROR);
    }

    /**
     * Coupon查看
     *
     * @param req
     * @return
     */
    public Result<CouponViewRESQ> view(CouponViewREQ req) {
        if (StringUtils.isEmpty(req.getId())) {
            return Result.error("ID不能为空");
        }
        Coupon record = dao.getById(req.getId());
        return Result.success(BeanUtil.copyProperties(record, CouponViewRESQ.class));
    }

}
