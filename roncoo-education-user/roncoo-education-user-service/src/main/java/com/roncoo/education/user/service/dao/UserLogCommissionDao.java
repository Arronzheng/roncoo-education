
package com.roncoo.education.user.service.dao;

import com.roncoo.education.user.service.dao.impl.mapper.entity.UserLogCommission;
import com.roncoo.education.user.service.dao.impl.mapper.entity.UserLogCommissionExample;
import com.roncoo.education.util.base.Page;

import java.util.List;

/**
 * 用户佣金记录表 服务类
 *
 * @author husend
 * @since 2020-06-11
 */
public interface UserLogCommissionDao {
            int save(UserLogCommission record);

            int deleteById(Long id);

            int updateById(UserLogCommission record);

            UserLogCommission getById(Long id);

            Page<UserLogCommission> listForPage(int pageCurrent, int pageSize, UserLogCommissionExample example);

            List<UserLogCommission> getCommissionLogByUserNo(Long userNo);
}
