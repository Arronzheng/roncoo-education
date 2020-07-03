
package com.roncoo.education.course.service.dao.impl;

import com.roncoo.education.course.service.dao.BargainCourseDao;
import com.roncoo.education.course.service.dao.impl.mapper.BargainCourseMapper;
import com.roncoo.education.course.service.dao.impl.mapper.entity.BargainCourse;
import com.roncoo.education.course.service.dao.impl.mapper.entity.BargainCourseExample;
import com.roncoo.education.util.base.Page;
import com.roncoo.education.util.base.PageUtil;
import com.roncoo.education.util.tools.IdWorker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 砍价表 服务实现类
 *
 * @author husend
 * @since 2020-04-14
 */
@Repository
public class BargainCourseDaoImpl implements BargainCourseDao {

    @Autowired
    private BargainCourseMapper bargainCourseMapper;

    @Override
    public int save(BargainCourse record) {
    record.setId(IdWorker.getId());
    return this.bargainCourseMapper.insertSelective(record);
    }

    @Override
    public int deleteById(Long id) {
    return this.bargainCourseMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int updateById(BargainCourse record) {
    return this.bargainCourseMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public BargainCourse getById(Long id) {
    return this.bargainCourseMapper.selectByPrimaryKey(id);
    }

    @Override
    public Page<BargainCourse> listForPage(int pageCurrent, int pageSize, BargainCourseExample example) {
        int count = this.bargainCourseMapper.countByExample(example);
        pageSize = PageUtil.checkPageSize(pageSize);
        pageCurrent = PageUtil.checkPageCurrent(count, pageSize, pageCurrent);
        int totalPage = PageUtil.countTotalPage(count, pageSize);
        example.setLimitStart(PageUtil.countOffset(pageCurrent, pageSize));
        example.setPageSize(pageSize);
        return new Page<BargainCourse>(count, totalPage, pageCurrent, pageSize, this.bargainCourseMapper.selectByExample(example));
    }

    @Override
    public List<BargainCourse> getByProductId(Long id) {
        BargainCourseExample example = new BargainCourseExample();
        example.createCriteria().andProductIdEqualTo(id);
        return this.bargainCourseMapper.selectByExample(example);
    }

    @Override
    public BargainCourse getByIdAndStatus(Long bargainId, Integer code) {
        BargainCourseExample example = new BargainCourseExample();
        example.createCriteria().andIdEqualTo(bargainId).andStatusEqualTo(code);
        return this.bargainCourseMapper.selectByExample(example).get(0);
    }
}
