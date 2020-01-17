
package com.roncoo.education.user.service.dao;

import com.roncoo.education.user.service.dao.impl.mapper.entity.SvipOrder;
import com.roncoo.education.user.service.dao.impl.mapper.entity.SvipOrderExample;
import com.roncoo.education.util.base.Page;

/**
 * 订单信息表 服务类
 *
 * @author ${author}
 * @since 2019-12-23
 */
public interface SvipOrderDao {
    int save(SvipOrder record);

    int deleteById(Long id);

    int updateById(SvipOrder record);

    SvipOrder getById(Long id);

    Page<SvipOrder> listForPage(int pageCurrent, int pageSize, SvipOrderExample example);

    SvipOrder getByUserNoAndOrderStatus(Long userNo);

    SvipOrder getByOrderNo(Long orderNo);

    SvipOrder getBySerialNo(Long serialNo);
}
