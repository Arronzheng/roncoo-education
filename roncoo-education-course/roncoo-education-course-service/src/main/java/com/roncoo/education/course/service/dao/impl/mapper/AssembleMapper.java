package com.roncoo.education.course.service.dao.impl.mapper;

import com.roncoo.education.course.service.dao.impl.mapper.entity.Assemble;
import com.roncoo.education.course.service.dao.impl.mapper.entity.AssembleExample;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 拼团表 Mapper 接口
 *
 * @author husend
 * @since 2020-04-14
 */
@Mapper
public interface AssembleMapper {
            int countByExample(AssembleExample example);

            int deleteByExample(AssembleExample example);

            int deleteByPrimaryKey(Long id);

            int insert(Assemble record);

            int insertSelective(Assemble record);

            List<Assemble> selectByExample(AssembleExample example);

            Assemble selectByPrimaryKey(Long id);

            int updateByExampleSelective(@Param("record") Assemble record, @Param("example") AssembleExample example);

            int updateByExample(@Param("record") Assemble record, @Param("example") AssembleExample example);

            int updateByPrimaryKeySelective(Assemble record);

            int updateByPrimaryKey(Assemble record);
        }
