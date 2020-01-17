package com.roncoo.education.user.service.biz.auth;

import com.roncoo.education.user.service.common.bo.auth.*;
import com.roncoo.education.user.service.common.dto.auth.AuthUserShippingAddressDTO;
import com.roncoo.education.user.service.common.dto.auth.AuthUserShippingAddressPageDTO;
import com.roncoo.education.user.service.dao.UserShippingAddressDao;
import com.roncoo.education.user.service.dao.impl.mapper.entity.UserShippingAddress;
import com.roncoo.education.user.service.dao.impl.mapper.entity.UserShippingAddressExample;
import com.roncoo.education.util.base.BaseBiz;
import com.roncoo.education.util.base.Page;
import com.roncoo.education.util.base.PageUtil;
import com.roncoo.education.util.base.Result;
import com.roncoo.education.util.enums.IsDefaultEnum;
import com.roncoo.education.util.enums.ResultEnum;
import com.roncoo.education.util.tools.BeanUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import java.util.List;

@Component
public class AuthApiUserShippingAddressBiz extends BaseBiz {

    @Autowired
    private UserShippingAddressDao dao;

    public Result<Page<AuthUserShippingAddressPageDTO>> listForPage(AuthUserShippingAddressPageBO authUserShippingAddressPageBO) {
        if(authUserShippingAddressPageBO.getUserNo() == null){
            return Result.error("用户编码不能为空");
        }
        UserShippingAddressExample example =new UserShippingAddressExample();
        example.createCriteria().andUserNoEqualTo(authUserShippingAddressPageBO.getUserNo());
        Page<UserShippingAddress> page = dao.listForPage(authUserShippingAddressPageBO.getPageCurrent(), authUserShippingAddressPageBO.getPageSize(), example);
        Page<AuthUserShippingAddressPageDTO> dtoPage = PageUtil.transform(page, AuthUserShippingAddressPageDTO.class);
        return Result.success(dtoPage);
    }

    public Result<Integer> saveAddress(AuthUserShippingAddressSaveBO authUserShippingAddressSaveBO) {
        if (authUserShippingAddressSaveBO.getUserNo() == null) {
            return Result.error("用户编码不能为空");
        }
        UserShippingAddress userShippingAddress = BeanUtil.copyProperties(authUserShippingAddressSaveBO, UserShippingAddress.class);
        //修改默认地址
        updateDefault(authUserShippingAddressSaveBO.getIsToleration(), authUserShippingAddressSaveBO.getUserNo(), userShippingAddress);
        int reNum = dao.save(userShippingAddress);
        if(reNum > 0){
            return Result.success(reNum);
        }else{
            return Result.error(ResultEnum.ERROR);
        }
    }

    public Result<AuthUserShippingAddressDTO> view(AuthUserShippingAddressViewBO authUserShippingAddressViewBO) {
        if(authUserShippingAddressViewBO.getId() == null){
            return Result.error("id不能为空");
        }
        UserShippingAddress userShippingAddress = dao.getById(authUserShippingAddressViewBO.getId());
        if(ObjectUtils.isEmpty(userShippingAddress)){
            return Result.error("找不到地址信息");
        }
        return Result.success(BeanUtil.copyProperties(userShippingAddress, AuthUserShippingAddressDTO.class));
    }

    public Result<Integer> update(AuthUserShippingAddressUpdateBO authUserShippingAddressUpdateBO) {
        if(StringUtils.isEmpty(authUserShippingAddressUpdateBO.getId())){
            return Result.error("id不能为空");
        }
        if (authUserShippingAddressUpdateBO.getUserNo() == null) {
            return Result.error("用户编码不能为空");
        }
        UserShippingAddress userShippingAddress = BeanUtil.copyProperties(authUserShippingAddressUpdateBO, UserShippingAddress.class);
        //修改默认地址
        updateDefault(authUserShippingAddressUpdateBO.getIsToleration(), authUserShippingAddressUpdateBO.getUserNo(), userShippingAddress);
        int reNum = dao.updateById(userShippingAddress);
        if(reNum > 0){
            return Result.success(reNum);
        }
        return Result.error(ResultEnum.SYSTEM_UPDATE_FAIL);
    }

    public Result<Integer> delete(AuthUserShippingAddressDelBO authUserShippingAddressDelBO) {
        if(StringUtils.isEmpty(authUserShippingAddressDelBO.getId())){
            return Result.error("id不能为空");
        }
        int res = dao.deleteById(authUserShippingAddressDelBO.getId());
        if(res > 0){
            return Result.success(res);
        }
        return Result.error(ResultEnum.SYSTEM_DELETE_FAIL);
    }

    private void updateDefault(Integer isToleration, Long UserNo, UserShippingAddress userShippingAddress){
        if(IsDefaultEnum.YES.getCode().equals(isToleration)){
            List<UserShippingAddress> list = dao.getByUserNo(UserNo);
            if(list.isEmpty()){
                userShippingAddress.setIsToleration(IsDefaultEnum.YES.getCode());
            }else{
                for (UserShippingAddress userShippingAddress1:list) {
                    if(userShippingAddress1.getIsToleration().equals(IsDefaultEnum.YES.getCode())){
                        userShippingAddress1.setIsToleration(IsDefaultEnum.NO.getCode());
                        dao.updateById(userShippingAddress1);
                    }
                }
            }
        }
    }
}
