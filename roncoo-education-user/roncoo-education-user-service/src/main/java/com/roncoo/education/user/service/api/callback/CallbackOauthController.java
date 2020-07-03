package com.roncoo.education.user.service.api.callback;

import com.roncoo.education.user.service.biz.callback.CallbackOauthBiz;
import com.roncoo.education.user.service.common.WeChatUtil;
import com.roncoo.education.user.service.common.bo.WeCheckBO;
import com.roncoo.education.user.service.common.dto.UserLoginDTO;
import com.roncoo.education.util.base.BaseController;
import com.roncoo.education.util.base.Result;
import com.roncoo.education.util.pay.AlipayUtil;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping(value = "/user/api/oauth/callback")
public class CallbackOauthController extends BaseController {

    @Autowired
    private CallbackOauthBiz biz;

    @GetMapping("/getCode")
    public Result<UserLoginDTO> getCode(@RequestParam(value = "code", required = false) String code, @RequestParam(value = "state", required = false) String state){
        return biz.getCode(code, state);
    }

}
