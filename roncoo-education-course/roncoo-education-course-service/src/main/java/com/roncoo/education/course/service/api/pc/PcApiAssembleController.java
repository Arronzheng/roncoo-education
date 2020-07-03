package com.roncoo.education.course.service.api.pc;

import com.roncoo.education.course.service.biz.ApiAssembleBiz;
import com.roncoo.education.course.service.biz.pc.PcApiAssembleBiz;
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
@RequestMapping("/course/pc/assemble")
    public class PcApiAssembleController extends BaseController {

        @Autowired
        private PcApiAssembleBiz biz;

        /**
         * Assemble分页列表接口
         * @param assemblePageREQ   分页信息
         * @return
         */
        @ApiOperation(value = "assemble分页列表接口", notes = "assemble分页列表接口")
        @RequestMapping(value = "/list", method = RequestMethod.POST)
        public Result<Page<AssemblePageRESQ>> list(@RequestBody AssemblePageREQ assemblePageREQ) {
            return biz.list(assemblePageREQ);
        }

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
         * @param assembleViewREQ
         * @return
         */
        @ApiOperation(value = "assemble查看接口", notes = "assemble查看接口")
        @RequestMapping(value = "/view", method = RequestMethod.POST)
        public Result<AssembleViewRESQ> view(@RequestBody AssembleViewREQ assembleViewREQ) {
            return biz.view(assembleViewREQ);
        }


    }

