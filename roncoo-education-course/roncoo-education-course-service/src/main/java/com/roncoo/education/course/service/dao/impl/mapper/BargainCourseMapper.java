package com.roncoo.education.course.service.dao.impl.mapper;

import com.roncoo.education.course.service.dao.impl.mapper.entity.BargainCourse;
import com.roncoo.education.course.service.dao.impl.mapper.entity.BargainCourseExample;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 砍价表 Mapper 接口
 *
 * @author husend
 * @since 2020-04-14
 */
@Mapper
public interface BargainCourseMapper {
            int countByExample(BargainCourseExample example);

            int deleteByExample(BargainCourseExample example);

            int deleteByPrimaryKey(Long id);

            int insert(BargainCourse record);

            int insertSelective(BargainCourse record);

            List<BargainCourse> selectByExample(BargainCourseExample example);

            BargainCourse selectByPrimaryKey(Long id);

            int updateByExampleSelective(@Param("record") BargainCourse record, @Param("example") BargainCourseExample example);

            int updateByExample(@Param("record") BargainCourse record, @Param("example") BargainCourseExample example);

            int updateByPrimaryKeySelective(BargainCourse record);

            int updateByPrimaryKey(BargainCourse record);
        }
