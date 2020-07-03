
package com.roncoo.education.course.service.dao.impl;

import com.roncoo.education.course.service.dao.BargainUserDao;
import com.roncoo.education.course.service.dao.impl.mapper.BargainUserMapper;
import com.roncoo.education.course.service.dao.impl.mapper.entity.BargainUser;
import com.roncoo.education.course.service.dao.impl.mapper.entity.BargainUserExample;
import com.roncoo.education.util.base.Page;
import com.roncoo.education.util.base.PageUtil;
import com.roncoo.education.util.tools.IdWorker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 用户参与砍价表 服务实现类
 *
 * @author husend
 * @since 2020-04-14
 */
@Repository
public class BargainUserDaoImpl implements BargainUserDao {

    @Autowired
    private BargainUserMapper bargainUserMapper;

    @Override
    public int save(BargainUser record) {
    record.setId(IdWorker.getId());
    return this.bargainUserMapper.insertSelective(record);
    }

    @Override
    public int deleteById(Long id) {
    return this.bargainUserMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int updateById(BargainUser record) {
    return this.bargainUserMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public BargainUser getById(Long id) {
    return this.bargainUserMapper.selectByPrimaryKey(id);
    }

    @Override
    public Page<BargainUser> listForPage(int pageCurrent, int pageSize, BargainUserExample example) {
        int count = this.bargainUserMapper.countByExample(example);
        pageSize = PageUtil.checkPageSize(pageSize);
        pageCurrent = PageUtil.checkPageCurrent(count, pageSize, pageCurrent);
        int totalPage = PageUtil.countTotalPage(count, pageSize);
        example.setLimitStart(PageUtil.countOffset(pageCurrent, pageSize));
        example.setPageSize(pageSize);
        return new Page<BargainUser>(count, totalPage, pageCurrent, pageSize, this.bargainUserMapper.selectByExample(example));
    }

    @Override
    public List<BargainUser> getByBargainIdAndUidAndStatus(Long bargainId, Long uid, int i) {
        BargainUserExample example = new BargainUserExample();
        example.createCriteria().andBargainIdEqualTo(bargainId).andUidEqualTo(uid).andStatusEqualTo(i);
        example.setOrderByClause("add_time desc");
        return this.bargainUserMapper.selectByExample(example);
    }

    @Override
    public List<BargainUser> getByUid(Long userNo) {
        BargainUserExample example = new BargainUserExample();
        example.createCriteria().andUidEqualTo(userNo);
        example.setOrderByClause("add_time desc");
        return this.bargainUserMapper.selectByExample(example);
    }
}
