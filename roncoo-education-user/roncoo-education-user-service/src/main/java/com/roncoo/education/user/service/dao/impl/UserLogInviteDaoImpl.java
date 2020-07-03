
package com.roncoo.education.user.service.dao.impl;

import com.roncoo.education.user.service.common.resq.UserExtViewRESQ;
import com.roncoo.education.user.service.dao.UserLogInviteDao;
import com.roncoo.education.user.service.dao.impl.mapper.UserLogInviteMapper;
import com.roncoo.education.user.service.dao.impl.mapper.entity.UserLogInvite;
import com.roncoo.education.user.service.dao.impl.mapper.entity.UserLogInviteExample;
import com.roncoo.education.util.base.Page;
import com.roncoo.education.util.base.PageUtil;
import com.roncoo.education.util.base.Result;
import com.roncoo.education.util.tools.IdWorker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 用户邀请记录表 服务实现类
 *
 * @author husend
 * @since 2020-05-19
 */
@Repository
public class UserLogInviteDaoImpl implements UserLogInviteDao {

    @Autowired
    private UserLogInviteMapper userLogInviteMapper;

    @Override
    public int save(UserLogInvite record) {
    record.setId(IdWorker.getId());
    return this.userLogInviteMapper.insertSelective(record);
    }

    @Override
    public int deleteById(Long id) {
    return this.userLogInviteMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int updateById(UserLogInvite record) {
    return this.userLogInviteMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public UserLogInvite getById(Long id) {
    return this.userLogInviteMapper.selectByPrimaryKey(id);
    }

    @Override
    public Page<UserLogInvite> listForPage(int pageCurrent, int pageSize, UserLogInviteExample example) {
        int count = this.userLogInviteMapper.countByExample(example);
        pageSize = PageUtil.checkPageSize(pageSize);
        pageCurrent = PageUtil.checkPageCurrent(count, pageSize, pageCurrent);
        int totalPage = PageUtil.countTotalPage(count, pageSize);
        example.setLimitStart(PageUtil.countOffset(pageCurrent, pageSize));
        example.setPageSize(pageSize);
        return new Page<UserLogInvite>(count, totalPage, pageCurrent, pageSize, this.userLogInviteMapper.selectByExample(example));
    }

    @Override
    public UserLogInvite getByInvitedUserNo(Long userNo) {
        UserLogInviteExample example = new UserLogInviteExample();
        example.createCriteria().andInvitedUserNoEqualTo(userNo);
        List<UserLogInvite> userLogInvites = this.userLogInviteMapper.selectByExample(example);
        if (userLogInvites.size() < 0 || userLogInvites.isEmpty()) {
            return null;
        }
        return userLogInvites.get(0);
    }

    @Override
    public List<UserLogInvite> getByInviteUserNo(Long userNo) {
        UserLogInviteExample example = new UserLogInviteExample();
        example.createCriteria().andInviteUserNoEqualTo(userNo);
        List<UserLogInvite> userLogInvites = this.userLogInviteMapper.selectByExample(example);
        return userLogInvites;
    }
}
