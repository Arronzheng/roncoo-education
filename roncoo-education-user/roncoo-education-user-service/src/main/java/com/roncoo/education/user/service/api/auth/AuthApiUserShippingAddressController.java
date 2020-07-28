package com.roncoo.education.user.service.api.auth;

import com.roncoo.education.user.service.biz.auth.AuthApiUserShippingAddressBiz;
import com.roncoo.education.user.service.common.bo.auth.*;
import com.roncoo.education.user.service.common.dto.auth.AuthUserShippingAddressDTO;
import com.roncoo.education.user.service.common.dto.auth.AuthUserShippingAddressPageDTO;
import com.roncoo.education.util.base.BaseController;
import com.roncoo.education.util.base.Page;
import com.roncoo.education.util.base.Result;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/user/auth/user/address")
public class AuthApiUserShippingAddressController extends BaseController {

    @Autowired
    private AuthApiUserShippingAddressBiz biz;

    /**
     * 收货地址分页列表接口
     */
    @ApiOperation(value = "收货地址分页列表接口", notes = "收货地址分页列表接口")
    @RequestMapping(value = "/list", method =  RequestMethod.POST)
    public Result<Page<AuthUserShippingAddressPageDTO>> listForPage(@RequestBody AuthUserShippingAddressPageBO authUserShippingAddressPageBO){
        return biz.listForPage(authUserShippingAddressPageBO);
    }

    /**
     * 收货地址信息保存接口
     */
    @ApiOperation(value = "收货地址信息保存接口", notes = "收货地址信息保存接口")
    @RequestMapping(value = "/save", method =  RequestMethod.POST)
    public Result<Integer> saveAddress(@RequestBody AuthUserShippingAddressSaveBO authUserShippingAddressSaveBO){
        return biz.saveAddress(authUserShippingAddressSaveBO);
    }

    /**
     * 收货地址信息查看接口,用于修改
     */
    @ApiOperation(value = "收货地址信息查看接口", notes = "收货地址信息查看接口")
    @RequestMapping(value = "/view", method =  RequestMethod.POST)
    public Result<AuthUserShippingAddressDTO> view(@RequestBody AuthUserShippingAddressViewBO authUserShippingAddressViewBO){
        return biz.view(authUserShippingAddressViewBO);
    }

    /**
     * 收货地址信息修改接口
     */
    @ApiOperation(value = "收货地址信息修改接口", notes = "收货地址信息修改接口")
    @RequestMapping(value = "/update", method =  RequestMethod.POST)
    public Result<Integer> update(@RequestBody AuthUserShippingAddressUpdateBO authUserShippingAddressUpdateBO){
        return biz.update(authUserShippingAddressUpdateBO);
    }

    /**
     * 收货地址信息删除接口
     */
    @ApiOperation(value = "收货地址信息删除接口", notes = "收货地址信息删除接口")
    @RequestMapping(value = "/delete", method =  RequestMethod.POST)
    public Result<Integer> delete(@RequestBody AuthUserShippingAddressDelBO authUserShippingAddressDelBO){
        return biz.delete(authUserShippingAddressDelBO);
    }

    /**
     * 获取默认收货地址接口
     */
    @ApiOperation(value = "获取默认收货地址接口", notes = "获取默认收货地址接口")
    @RequestMapping(value = "/defaultAddress", method =  RequestMethod.POST)
    public Result<AuthUserShippingAddressDTO> defaultAddress(@RequestBody AuthUserShippingAddressdefaultBO authUserShippingAddressdefaultBO){
        return biz.defaultAddress(authUserShippingAddressdefaultBO);
    }

}
