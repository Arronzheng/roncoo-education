package com.roncoo.education.user.service.dao.impl.mapper;

import com.roncoo.education.user.service.dao.impl.mapper.entity.UserShippingAddress;
import com.roncoo.education.user.service.dao.impl.mapper.entity.UserShippingAddressExample;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 用户收货地址信息 Mapper 接口
 *
 * @author ${author}
 * @since 2020-01-08
 */
@Mapper
public interface UserShippingAddressMapper {
            int countByExample(UserShippingAddressExample example);

            int deleteByExample(UserShippingAddressExample example);

            int deleteByPrimaryKey(Long id);

            int insert(UserShippingAddress record);

            int insertSelective(UserShippingAddress record);

            List<UserShippingAddress> selectByExample(UserShippingAddressExample example);

            UserShippingAddress selectByPrimaryKey(Long id);

            int updateByExampleSelective(@Param("record") UserShippingAddress record, @Param("example") UserShippingAddressExample example);

            int updateByExample(@Param("record") UserShippingAddress record, @Param("example") UserShippingAddressExample example);

            int updateByPrimaryKeySelective(UserShippingAddress record);

            int updateByPrimaryKey(UserShippingAddress record);
        }
