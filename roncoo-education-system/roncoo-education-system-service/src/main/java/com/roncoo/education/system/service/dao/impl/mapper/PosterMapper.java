package com.roncoo.education.system.service.dao.impl.mapper;

import com.roncoo.education.system.service.dao.impl.mapper.entity.Poster;
import com.roncoo.education.system.service.dao.impl.mapper.entity.PosterExample;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 *  Mapper 接口
 *
 * @author husend
 * @since 2020-07-25
 */
@Mapper
public interface PosterMapper {
            int countByExample(PosterExample example);

            int deleteByExample(PosterExample example);

            int deleteByPrimaryKey(Long id);

            int insert(Poster record);

            int insertSelective(Poster record);

            List<Poster> selectByExample(PosterExample example);

            Poster selectByPrimaryKey(Long id);

            int updateByExampleSelective(@Param("record") Poster record, @Param("example") PosterExample example);

            int updateByExample(@Param("record") Poster record, @Param("example") PosterExample example);

            int updateByPrimaryKeySelective(Poster record);

            int updateByPrimaryKey(Poster record);
        }
