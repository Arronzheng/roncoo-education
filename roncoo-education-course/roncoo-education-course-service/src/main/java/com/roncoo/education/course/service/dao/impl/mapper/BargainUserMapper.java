package com.roncoo.education.course.service.dao.impl.mapper;

import com.roncoo.education.course.service.dao.impl.mapper.entity.BargainUser;
import com.roncoo.education.course.service.dao.impl.mapper.entity.BargainUserExample;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 用户参与砍价表 Mapper 接口
 *
 * @author husend
 * @since 2020-04-14
 */
@Mapper
public interface BargainUserMapper {
            int countByExample(BargainUserExample example);

            int deleteByExample(BargainUserExample example);

            int deleteByPrimaryKey(Long id);

            int insert(BargainUser record);

            int insertSelective(BargainUser record);

            List<BargainUser> selectByExample(BargainUserExample example);

            BargainUser selectByPrimaryKey(Long id);

            int updateByExampleSelective(@Param("record") BargainUser record, @Param("example") BargainUserExample example);

            int updateByExample(@Param("record") BargainUser record, @Param("example") BargainUserExample example);

            int updateByPrimaryKeySelective(BargainUser record);

            int updateByPrimaryKey(BargainUser record);
        }
