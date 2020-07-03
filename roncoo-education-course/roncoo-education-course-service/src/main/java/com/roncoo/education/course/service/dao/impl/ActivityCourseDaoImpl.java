
package com.roncoo.education.course.service.dao.impl;

import com.roncoo.education.course.service.dao.ActivityCourseDao;
import com.roncoo.education.course.service.dao.impl.mapper.ActivityCourseMapper;
import com.roncoo.education.course.service.dao.impl.mapper.entity.ActivityCourse;
import com.roncoo.education.course.service.dao.impl.mapper.entity.ActivityCourseExample;
import com.roncoo.education.util.base.Page;
import com.roncoo.education.util.base.PageUtil;
import com.roncoo.education.util.tools.IdWorker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 活动专区课程关联表 服务实现类
 *
 * @author husend
 * @since 2020-04-10
 */
@Repository
public class ActivityCourseDaoImpl implements ActivityCourseDao {

    @Autowired
    private ActivityCourseMapper activityCourseMapper;

    @Override
    public int save(ActivityCourse record) {
    record.setId(IdWorker.getId());
    return this.activityCourseMapper.insertSelective(record);
    }

    @Override
    public int deleteById(Long id) {
    return this.activityCourseMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int updateById(ActivityCourse record) {
    return this.activityCourseMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public ActivityCourse getById(Long id) {
    return this.activityCourseMapper.selectByPrimaryKey(id);
    }

    @Override
    public Page<ActivityCourse> listForPage(int pageCurrent, int pageSize, ActivityCourseExample example) {
        int count = this.activityCourseMapper.countByExample(example);
        pageSize = PageUtil.checkPageSize(pageSize);
        pageCurrent = PageUtil.checkPageCurrent(count, pageSize, pageCurrent);
        int totalPage = PageUtil.countTotalPage(count, pageSize);
        example.setLimitStart(PageUtil.countOffset(pageCurrent, pageSize));
        example.setPageSize(pageSize);
        return new Page<ActivityCourse>(count, totalPage, pageCurrent, pageSize, this.activityCourseMapper.selectByExample(example));
    }

    @Override
    public List<ActivityCourse> getBycourseIdAndCategory(Long id, Integer code) {
        ActivityCourseExample example = new ActivityCourseExample();
        ActivityCourseExample.Criteria c = example.createCriteria();
        c.andCourseIdEqualTo(id);
        c.andActivityCategoryEqualTo(code);
        List<ActivityCourse> list = this.activityCourseMapper.selectByExample(example);
        return list;
    }
}
