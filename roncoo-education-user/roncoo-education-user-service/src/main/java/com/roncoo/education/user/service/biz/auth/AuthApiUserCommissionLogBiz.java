package com.roncoo.education.user.service.biz.auth;

import com.roncoo.education.course.common.bean.vo.OrderInfoVO;
import com.roncoo.education.course.feign.IBossOrderInfo;
import com.roncoo.education.user.common.bean.vo.UserExtVO;
import com.roncoo.education.user.service.common.bo.auth.AuthApiUserCommissionLogBO;
import com.roncoo.education.user.service.common.dto.auth.AuthUserLogCommissionListDTO;
import com.roncoo.education.user.service.common.dto.auth.AuthUserLogCommissionViewDTO;
import com.roncoo.education.user.service.dao.UserExtDao;
import com.roncoo.education.user.service.dao.UserLogCommissionDao;
import com.roncoo.education.user.service.dao.impl.mapper.entity.UserExt;
import com.roncoo.education.user.service.dao.impl.mapper.entity.UserLogCommission;
import com.roncoo.education.user.service.dao.impl.mapper.entity.UserLogCommissionExample;
import com.roncoo.education.util.base.BaseBiz;
import com.roncoo.education.util.base.Page;
import com.roncoo.education.util.base.PageUtil;
import com.roncoo.education.util.base.Result;
import com.roncoo.education.util.tools.BeanUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @description:
 * @author: husend
 * @time: 2020/5/19
 */
@Component
public class AuthApiUserCommissionLogBiz extends BaseBiz {

    @Autowired
    private UserLogCommissionDao dao;
    @Autowired
    private UserExtDao userExtDao;
    @Autowired
    private IBossOrderInfo bossOrderInfo;

    public Result<Page<AuthUserLogCommissionViewDTO>> getCommissionLogByUserNo(AuthApiUserCommissionLogBO authApiUserCommissionLogBO) {
        UserLogCommissionExample example = new UserLogCommissionExample();
        example.createCriteria().andUserNoEqualTo(authApiUserCommissionLogBO.getUserNo());
        Page<UserLogCommission> page = dao.listForPage(authApiUserCommissionLogBO.getPageCurrent(), authApiUserCommissionLogBO.getPageSize(), example);
        Page<AuthUserLogCommissionViewDTO> dtoPage = PageUtil.transform(page, AuthUserLogCommissionViewDTO.class);

        for (AuthUserLogCommissionViewDTO authUserLogCommissionViewDTO : dtoPage.getList()) {
            UserExt userExt1 = userExtDao.getByUserNo(authUserLogCommissionViewDTO.getUserNo());
            UserExt userExt2 = userExtDao.getByUserNo(authUserLogCommissionViewDTO.getSourceUserNo());
            UserExtVO userExtVO1 = BeanUtil.copyProperties(userExt1, UserExtVO.class);
            UserExtVO userExtVO2 = BeanUtil.copyProperties(userExt2, UserExtVO.class);
            authUserLogCommissionViewDTO.setUserExtVO(userExtVO1);
            authUserLogCommissionViewDTO.setSourceUserExtVO(userExtVO2);
            OrderInfoVO orderInfoVO = bossOrderInfo.getByOrderNo(authUserLogCommissionViewDTO.getOrderNo());
            authUserLogCommissionViewDTO.setOrderInfoVO(orderInfoVO);
        }
        return Result.success(dtoPage);
    }
}
