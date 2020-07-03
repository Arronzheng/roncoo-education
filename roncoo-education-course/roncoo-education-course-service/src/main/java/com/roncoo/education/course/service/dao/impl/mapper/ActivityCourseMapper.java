package com.roncoo.education.course.service.dao.impl.mapper;

import com.roncoo.education.course.service.dao.impl.mapper.entity.ActivityCourse;
import com.roncoo.education.course.service.dao.impl.mapper.entity.ActivityCourseExample;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 活动专区课程关联表 Mapper 接口
 *
 * @author husend
 * @since 2020-04-10
 */
@Mapper
public interface ActivityCourseMapper {
            int countByExample(ActivityCourseExample example);

            int deleteByExample(ActivityCourseExample example);

            int deleteByPrimaryKey(Long id);

            int insert(ActivityCourse record);

            int insertSelective(ActivityCourse record);

            List<ActivityCourse> selectByExample(ActivityCourseExample example);

            ActivityCourse selectByPrimaryKey(Long id);

            int updateByExampleSelective(@Param("record") ActivityCourse record, @Param("example") ActivityCourseExample example);

            int updateByExample(@Param("record") ActivityCourse record, @Param("example") ActivityCourseExample example);

            int updateByPrimaryKeySelective(ActivityCourse record);

            int updateByPrimaryKey(ActivityCourse record);
        }
