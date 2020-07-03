
package com.roncoo.education.course.service.dao;

import com.roncoo.education.course.service.dao.impl.mapper.entity.ActivityCourse;
import com.roncoo.education.course.service.dao.impl.mapper.entity.ActivityCourseExample;
import com.roncoo.education.util.base.Page;

import java.util.List;

/**
 * 活动专区课程关联表 服务类
 *
 * @author husend
 * @since 2020-04-10
 */
public interface ActivityCourseDao {
            int save(ActivityCourse record);

            int deleteById(Long id);

            int updateById(ActivityCourse record);

            ActivityCourse getById(Long id);

            Page<ActivityCourse> listForPage(int pageCurrent, int pageSize, ActivityCourseExample example);

            List<ActivityCourse> getBycourseIdAndCategory(Long id, Integer code);
}
