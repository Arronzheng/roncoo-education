package com.roncoo.education.user.service.api.auth;

import com.roncoo.education.user.service.biz.pc.PcApiSvipBiz;
import com.roncoo.education.user.service.common.bo.auth.AuthSvipOrderBO;
import com.roncoo.education.user.service.common.req.*;
import com.roncoo.education.user.service.common.resq.SvipPageRESQ;
import com.roncoo.education.user.service.common.resq.SvipViewRESQ;
import com.roncoo.education.util.base.BaseController;
import com.roncoo.education.util.base.Page;
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
@RequestMapping("/user/svip")
    public class AuthApiSvipController extends BaseController {

        @Autowired
        private PcApiSvipBiz biz;

    }

