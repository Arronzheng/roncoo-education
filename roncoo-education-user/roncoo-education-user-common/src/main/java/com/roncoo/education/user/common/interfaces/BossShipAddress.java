package com.roncoo.education.user.common.interfaces;

import com.roncoo.education.user.common.bean.vo.SvipOrderVO;
import com.roncoo.education.user.common.bean.vo.UserShippingAddressVO;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

public interface BossShipAddress {

    @RequestMapping(value = "/boss/user/address/getById/{id}", method = RequestMethod.GET)
    UserShippingAddressVO getById(@PathVariable(value = "id") Long id);
}
