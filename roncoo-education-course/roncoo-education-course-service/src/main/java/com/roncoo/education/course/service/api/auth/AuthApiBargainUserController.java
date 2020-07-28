package com.roncoo.education.course.service.api.auth;

import com.roncoo.education.course.service.biz.auth.AuthApiBargainUserBiz;
import com.roncoo.education.course.service.common.bo.AssembleViewBO;
import com.roncoo.education.course.service.common.bo.BargainIngBO;
import com.roncoo.education.course.service.common.bo.BargainRecordBO;
import com.roncoo.education.course.service.common.dto.BargainUserHelpSaveDTO;
import com.roncoo.education.course.service.common.dto.BargainUserViewDTO;
import com.roncoo.education.course.service.common.dto.auth.AuthAssembleViewDTO;
import com.roncoo.education.course.service.common.dto.auth.AuthBargainUserViewDTO;
import com.roncoo.education.course.service.common.req.AssembleSaveREQ;
import com.roncoo.education.course.service.common.req.BargainUserHelpREQ;
import com.roncoo.education.course.service.common.req.BargainUserSaveREQ;
import com.roncoo.education.course.service.common.req.BargainUserViewREQ;
import com.roncoo.education.course.service.common.resq.BargainUserPageRESQ;
import com.roncoo.education.course.service.dao.impl.mapper.entity.BargainUser;
import com.roncoo.education.util.base.BaseController;
import com.roncoo.education.util.base.Result;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * 砍价表 前端控制器
 *
 * @author husend
 * @since 2020-04-14
 */
@RestController
@RequestMapping("/course/auth/bargain")
    public class AuthApiBargainUserController extends BaseController {

        @Autowired
        private AuthApiBargainUserBiz biz;

        /**
        * BargainUser添加接口
        * @param bargainUserSaveREQ
        * @return
        */
        @ApiOperation(value = "BargainUser添加接口", notes = "BargainUser添加接口")
        @RequestMapping(value = "/save", method = RequestMethod.POST)
        public Result<BargainUserPageRESQ> save(@RequestBody BargainUserSaveREQ bargainUserSaveREQ) {
            return biz.save(bargainUserSaveREQ);
        }

        /**
         * BargainUser查看接口
         * @param bargainUserViewREQ
         * @return
         */
        @ApiOperation(value = "BargainUser查看接口", notes = "BargainUser查看接口")
        @RequestMapping(value = "/view", method = RequestMethod.POST)
        public Result<BargainUserViewDTO> view(@RequestBody BargainUserViewREQ bargainUserViewREQ) {
            return biz.view(bargainUserViewREQ);
        }

        /**
         * BargainUser帮砍接口
         * @param bargainUserHelpREQ
         * @return
         */
        @ApiOperation(value = "BargainUser帮砍接口", notes = "BargainUser帮砍接口")
        @RequestMapping(value = "/help", method = RequestMethod.POST)
        public Result<BargainUserHelpSaveDTO> helpBargain(@RequestBody BargainUserHelpREQ bargainUserHelpREQ) {
            return biz.helpBargain(bargainUserHelpREQ);
        }

    /**
     * BargainUser记录接口
     * @param bargainRecordBO
     * @return
     */
    @ApiOperation(value = "BargainUser记录接口", notes = "BargainUser记录接口")
    @RequestMapping(value = "/record", method = RequestMethod.POST)
    public Result<AuthBargainUserViewDTO> record(@RequestBody BargainRecordBO bargainRecordBO) {
        return biz.record(bargainRecordBO);
    }

    /**
     * 是否有正在砍价接口
     * @param bargainIngBO
     * @return
     */
    @ApiOperation(value = "是否有正在砍价接口", notes = "是否有正在砍价接口")
    @RequestMapping(value = "/isBargainIng", method = RequestMethod.POST)
    public Result<BargainUserViewDTO> isBargainIng(@RequestBody BargainIngBO bargainIngBO) {
        return biz.isBargainIng(bargainIngBO);
    }

    }

