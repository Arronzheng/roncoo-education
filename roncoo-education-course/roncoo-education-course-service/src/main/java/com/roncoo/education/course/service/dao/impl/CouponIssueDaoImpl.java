
package com.roncoo.education.course.service.dao.impl;

import com.roncoo.education.course.service.dao.CouponIssueDao;
import com.roncoo.education.course.service.dao.impl.mapper.CouponIssueMapper;
import com.roncoo.education.course.service.dao.impl.mapper.entity.CouponIssue;
import com.roncoo.education.course.service.dao.impl.mapper.entity.CouponIssueExample;
import com.roncoo.education.util.base.Page;
import com.roncoo.education.util.base.PageUtil;
import com.roncoo.education.util.tools.IdWorker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * 优惠券前台领取表 服务实现类
 *
 * @author husend
 * @since 2020-05-22
 */
@Repository
public class CouponIssueDaoImpl implements CouponIssueDao {

    @Autowired
    private CouponIssueMapper couponIssueMapper;

    @Override
    public int save(CouponIssue record) {
    record.setId(IdWorker.getId());
    return this.couponIssueMapper.insertSelective(record);
    }

    @Override
    public int deleteById(Long id) {
    return this.couponIssueMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int updateById(CouponIssue record) {
    return this.couponIssueMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public CouponIssue getById(Long id) {
    return this.couponIssueMapper.selectByPrimaryKey(id);
    }

    @Override
    public Page<CouponIssue> listForPage(int pageCurrent, int pageSize, CouponIssueExample example) {
        int count = this.couponIssueMapper.countByExample(example);
        pageSize = PageUtil.checkPageSize(pageSize);
        pageCurrent = PageUtil.checkPageCurrent(count, pageSize, pageCurrent);
        int totalPage = PageUtil.countTotalPage(count, pageSize);
        example.setLimitStart(PageUtil.countOffset(pageCurrent, pageSize));
        example.setPageSize(pageSize);
        return new Page<CouponIssue>(count, totalPage, pageCurrent, pageSize, this.couponIssueMapper.selectByExample(example));
    }
}
