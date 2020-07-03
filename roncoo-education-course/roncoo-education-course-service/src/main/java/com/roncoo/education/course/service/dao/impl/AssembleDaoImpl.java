
package com.roncoo.education.course.service.dao.impl;

import com.roncoo.education.course.service.dao.AssembleDao;
import com.roncoo.education.course.service.dao.impl.mapper.AssembleMapper;
import com.roncoo.education.course.service.dao.impl.mapper.entity.Assemble;
import com.roncoo.education.course.service.dao.impl.mapper.entity.AssembleCourse;
import com.roncoo.education.course.service.dao.impl.mapper.entity.AssembleExample;
import com.roncoo.education.util.base.Page;
import com.roncoo.education.util.base.PageUtil;
import com.roncoo.education.util.tools.IdWorker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 拼团表 服务实现类
 *
 * @author husend
 * @since 2020-04-14
 */
@Repository
public class AssembleDaoImpl implements AssembleDao {

    @Autowired
    private AssembleMapper assembleMapper;

    @Override
    public int save(Assemble record) {
    record.setId(IdWorker.getId());
    return this.assembleMapper.insertSelective(record);
    }

    @Override
    public int deleteById(Long id) {
    return this.assembleMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int updateById(Assemble record) {
    return this.assembleMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public Assemble getById(Long id) {
    return this.assembleMapper.selectByPrimaryKey(id);
    }

    @Override
    public Page<Assemble> listForPage(int pageCurrent, int pageSize, AssembleExample example) {
        int count = this.assembleMapper.countByExample(example);
        pageSize = PageUtil.checkPageSize(pageSize);
        pageCurrent = PageUtil.checkPageCurrent(count, pageSize, pageCurrent);
        int totalPage = PageUtil.countTotalPage(count, pageSize);
        example.setLimitStart(PageUtil.countOffset(pageCurrent, pageSize));
        example.setPageSize(pageSize);
        return new Page<Assemble>(count, totalPage, pageCurrent, pageSize, this.assembleMapper.selectByExample(example));
    }

    @Override
    public void updateByAssembleId(Assemble a) {
        AssembleExample example = new AssembleExample();
        AssembleExample.Criteria c = example.createCriteria();
        c.andAssembleIdEqualTo(a.getAssembleId());
        this.assembleMapper.updateByExampleSelective(a, example);
    }

    @Override
    public List<Assemble> getByAssembleId(Long id) {
        AssembleExample example = new AssembleExample();
        AssembleExample.Criteria c = example.createCriteria();
        c.andAssembleIdEqualTo(id);
        return this.assembleMapper.selectByExample(example);
    }

    @Override
    public List<Assemble> getByCid(Long id, Long UserNo) {
        AssembleExample example = new AssembleExample();
        AssembleExample.Criteria c = example.createCriteria();
        c.andCidEqualTo(id).andStatusEqualTo(1).andIsAsmerEqualTo(1).andUidNotEqualTo(UserNo);
        return this.assembleMapper.selectByExample(example);
    }

    @Override
    public List<Assemble> getByUid(Long userNo) {
        AssembleExample example = new AssembleExample();
        AssembleExample.Criteria c = example.createCriteria();
        c.andUidEqualTo(userNo);
        return this.assembleMapper.selectByExample(example);
    }
}
