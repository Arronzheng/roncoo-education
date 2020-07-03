package com.roncoo.education.user.service.api.auth;

import com.roncoo.education.user.service.biz.auth.AuthApiSvipOrderBiz;
import com.roncoo.education.user.service.biz.pc.PcApiSvipBiz;
import com.roncoo.education.user.service.common.bo.auth.AuthSvipOrderBO;
import com.roncoo.education.user.service.common.bo.auth.AuthSvipOrderViewBO;
import com.roncoo.education.user.service.common.dto.auth.AuthSvipOrderDTO;
import com.roncoo.education.user.service.common.dto.auth.AuthSvipOrderViewDTO;
import com.roncoo.education.util.base.BaseController;
import com.roncoo.education.util.base.Result;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * 会员信息表 前端控制器
 *
 * @author ${author}
 * @since 2019-12-17
 */
@RestController
@RequestMapping("/user/auth/svip")
    public class AuthApiSvipOrderController extends BaseController {

        @Autowired
        private AuthApiSvipOrderBiz biz;

        /**
        * Svip添加接口
        * @param authSvipOrderBO
        * @return
        */
        @ApiOperation(value = "svip下单接口", notes = "svip下单接口")
        @RequestMapping(value = "/pay", method = RequestMethod.POST)
        public Result<AuthSvipOrderDTO> save(@RequestBody AuthSvipOrderBO authSvipOrderBO) throws Exception {
            return biz.pay(authSvipOrderBO);
        }

        /**
         * Svip添加接口
         * @param authSvipOrderViewBO
         * @return
         */
        @ApiOperation(value = "svip下单接口", notes = "svip下单接口")
        @RequestMapping(value = "/view", method = RequestMethod.POST)
        public Result<AuthSvipOrderViewDTO> view(@RequestBody AuthSvipOrderViewBO authSvipOrderViewBO) {
            return biz.view(authSvipOrderViewBO);
        }

    }

