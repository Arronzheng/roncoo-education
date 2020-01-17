
package com.roncoo.education.user.service.dao.impl;

import com.roncoo.education.user.service.dao.SvipBuyLogDao;
import com.roncoo.education.user.service.dao.impl.mapper.SvipBuyLogMapper;
import com.roncoo.education.user.service.dao.impl.mapper.entity.SvipBuyLog;
import com.roncoo.education.user.service.dao.impl.mapper.entity.SvipBuyLogExample;
import com.roncoo.education.util.base.Page;
import com.roncoo.education.util.base.PageUtil;
import com.roncoo.education.util.tools.IdWorker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * 会员购买日志表 服务实现类
 *
 * @author ${author}
 * @since 2019-12-25
 */
@Repository
public class SvipBuyLogDaoImpl implements SvipBuyLogDao {

    @Autowired
    private SvipBuyLogMapper svipBuyLogMapper;

    @Override
    public int save(SvipBuyLog record) {
    record.setId(IdWorker.getId());
    return this.svipBuyLogMapper.insertSelective(record);
    }

    @Override
    public int deleteById(Long id) {
    return this.svipBuyLogMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int updateById(SvipBuyLog record) {
    return this.svipBuyLogMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public SvipBuyLog getById(Long id) {
    return this.svipBuyLogMapper.selectByPrimaryKey(id);
    }

    @Override
    public Page<SvipBuyLog> listForPage(int pageCurrent, int pageSize, SvipBuyLogExample example) {
        int count = this.svipBuyLogMapper.countByExample(example);
        pageSize = PageUtil.checkPageSize(pageSize);
        pageCurrent = PageUtil.checkPageCurrent(count, pageSize, pageCurrent);
        int totalPage = PageUtil.countTotalPage(count, pageSize);
        example.setLimitStart(PageUtil.countOffset(pageCurrent, pageSize));
        example.setPageSize(pageSize);
        return new Page<SvipBuyLog>(count, totalPage, pageCurrent, pageSize, this.svipBuyLogMapper.selectByExample(example));
    }
}
