
package com.roncoo.education.course.service.dao.impl;

import com.roncoo.education.course.service.dao.CourseCommentDao;
import com.roncoo.education.course.service.dao.impl.mapper.CourseCommentMapper;
import com.roncoo.education.course.service.dao.impl.mapper.entity.CourseComment;
import com.roncoo.education.course.service.dao.impl.mapper.entity.CourseCommentExample;
import com.roncoo.education.util.base.Page;
import com.roncoo.education.util.base.PageUtil;
import com.roncoo.education.util.tools.IdWorker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 *  服务实现类
 *
 * @author hw
 * @since 2019-12-05
 */
@Repository
public class CourseCommentDaoImpl implements CourseCommentDao {

    @Autowired
    private CourseCommentMapper courseCommentMapper;

    @Override
    public int save(CourseComment record) {
    record.setId(IdWorker.getId());
    return this.courseCommentMapper.insertSelective(record);
    }

    @Override
    public int deleteById(Long id) {
    return this.courseCommentMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int updateById(CourseComment record) {
    return this.courseCommentMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public CourseComment getById(Long id) {
    return this.courseCommentMapper.selectByPrimaryKey(id);
    }

    @Override
    public Page<CourseComment> listForPage(int pageCurrent, int pageSize, CourseCommentExample example) {
        int count = this.courseCommentMapper.countByExample(example);
        pageSize = PageUtil.checkPageSize(pageSize);
        pageCurrent = PageUtil.checkPageCurrent(count, pageSize, pageCurrent);
        int totalPage = PageUtil.countTotalPage(count, pageSize);
        example.setLimitStart(PageUtil.countOffset(pageCurrent, pageSize));
        example.setPageSize(pageSize);
        return new Page<CourseComment>(count, totalPage, pageCurrent, pageSize, this.courseCommentMapper.selectByExample(example));
    }

    @Override
    public List<CourseComment> listByParentId(Long parentId) {
        CourseCommentExample example = new CourseCommentExample();
        CourseCommentExample.Criteria c = example.createCriteria();
        c.andPidEqualTo(parentId);
        example.setOrderByClause("created_at asc");
        return this.courseCommentMapper.selectByExample(example);
    }

    @Override
    public int deleteByPid(Long pid) {
        CourseCommentExample example = new CourseCommentExample();
        CourseCommentExample.Criteria c = example.createCriteria();
        c.andPidEqualTo(pid);
        return this.courseCommentMapper.deleteByExample(example);
    }

    @Override
    public List<CourseComment> getByCourseIdAndPid(Long courseId) {
        CourseCommentExample example = new CourseCommentExample();
        CourseCommentExample.Criteria c = example.createCriteria();
        c.andCourseIdEqualTo(courseId).andPidEqualTo(0L).andStatusEqualTo(1);
        example.setOrderByClause("topping asc, created_at desc");
        return this.courseCommentMapper.selectByExample(example);
    }
}
