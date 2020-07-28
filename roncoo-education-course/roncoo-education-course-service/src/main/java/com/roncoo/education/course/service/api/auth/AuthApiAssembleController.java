package com.roncoo.education.course.service.api.auth;

import com.roncoo.education.course.service.biz.ApiAssembleBiz;
import com.roncoo.education.course.service.biz.auth.AuthApiAssembleBiz;
import com.roncoo.education.course.service.common.bo.AssembleIngBO;
import com.roncoo.education.course.service.common.bo.AssembleRecordBO;
import com.roncoo.education.course.service.common.bo.AssembleViewBO;
import com.roncoo.education.course.service.common.dto.AssembleViewDTO;
import com.roncoo.education.course.service.common.dto.auth.AuthAssembleViewDTO;
import com.roncoo.education.course.service.common.req.AssemblePageREQ;
import com.roncoo.education.course.service.common.req.AssembleSaveREQ;
import com.roncoo.education.course.service.common.req.AssembleViewREQ;
import com.roncoo.education.course.service.common.resq.AssemblePageRESQ;
import com.roncoo.education.course.service.common.resq.AssembleViewRESQ;
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
 * 拼团表 前端控制器
 *
 * @author husend
 * @since 2020-04-14
 */
@RestController
@RequestMapping("/course/auth/assemble")
    public class AuthApiAssembleController extends BaseController {

        @Autowired
        private AuthApiAssembleBiz biz;

        /**
        * Assemble添加接口
        * @param assembleSaveREQ
        * @return
        */
        @ApiOperation(value = "assemble添加接口", notes = "assemble添加接口")
        @RequestMapping(value = "/save", method = RequestMethod.POST)
        public Result<Integer> save(@RequestBody AssembleSaveREQ assembleSaveREQ) {
            return biz.save(assembleSaveREQ);
        }

        /**
         * Assemble查看接口
         * @param assembleViewBO
         * @return
         */
        @ApiOperation(value = "assemble查看接口", notes = "assemble查看接口")
        @RequestMapping(value = "/view", method = RequestMethod.POST)
        public Result<AuthAssembleViewDTO> view(@RequestBody AssembleViewBO assembleViewBO) {
            return biz.view(assembleViewBO);
        }

    /**
     * Assemble记录接口
     * @param assembleRecordBO
     * @return
     */
    @ApiOperation(value = "Assemble记录接口", notes = "Assemble记录接口")
    @RequestMapping(value = "/record", method = RequestMethod.POST)
    public Result<AuthAssembleViewDTO> record(@RequestBody AssembleRecordBO assembleRecordBO) {
        return biz.record(assembleRecordBO);
    }

    /**
     * Assemble是否有正在拼团接口
     * @param assembleIngBO
     * @return
     */
    @ApiOperation(value = "是否有正在拼团接口", notes = "是否有正在拼团接口")
    @RequestMapping(value = "/isAssemble", method = RequestMethod.POST)
    public Result<Boolean> isAssemble(@RequestBody AssembleIngBO assembleIngBO) {
        return biz.isAssemble(assembleIngBO);
    }

    }

