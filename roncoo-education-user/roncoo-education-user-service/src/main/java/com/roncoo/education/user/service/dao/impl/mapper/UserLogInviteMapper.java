package com.roncoo.education.user.service.dao.impl.mapper;

import com.roncoo.education.user.service.dao.impl.mapper.entity.UserLogInvite;
import com.roncoo.education.user.service.dao.impl.mapper.entity.UserLogInviteExample;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 用户邀请记录表 Mapper 接口
 *
 * @author husend
 * @since 2020-05-19
 */
@Mapper
public interface UserLogInviteMapper {
            int countByExample(UserLogInviteExample example);

            int deleteByExample(UserLogInviteExample example);

            int deleteByPrimaryKey(Long id);

            int insert(UserLogInvite record);

            int insertSelective(UserLogInvite record);

            List<UserLogInvite> selectByExample(UserLogInviteExample example);

            UserLogInvite selectByPrimaryKey(Long id);

            int updateByExampleSelective(@Param("record") UserLogInvite record, @Param("example") UserLogInviteExample example);

            int updateByExample(@Param("record") UserLogInvite record, @Param("example") UserLogInviteExample example);

            int updateByPrimaryKeySelective(UserLogInvite record);

            int updateByPrimaryKey(UserLogInvite record);
        }
