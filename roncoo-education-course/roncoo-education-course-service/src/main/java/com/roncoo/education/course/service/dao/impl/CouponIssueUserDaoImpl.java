
package com.roncoo.education.course.service.dao.impl;

import com.roncoo.education.course.service.dao.CouponIssueUserDao;
import com.roncoo.education.course.service.dao.impl.mapper.CouponIssueUserMapper;
import com.roncoo.education.course.service.dao.impl.mapper.entity.CouponIssueUser;
import com.roncoo.education.course.service.dao.impl.mapper.entity.CouponIssueUserExample;
import com.roncoo.education.util.base.Page;
import com.roncoo.education.util.base.PageUtil;
import com.roncoo.education.util.tools.IdWorker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * 优惠券前台用户领取记录表 服务实现类
 *
 * @author husend
 * @since 2020-05-22
 */
@Repository
public class CouponIssueUserDaoImpl implements CouponIssueUserDao {

    @Autowired
    private CouponIssueUserMapper couponIssueUserMapper;

    @Override
    public int save(CouponIssueUser record) {
//    record.setId(IdWorker.getId());
    return this.couponIssueUserMapper.insertSelective(record);
    }

    @Override
    public int deleteById(Long id) {
    return this.couponIssueUserMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int updateById(CouponIssueUser record) {
    return this.couponIssueUserMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public CouponIssueUser getById(Long id) {
    return this.couponIssueUserMapper.selectByPrimaryKey(id);
    }

    @Override
    public Page<CouponIssueUser> listForPage(int pageCurrent, int pageSize, CouponIssueUserExample example) {
        int count = this.couponIssueUserMapper.countByExample(example);
        pageSize = PageUtil.checkPageSize(pageSize);
        pageCurrent = PageUtil.checkPageCurrent(count, pageSize, pageCurrent);
        int totalPage = PageUtil.countTotalPage(count, pageSize);
        example.setLimitStart(PageUtil.countOffset(pageCurrent, pageSize));
        example.setPageSize(pageSize);
        return new Page<CouponIssueUser>(count, totalPage, pageCurrent, pageSize, this.couponIssueUserMapper.selectByExample(example));
    }
}
