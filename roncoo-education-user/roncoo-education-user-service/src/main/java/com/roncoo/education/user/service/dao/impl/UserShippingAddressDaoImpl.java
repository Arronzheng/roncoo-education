
package com.roncoo.education.user.service.dao.impl;

import com.roncoo.education.user.service.dao.UserShippingAddressDao;
import com.roncoo.education.user.service.dao.impl.mapper.UserShippingAddressMapper;
import com.roncoo.education.user.service.dao.impl.mapper.entity.UserShippingAddress;
import com.roncoo.education.user.service.dao.impl.mapper.entity.UserShippingAddressExample;
import com.roncoo.education.util.base.Page;
import com.roncoo.education.util.base.PageUtil;
import com.roncoo.education.util.base.Result;
import com.roncoo.education.util.enums.StatusIdEnum;
import com.roncoo.education.util.tools.IdWorker;
import com.xiaoleilu.hutool.util.CollectionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 用户收货地址信息 服务实现类
 *
 * @author ${author}
 * @since 2020-01-08
 */
@Repository
public class UserShippingAddressDaoImpl implements UserShippingAddressDao {

    @Autowired
    private UserShippingAddressMapper userShippingAddressMapper;

    @Override
    public int save(UserShippingAddress record) {
    record.setId(IdWorker.getId());
    return this.userShippingAddressMapper.insertSelective(record);
    }

    @Override
    public int deleteById(Long id) {
    return this.userShippingAddressMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int updateById(UserShippingAddress record) {
    return this.userShippingAddressMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public UserShippingAddress getById(Long id) {
    return this.userShippingAddressMapper.selectByPrimaryKey(id);
    }

    @Override
    public Page<UserShippingAddress> listForPage(int pageCurrent, int pageSize, UserShippingAddressExample example) {
        int count = this.userShippingAddressMapper.countByExample(example);
        pageSize = PageUtil.checkPageSize(pageSize);
        pageCurrent = PageUtil.checkPageCurrent(count, pageSize, pageCurrent);
        int totalPage = PageUtil.countTotalPage(count, pageSize);
        example.setLimitStart(PageUtil.countOffset(pageCurrent, pageSize));
        example.setPageSize(pageSize);
        example.setOrderByClause("is_toleration desc");
        return new Page<UserShippingAddress>(count, totalPage, pageCurrent, pageSize, this.userShippingAddressMapper.selectByExample(example));
    }

    @Override
    public List<UserShippingAddress> getByUserNo(Long userNo) {
        UserShippingAddressExample example = new UserShippingAddressExample();
        example.createCriteria().andUserNoEqualTo(userNo).andStatusIdEqualTo(StatusIdEnum.YES.getCode());
        return this.userShippingAddressMapper.selectByExample(example);
    }

    @Override
    public UserShippingAddress getByUserNoAndIsToleration(Long userNo, int i) {
        UserShippingAddressExample example = new UserShippingAddressExample();
        example.createCriteria().andUserNoEqualTo(userNo).andIsTolerationEqualTo(i);
        List<UserShippingAddress> userShippingAddresses = this.userShippingAddressMapper.selectByExample(example);
        if (CollectionUtil.isNotEmpty(userShippingAddresses)) {
            return userShippingAddresses.get(0);
        }
        return null;
    }
}
