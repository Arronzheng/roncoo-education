package com.roncoo.education.course.service.dao.impl.mapper;

import com.roncoo.education.course.service.dao.impl.mapper.entity.CourseComment;
import com.roncoo.education.course.service.dao.impl.mapper.entity.CourseCommentExample;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 *  Mapper 接口
 *
 * @author hw
 * @since 2019-12-05
 */
@Mapper
public interface CourseCommentMapper {
            int countByExample(CourseCommentExample example);

            int deleteByExample(CourseCommentExample example);

            int deleteByPrimaryKey(Long id);

            int insert(CourseComment record);

            int insertSelective(CourseComment record);

            List<CourseComment> selectByExample(CourseCommentExample example);

            CourseComment selectByPrimaryKey(Long id);

            int updateByExampleSelective(@Param("record") CourseComment record, @Param("example") CourseCommentExample example);

            int updateByExample(@Param("record") CourseComment record, @Param("example") CourseCommentExample example);

            int updateByPrimaryKeySelective(CourseComment record);

            int updateByPrimaryKey(CourseComment record);
        }
