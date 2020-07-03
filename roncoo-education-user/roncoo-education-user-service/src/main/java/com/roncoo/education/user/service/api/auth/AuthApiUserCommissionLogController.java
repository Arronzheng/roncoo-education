package com.roncoo.education.user.service.api.auth;

import com.roncoo.education.user.service.biz.auth.AuthApiUserCommissionLogBiz;
import com.roncoo.education.user.service.biz.auth.AuthApiUserInviteLogBiz;
import com.roncoo.education.user.service.common.bo.auth.AuthApiUserCommissionLogBO;
import com.roncoo.education.user.service.common.bo.auth.AuthApiUserInviteLogBO;
import com.roncoo.education.user.service.common.dto.auth.AuthUserLogCommissionListDTO;
import com.roncoo.education.user.service.common.dto.auth.AuthUserLogCommissionViewDTO;
import com.roncoo.education.user.service.common.resq.UserExtViewRESQ;
import com.roncoo.education.util.base.BaseController;
import com.roncoo.education.util.base.Page;
import com.roncoo.education.util.base.Result;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description:
 * @author: husend
 * @time: 2020/5/19
 */
@RestController
@RequestMapping("/user/auth/user/log/commission")
public class AuthApiUserCommissionLogController extends BaseController {

    @Autowired
    private AuthApiUserCommissionLogBiz biz;

    @ApiOperation(value = "查询佣金记录", notes = "查询佣金记录")
    @PostMapping("getCommissionInfo")
    public Result<Page<AuthUserLogCommissionViewDTO>> getCommissionLogByUserNo(@RequestBody AuthApiUserCommissionLogBO authApiUserCommissionLogBO) {
        return biz.getCommissionLogByUserNo(authApiUserCommissionLogBO);
    }
}
