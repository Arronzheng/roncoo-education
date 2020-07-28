
package com.roncoo.education.system.service.dao.impl;

import com.roncoo.education.system.service.dao.PosterDao;
import com.roncoo.education.system.service.dao.impl.mapper.PosterMapper;
import com.roncoo.education.system.service.dao.impl.mapper.entity.Poster;
import com.roncoo.education.system.service.dao.impl.mapper.entity.PosterExample;
import com.roncoo.education.util.base.Page;
import com.roncoo.education.util.base.PageUtil;
import com.roncoo.education.util.tools.IdWorker;
import com.xiaoleilu.hutool.util.CollectionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 *  服务实现类
 *
 * @author husend
 * @since 2020-07-25
 */
@Repository
public class PosterDaoImpl implements PosterDao {

    @Autowired
    private PosterMapper posterMapper;

    @Override
    public int save(Poster record) {
    record.setId(IdWorker.getId());
    return this.posterMapper.insertSelective(record);
    }

    @Override
    public int deleteById(Long id) {
    return this.posterMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int updateById(Poster record) {
    return this.posterMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public Poster getById(Long id) {
    return this.posterMapper.selectByPrimaryKey(id);
    }

    @Override
    public Page<Poster> listForPage(int pageCurrent, int pageSize, PosterExample example) {
        int count = this.posterMapper.countByExample(example);
        pageSize = PageUtil.checkPageSize(pageSize);
        pageCurrent = PageUtil.checkPageCurrent(count, pageSize, pageCurrent);
        int totalPage = PageUtil.countTotalPage(count, pageSize);
        example.setLimitStart(PageUtil.countOffset(pageCurrent, pageSize));
        example.setPageSize(pageSize);
        return new Page<Poster>(count, totalPage, pageCurrent, pageSize, this.posterMapper.selectByExample(example));
    }

    @Override
    public Poster getByPosterType(Integer posterType) {
        PosterExample example = new PosterExample();
        PosterExample.Criteria c = example.createCriteria();
        c.andPosterTypeEqualTo(posterType);
        example.setOrderByClause("gmt_modified desc");
        List<Poster> posters = this.posterMapper.selectByExample(example);
        if (CollectionUtil.isNotEmpty(posters)) {
            return posters.get(0);
        }
        return null;
    }
}
