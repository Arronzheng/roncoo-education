package com.roncoo.education.user.service.api.auth;

import com.roncoo.education.user.service.biz.auth.AuthApiUserInviteLogBiz;
import com.roncoo.education.user.service.common.bo.auth.AuthApiUserInviteLogBO;
import com.roncoo.education.user.service.common.resq.UserExtViewRESQ;
import com.roncoo.education.util.base.BaseController;
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
@RequestMapping("/user/auth/user/log/invite")
public class AuthApiUserInviteLogController extends BaseController {

    @Autowired
    private AuthApiUserInviteLogBiz biz;

    @ApiOperation(value = "查询推荐人", notes = "查询推荐人")
    @PostMapping("/getInviteUser")
    public Result<UserExtViewRESQ> getInviteUser(@RequestBody AuthApiUserInviteLogBO authApiUserInviteLogBO) {
        return biz.getInviteUser(authApiUserInviteLogBO);
    }
}
