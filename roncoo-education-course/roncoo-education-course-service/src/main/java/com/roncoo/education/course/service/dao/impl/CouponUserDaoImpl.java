
package com.roncoo.education.course.service.dao.impl;

import com.roncoo.education.course.service.common.bo.auth.AuthCouponUserListBO;
import com.roncoo.education.course.service.dao.CouponUserDao;
import com.roncoo.education.course.service.dao.impl.mapper.CouponUserMapper;
import com.roncoo.education.course.service.dao.impl.mapper.entity.CouponUser;
import com.roncoo.education.course.service.dao.impl.mapper.entity.CouponUserExample;
import com.roncoo.education.util.base.Page;
import com.roncoo.education.util.base.PageUtil;
import com.roncoo.education.util.tools.IdWorker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 优惠券发放记录表 服务实现类
 *
 * @author husend
 * @since 2020-05-22
 */
@Repository
public class CouponUserDaoImpl implements CouponUserDao {

    @Autowired
    private CouponUserMapper couponUserMapper;

    @Override
    public int save(CouponUser record) {
    record.setId(IdWorker.getId());
    return this.couponUserMapper.insertSelective(record);
    }

    @Override
    public int deleteById(Long id) {
    return this.couponUserMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int updateById(CouponUser record) {
    return this.couponUserMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public CouponUser getById(Long id) {
    return this.couponUserMapper.selectByPrimaryKey(id);
    }

    @Override
    public Page<CouponUser> listForPage(int pageCurrent, int pageSize, CouponUserExample example) {
        int count = this.couponUserMapper.countByExample(example);
        pageSize = PageUtil.checkPageSize(pageSize);
        pageCurrent = PageUtil.checkPageCurrent(count, pageSize, pageCurrent);
        int totalPage = PageUtil.countTotalPage(count, pageSize);
        example.setLimitStart(PageUtil.countOffset(pageCurrent, pageSize));
        example.setPageSize(pageSize);
        return new Page<CouponUser>(count, totalPage, pageCurrent, pageSize, this.couponUserMapper.selectByExample(example));
    }

    @Override
    public List<CouponUser> getByUserNo(Long userNo) {
        CouponUserExample example = new CouponUserExample();
        example.createCriteria().andUidEqualTo(userNo).andStatusEqualTo(0).andIsFailEqualTo(1);
        List<CouponUser> couponUsers = this.couponUserMapper.selectByExample(example);
        return couponUsers;
    }

    @Override
    public int getMyCouponCount(AuthCouponUserListBO authCouponUserListBO) {
        CouponUserExample example = new CouponUserExample();
        example.createCriteria()
                .andUidEqualTo(authCouponUserListBO.getUserNo())
                .andStatusEqualTo(0)
                .andIsFailEqualTo(1);
        return this.couponUserMapper.countByExample(example);
    }
}
