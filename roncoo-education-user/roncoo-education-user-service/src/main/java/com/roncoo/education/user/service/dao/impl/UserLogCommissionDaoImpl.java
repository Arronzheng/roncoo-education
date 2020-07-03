
package com.roncoo.education.user.service.dao.impl;

import com.roncoo.education.user.service.dao.UserLogCommissionDao;
import com.roncoo.education.user.service.dao.impl.mapper.UserLogCommissionMapper;
import com.roncoo.education.user.service.dao.impl.mapper.entity.UserLogCommission;
import com.roncoo.education.user.service.dao.impl.mapper.entity.UserLogCommissionExample;
import com.roncoo.education.util.base.Page;
import com.roncoo.education.util.base.PageUtil;
import com.roncoo.education.util.tools.IdWorker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 用户佣金记录表 服务实现类
 *
 * @author husend
 * @since 2020-06-11
 */
@Repository
public class UserLogCommissionDaoImpl implements UserLogCommissionDao {

    @Autowired
    private UserLogCommissionMapper userLogCommissionMapper;

    @Override
    public int save(UserLogCommission record) {
    record.setId(IdWorker.getId());
    return this.userLogCommissionMapper.insertSelective(record);
    }

    @Override
    public int deleteById(Long id) {
    return this.userLogCommissionMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int updateById(UserLogCommission record) {
    return this.userLogCommissionMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public UserLogCommission getById(Long id) {
    return this.userLogCommissionMapper.selectByPrimaryKey(id);
    }

    @Override
    public Page<UserLogCommission> listForPage(int pageCurrent, int pageSize, UserLogCommissionExample example) {
        int count = this.userLogCommissionMapper.countByExample(example);
        pageSize = PageUtil.checkPageSize(pageSize);
        pageCurrent = PageUtil.checkPageCurrent(count, pageSize, pageCurrent);
        int totalPage = PageUtil.countTotalPage(count, pageSize);
        example.setLimitStart(PageUtil.countOffset(pageCurrent, pageSize));
        example.setPageSize(pageSize);
        return new Page<UserLogCommission>(count, totalPage, pageCurrent, pageSize, this.userLogCommissionMapper.selectByExample(example));
    }

    @Override
    public List<UserLogCommission> getCommissionLogByUserNo(Long userNo) {
        UserLogCommissionExample example = new UserLogCommissionExample();
        example.createCriteria().andUserNoEqualTo(userNo);
        example.setOrderByClause("add_time desc");
        return this.userLogCommissionMapper.selectByExample(example);
    }
}
