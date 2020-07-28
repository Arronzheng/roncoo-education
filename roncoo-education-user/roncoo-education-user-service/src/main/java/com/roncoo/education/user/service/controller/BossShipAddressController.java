package com.roncoo.education.user.service.controller;

import com.roncoo.education.user.common.bean.vo.SvipOrderVO;
import com.roncoo.education.user.common.bean.vo.UserShippingAddressVO;
import com.roncoo.education.user.common.interfaces.BossShipAddress;
import com.roncoo.education.user.common.interfaces.BossSvipOrder;
import com.roncoo.education.user.service.controller.biz.BossShipAddressBiz;
import com.roncoo.education.user.service.controller.biz.BossSvipOrderBiz;
import com.roncoo.education.util.base.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ${author}
 * @since 2019-12-17
 */
@RestController
public class BossShipAddressController extends BaseController implements BossShipAddress {

    @Autowired
    private BossShipAddressBiz biz;

    @Override
    public UserShippingAddressVO getById(@PathVariable(value = "id") Long id) {
        return biz.getById(id);
    }
}

