
package com.roncoo.education.course.service.dao.impl;

import com.roncoo.education.course.service.dao.BargainUserHelpDao;
import com.roncoo.education.course.service.dao.impl.mapper.BargainUserHelpMapper;
import com.roncoo.education.course.service.dao.impl.mapper.entity.BargainUserHelp;
import com.roncoo.education.course.service.dao.impl.mapper.entity.BargainUserHelpExample;
import com.roncoo.education.util.base.Page;
import com.roncoo.education.util.base.PageUtil;
import com.roncoo.education.util.tools.IdWorker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 砍价用户帮助表 服务实现类
 *
 * @author husend
 * @since 2020-04-14
 */
@Repository
public class BargainUserHelpDaoImpl implements BargainUserHelpDao {

    @Autowired
    private BargainUserHelpMapper bargainUserHelpMapper;

    @Override
    public int save(BargainUserHelp record) {
    record.setId(IdWorker.getId());
    return this.bargainUserHelpMapper.insertSelective(record);
    }

    @Override
    public int deleteById(Long id) {
    return this.bargainUserHelpMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int updateById(BargainUserHelp record) {
    return this.bargainUserHelpMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public BargainUserHelp getById(Long id) {
    return this.bargainUserHelpMapper.selectByPrimaryKey(id);
    }

    @Override
    public Page<BargainUserHelp> listForPage(int pageCurrent, int pageSize, BargainUserHelpExample example) {
        int count = this.bargainUserHelpMapper.countByExample(example);
        pageSize = PageUtil.checkPageSize(pageSize);
        pageCurrent = PageUtil.checkPageCurrent(count, pageSize, pageCurrent);
        int totalPage = PageUtil.countTotalPage(count, pageSize);
        example.setLimitStart(PageUtil.countOffset(pageCurrent, pageSize));
        example.setPageSize(pageSize);
        return new Page<BargainUserHelp>(count, totalPage, pageCurrent, pageSize, this.bargainUserHelpMapper.selectByExample(example));
    }

    @Override
    public List<BargainUserHelp> getByBargainUserId(Long id) {
        BargainUserHelpExample example = new BargainUserHelpExample();
        example.createCriteria().andBargainUserIdEqualTo(id);
        example.setOrderByClause("add_time desc");
        return this.bargainUserHelpMapper.selectByExample(example);
    }

    @Override
    public BargainUserHelp getByUidAndBargainUserId(Long uid, Long bargainUserId) {
        BargainUserHelpExample example = new BargainUserHelpExample();
        example.createCriteria().andUidEqualTo(uid).andBargainUserIdEqualTo(bargainUserId);
        List<BargainUserHelp> bargainUserHelps = this.bargainUserHelpMapper.selectByExample(example);
        if (bargainUserHelps == null || bargainUserHelps.isEmpty()) {
            return null;
        }
        return bargainUserHelps.get(0);
    }
}
