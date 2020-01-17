
package com.roncoo.education.user.service.dao.impl;

import com.roncoo.education.user.common.bean.vo.SvipVO;
import com.roncoo.education.user.service.dao.SvipDao;
import com.roncoo.education.user.service.dao.impl.mapper.SvipMapper;
import com.roncoo.education.user.service.dao.impl.mapper.entity.Svip;
import com.roncoo.education.user.service.dao.impl.mapper.entity.SvipExample;
import com.roncoo.education.util.base.Page;
import com.roncoo.education.util.base.PageUtil;
import com.roncoo.education.util.tools.IdWorker;
import com.xiaoleilu.hutool.util.CollectionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * 会员信息表 服务实现类
 *
 * @author ${author}
 * @since 2019-12-17
 */
@Repository
public class SvipDaoImpl implements SvipDao {

    @Autowired
    private SvipMapper svipMapper;

    @Override
    public int save(Svip record) {
    record.setId(IdWorker.getId());
    return this.svipMapper.insertSelective(record);
    }

    @Override
    public int deleteById(Long id) {
    return this.svipMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int updateById(Svip record) {
    return this.svipMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public Svip getById(Long id) {
    return this.svipMapper.selectByPrimaryKey(id);
    }

    @Override
    public Page<Svip> listForPage(int pageCurrent, int pageSize, SvipExample example) {
        int count = this.svipMapper.countByExample(example);
        pageSize = PageUtil.checkPageSize(pageSize);
        pageCurrent = PageUtil.checkPageCurrent(count, pageSize, pageCurrent);
        int totalPage = PageUtil.countTotalPage(count, pageSize);
        example.setLimitStart(PageUtil.countOffset(pageCurrent, pageSize));
        example.setPageSize(pageSize);
        return new Page<Svip>(count, totalPage, pageCurrent, pageSize, this.svipMapper.selectByExample(example));
    }

    @Override
    public Svip getByUserNo(Long userNo) {
        SvipExample example = new SvipExample();
        SvipExample.Criteria c = example.createCriteria();
        c.andUserNoEqualTo(userNo).andDueTimeGreaterThan(new Date());
        List<Svip> vipVoList = this.svipMapper.selectByExample(example);
        if(vipVoList.isEmpty()){
            return null;
        }
        return vipVoList.get(0);
    }
}
