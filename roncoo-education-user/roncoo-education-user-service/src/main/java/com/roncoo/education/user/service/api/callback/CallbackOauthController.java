package com.roncoo.education.user.service.api.callback;

import com.roncoo.education.user.service.biz.callback.CallbackOauthBiz;
import com.roncoo.education.user.service.common.dto.UserLoginDTO;
import com.roncoo.education.util.base.BaseController;
import com.roncoo.education.util.base.Result;
import com.roncoo.education.util.pay.AlipayUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
