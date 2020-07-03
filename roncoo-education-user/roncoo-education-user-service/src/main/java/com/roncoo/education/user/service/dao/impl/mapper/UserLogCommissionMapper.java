package com.roncoo.education.user.service.dao.impl.mapper;

import com.roncoo.education.user.service.dao.impl.mapper.entity.UserLogCommission;
import com.roncoo.education.user.service.dao.impl.mapper.entity.UserLogCommissionExample;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 用户佣金记录表 Mapper 接口
 *
 * @author husend
 * @since 2020-06-11
 */
@Mapper
public interface UserLogCommissionMapper {
            int countByExample(UserLogCommissionExample example);

            int deleteByExample(UserLogCommissionExample example);

            int deleteByPrimaryKey(Long id);

            int insert(UserLogCommission record);

            int insertSelective(UserLogCommission record);

            List<UserLogCommission> selectByExample(UserLogCommissionExample example);

            UserLogCommission selectByPrimaryKey(Long id);

            int updateByExampleSelective(@Param("record") UserLogCommission record, @Param("example") UserLogCommissionExample example);

            int updateByExample(@Param("record") UserLogCommission record, @Param("example") UserLogCommissionExample example);

            int updateByPrimaryKeySelective(UserLogCommission record);

            int updateByPrimaryKey(UserLogCommission record);
        }
