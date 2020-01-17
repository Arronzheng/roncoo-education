package com.roncoo.education.user.service.controller;

import com.roncoo.education.user.common.bean.vo.SvipOrderVO;
import com.roncoo.education.user.common.interfaces.BossSvipOrder;
import com.roncoo.education.user.service.biz.auth.AuthApiSvipOrderBiz;
import com.roncoo.education.user.service.common.bo.auth.AuthSvipOrderBO;
import com.roncoo.education.user.service.common.bo.auth.AuthSvipOrderViewBO;
import com.roncoo.education.user.service.common.dto.auth.AuthSvipOrderDTO;
import com.roncoo.education.user.service.controller.biz.BossSvipOrderBiz;
import com.roncoo.education.util.base.BaseController;
import com.roncoo.education.util.base.Result;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author ${author}
 * @since 2019-12-17
 */
@RestController
public class BossSvipOrderController extends BaseController implements BossSvipOrder{

    @Autowired
    private BossSvipOrderBiz biz;

    @Override
    public SvipOrderVO getBySerialNo(@PathVariable(value = "serialNo") Long serialNo) {
        return biz.getBySerialNo(serialNo);
    }

    @Override
    public int updateById(@RequestBody SvipOrderVO record) {
        return biz.updateById(record);
    }

}

