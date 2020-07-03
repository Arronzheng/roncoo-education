package com.roncoo.education.course.service.dao.impl.mapper;

import com.roncoo.education.course.service.dao.impl.mapper.entity.BargainUserHelp;
import com.roncoo.education.course.service.dao.impl.mapper.entity.BargainUserHelpExample;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 砍价用户帮助表 Mapper 接口
 *
 * @author husend
 * @since 2020-04-14
 */
@Mapper
public interface BargainUserHelpMapper {
            int countByExample(BargainUserHelpExample example);

            int deleteByExample(BargainUserHelpExample example);

            int deleteByPrimaryKey(Long id);

            int insert(BargainUserHelp record);

            int insertSelective(BargainUserHelp record);

            List<BargainUserHelp> selectByExample(BargainUserHelpExample example);

            BargainUserHelp selectByPrimaryKey(Long id);

            int updateByExampleSelective(@Param("record") BargainUserHelp record, @Param("example") BargainUserHelpExample example);

            int updateByExample(@Param("record") BargainUserHelp record, @Param("example") BargainUserHelpExample example);

            int updateByPrimaryKeySelective(BargainUserHelp record);

            int updateByPrimaryKey(BargainUserHelp record);
        }
