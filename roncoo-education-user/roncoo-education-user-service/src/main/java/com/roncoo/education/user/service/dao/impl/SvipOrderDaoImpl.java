
package com.roncoo.education.user.service.dao.impl;

import com.roncoo.education.user.service.dao.SvipOrderDao;
import com.roncoo.education.user.service.dao.impl.mapper.SvipOrderMapper;
import com.roncoo.education.user.service.dao.impl.mapper.entity.SvipOrder;
import com.roncoo.education.user.service.dao.impl.mapper.entity.SvipOrderExample;
import com.roncoo.education.util.base.Page;
import com.roncoo.education.util.base.PageUtil;
import com.roncoo.education.util.enums.OrderStatusEnum;
import com.roncoo.education.util.tools.IdWorker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 订单信息表 服务实现类
 *
 * @author ${author}
 * @since 2019-12-23
 */
@Repository
public class SvipOrderDaoImpl implements SvipOrderDao {

    @Autowired
    private SvipOrderMapper svipOrderMapper;

    @Override
    public int save(SvipOrder record) {
    record.setId(IdWorker.getId());
    return this.svipOrderMapper.insertSelective(record);
    }

    @Override
    public int deleteById(Long id) {
    return this.svipOrderMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int updateById(SvipOrder record) {
    return this.svipOrderMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public SvipOrder getById(Long id) {
    return this.svipOrderMapper.selectByPrimaryKey(id);
    }

    @Override
    public Page<SvipOrder> listForPage(int pageCurrent, int pageSize, SvipOrderExample example) {
        int count = this.svipOrderMapper.countByExample(example);
        pageSize = PageUtil.checkPageSize(pageSize);
        pageCurrent = PageUtil.checkPageCurrent(count, pageSize, pageCurrent);
        int totalPage = PageUtil.countTotalPage(count, pageSize);
        example.setLimitStart(PageUtil.countOffset(pageCurrent, pageSize));
        example.setPageSize(pageSize);
        return new Page<SvipOrder>(count, totalPage, pageCurrent, pageSize, this.svipOrderMapper.selectByExample(example));
    }

    @Override
    public SvipOrder getByUserNoAndOrderStatus(Long userNo) {
        SvipOrderExample example = new SvipOrderExample();
        example.createCriteria().andUserNoEqualTo(userNo).andOrderStatusEqualTo(OrderStatusEnum.WAIT.getCode());
        example.setOrderByClause(" id desc ");
        List<SvipOrder> list = this.svipOrderMapper.selectByExample(example);
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }

    @Override
    public SvipOrder getByOrderNo(Long orderNo) {
        SvipOrderExample example = new SvipOrderExample();
        example.createCriteria().andOrderNoEqualTo(orderNo);
        example.setOrderByClause(" id desc ");
        List<SvipOrder> list = this.svipOrderMapper.selectByExample(example);
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }

    @Override
    public SvipOrder getBySerialNo(Long serialNo) {
        SvipOrderExample example = new SvipOrderExample();
        example.createCriteria().andSerialNoEqualTo(serialNo);
        example.setOrderByClause(" id desc ");
        List<SvipOrder> list = this.svipOrderMapper.selectByExample(example);
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }
}
