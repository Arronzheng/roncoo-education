package com.roncoo.education.course.service.dao.impl.mapper;

import com.roncoo.education.course.service.dao.impl.mapper.entity.CourseUserCollection;
import com.roncoo.education.course.service.dao.impl.mapper.entity.CourseUserCollectionExample;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 *  Mapper 接口
 *
 * @author ${author}
 * @since 2020-01-04
 */
@Mapper
public interface CourseUserCollectionMapper {
            int countByExample(CourseUserCollectionExample example);

            int deleteByExample(CourseUserCollectionExample example);

            int deleteByPrimaryKey(Long id);

            int insert(CourseUserCollection record);

            int insertSelective(CourseUserCollection record);

            List<CourseUserCollection> selectByExample(CourseUserCollectionExample example);

            CourseUserCollection selectByPrimaryKey(Long id);

            int updateByExampleSelective(@Param("record") CourseUserCollection record, @Param("example") CourseUserCollectionExample example);

            int updateByExample(@Param("record") CourseUserCollection record, @Param("example") CourseUserCollectionExample example);

            int updateByPrimaryKeySelective(CourseUserCollection record);

            int updateByPrimaryKey(CourseUserCollection record);
        }
