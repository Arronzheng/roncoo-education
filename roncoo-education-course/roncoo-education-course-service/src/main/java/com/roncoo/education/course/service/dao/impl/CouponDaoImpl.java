
package com.roncoo.education.course.service.dao.impl;

import com.roncoo.education.course.service.dao.CouponDao;
import com.roncoo.education.course.service.dao.impl.mapper.CouponMapper;
import com.roncoo.education.course.service.dao.impl.mapper.entity.Coupon;
import com.roncoo.education.course.service.dao.impl.mapper.entity.CouponExample;
import com.roncoo.education.util.base.Page;
import com.roncoo.education.util.base.PageUtil;
import com.roncoo.education.util.tools.IdWorker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 优惠券表 服务实现类
 *
 * @author husend
 * @since 2020-05-22
 */
@Repository
public class CouponDaoImpl implements CouponDao {

    @Autowired
    private CouponMapper couponMapper;

    @Override
    public int save(Coupon record) {
    record.setId(IdWorker.getId());
    return this.couponMapper.insertSelective(record);
    }

    @Override
    public int deleteById(Long id) {
    return this.couponMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int updateById(Coupon record) {
    return this.couponMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public Coupon getById(Long id) {
    return this.couponMapper.selectByPrimaryKey(id);
    }

    @Override
    public Page<Coupon> listForPage(int pageCurrent, int pageSize, CouponExample example) {
        int count = this.couponMapper.countByExample(example);
        pageSize = PageUtil.checkPageSize(pageSize);
        pageCurrent = PageUtil.checkPageCurrent(count, pageSize, pageCurrent);
        int totalPage = PageUtil.countTotalPage(count, pageSize);
        example.setLimitStart(PageUtil.countOffset(pageCurrent, pageSize));
        example.setPageSize(pageSize);
        return new Page<Coupon>(count, totalPage, pageCurrent, pageSize, this.couponMapper.selectByExample(example));
    }

    @Override
    public Coupon getByIsConvert(Integer yes) {
        CouponExample example = new CouponExample();
        example.createCriteria().andIsConvertEqualTo(yes).andStatusEqualTo(1);
        List<Coupon> couponList = this.couponMapper.selectByExample(example);
        if (couponList.isEmpty() || couponList.size() == 0) {
            return null;
        }
        return couponList.get(0);
    }
}
