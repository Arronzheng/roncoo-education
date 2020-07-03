package com.roncoo.education.course.service.api;

import com.roncoo.education.course.service.biz.ApiAssembleBiz;
import com.roncoo.education.course.service.common.bo.AssemblePageBO;
import com.roncoo.education.course.service.common.dto.AssemblePageDTO;
import com.roncoo.education.course.service.common.req.*;
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
@RequestMapping("/course/api/assemble")
    public class ApiAssembleController extends BaseController {

        @Autowired
        private ApiAssembleBiz biz;

        /**
         * Assemble分页列表接口
         * @param assemblePageBO   分页信息
         * @return
         */
        @ApiOperation(value = "assemble分页列表接口", notes = "assemble分页列表接口")
        @RequestMapping(value = "/list", method = RequestMethod.POST)
        public Result<Page<AssemblePageDTO>> list(@RequestBody AssemblePageBO assemblePageBO) {
            return biz.list(assemblePageBO);
        }
    }

