package com.roncoo.education.course.service.dao.impl.mapper;

import com.roncoo.education.course.service.dao.impl.mapper.entity.AssembleCourse;
import com.roncoo.education.course.service.dao.impl.mapper.entity.AssembleCourseExample;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 拼团产品表 Mapper 接口
 *
 * @author husend
 * @since 2020-04-14
 */
@Mapper
public interface AssembleCourseMapper {
            int countByExample(AssembleCourseExample example);

            int deleteByExample(AssembleCourseExample example);

            int deleteByPrimaryKey(Long id);

            int insert(AssembleCourse record);

            int insertSelective(AssembleCourse record);

            List<AssembleCourse> selectByExample(AssembleCourseExample example);

            AssembleCourse selectByPrimaryKey(Long id);

            int updateByExampleSelective(@Param("record") AssembleCourse record, @Param("example") AssembleCourseExample example);

            int updateByExample(@Param("record") AssembleCourse record, @Param("example") AssembleCourseExample example);

            int updateByPrimaryKeySelective(AssembleCourse record);

            int updateByPrimaryKey(AssembleCourse record);
        }
