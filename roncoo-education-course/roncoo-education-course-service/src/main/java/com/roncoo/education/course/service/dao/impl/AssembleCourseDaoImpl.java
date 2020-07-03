
package com.roncoo.education.course.service.dao.impl;

import com.roncoo.education.course.service.dao.AssembleCourseDao;
import com.roncoo.education.course.service.dao.impl.mapper.AssembleCourseMapper;
import com.roncoo.education.course.service.dao.impl.mapper.entity.AssembleCourse;
import com.roncoo.education.course.service.dao.impl.mapper.entity.AssembleCourseExample;
import com.roncoo.education.util.base.Page;
import com.roncoo.education.util.base.PageUtil;
import com.roncoo.education.util.enums.StatusIdEnum;
import com.roncoo.education.util.tools.IdWorker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 拼团产品表 服务实现类
 *
 * @author husend
 * @since 2020-04-14
 */
@Repository
public class AssembleCourseDaoImpl implements AssembleCourseDao {

    @Autowired
    private AssembleCourseMapper assembleCourseMapper;

    @Override
    public int save(AssembleCourse record) {
    record.setId(IdWorker.getId());
    return this.assembleCourseMapper.insertSelective(record);
    }

    @Override
    public int deleteById(Long id) {
    return this.assembleCourseMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int updateById(AssembleCourse record) {
    return this.assembleCourseMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public AssembleCourse getById(Long id) {
    return this.assembleCourseMapper.selectByPrimaryKey(id);
    }

    @Override
    public Page<AssembleCourse> listForPage(int pageCurrent, int pageSize, AssembleCourseExample example) {
        int count = this.assembleCourseMapper.countByExample(example);
        pageSize = PageUtil.checkPageSize(pageSize);
        pageCurrent = PageUtil.checkPageCurrent(count, pageSize, pageCurrent);
        int totalPage = PageUtil.countTotalPage(count, pageSize);
        example.setLimitStart(PageUtil.countOffset(pageCurrent, pageSize));
        example.setPageSize(pageSize);
        return new Page<AssembleCourse>(count, totalPage, pageCurrent, pageSize, this.assembleCourseMapper.selectByExample(example));
    }

    @Override
    public List<AssembleCourse> getByProductId(Long id) {
        AssembleCourseExample example = new AssembleCourseExample();
        example.createCriteria().andProductIdEqualTo(id).andIsShowEqualTo(StatusIdEnum.YES.getCode());
        return this.assembleCourseMapper.selectByExample(example);
    }
}
