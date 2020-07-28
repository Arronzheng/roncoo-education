package com.roncoo.education.user.service.controller.biz;

import com.roncoo.education.user.common.bean.vo.UserShippingAddressVO;
import com.roncoo.education.user.service.dao.UserShippingAddressDao;
import com.roncoo.education.util.base.BaseBiz;
import com.roncoo.education.util.tools.BeanUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 订单信息表
 *
 *
 */
@Component
public class BossShipAddressBiz extends BaseBiz {

	@Autowired
	private UserShippingAddressDao dao;

	public UserShippingAddressVO getById(Long id) {
		return BeanUtil.copyProperties(dao.getById(id), UserShippingAddressVO.class);
	}
}
