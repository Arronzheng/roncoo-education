package com.roncoo.education.course.service.api.auth;

import com.roncoo.education.course.service.biz.auth.AuthApiBargainUserBiz;
import com.roncoo.education.course.service.biz.auth.AuthApiBargainUserHelpBiz;
import com.roncoo.education.course.service.common.bo.BargainUserHelpSaveBO;
import com.roncoo.education.course.service.common.dto.BargainUserViewDTO;
import com.roncoo.education.course.service.common.req.BargainUserHelpREQ;
import com.roncoo.education.course.service.common.req.BargainUserSaveREQ;
import com.roncoo.education.course.service.common.req.BargainUserViewREQ;
import com.roncoo.education.course.service.common.resq.BargainUserPageRESQ;
import com.roncoo.education.util.base.BaseController;
import com.roncoo.education.util.base.Result;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 砍价表 前端控制器
 *
 * @author husend
 * @since 2020-04-14
 */
@RestController
@RequestMapping("/course/auth/bargain/user")
public class AuthApiBargainUserHelpController extends BaseController {

    @Autowired
    private AuthApiBargainUserHelpBiz biz;

    @ApiOperation(value = "查询是否帮助砍价", notes = "查询是否帮助砍价")
    @PostMapping("/getBargainHelpByUid")
    public Result<Boolean> getBargainHelpByUid(@RequestBody BargainUserHelpSaveBO bargainUserHelpSaveBO){
        return biz.getBargainHelpByUid(bargainUserHelpSaveBO);
    }

}

