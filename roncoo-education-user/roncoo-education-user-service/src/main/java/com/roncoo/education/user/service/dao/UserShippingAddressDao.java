
package com.roncoo.education.user.service.dao;

import com.roncoo.education.user.service.dao.impl.mapper.entity.UserShippingAddress;
import com.roncoo.education.user.service.dao.impl.mapper.entity.UserShippingAddressExample;
import com.roncoo.education.util.base.Page;

import java.util.List;

/**
 * 用户收货地址信息 服务类
 *
 * @author ${author}
 * @since 2020-01-08
 */
public interface UserShippingAddressDao {
            int save(UserShippingAddress record);

            int deleteById(Long id);

            int updateById(UserShippingAddress record);

            UserShippingAddress getById(Long id);

            Page<UserShippingAddress> listForPage(int pageCurrent, int pageSize, UserShippingAddressExample example);

            /**
             * 根据用户编码查询用户所有地址
             * @param userNo
             * @return List<UserShippingAddress>
             */
            List<UserShippingAddress> getByUserNo(Long userNo);

            UserShippingAddress getByUserNoAndIsToleration(Long userNo, int i);
}
