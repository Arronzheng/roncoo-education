package com.roncoo.education.user.service.api.pc;

import com.roncoo.education.user.service.biz.pc.PcApiSvipBiz;
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
@RequestMapping("/user/pc/svip")
    public class PcApiSvipController extends BaseController {

        @Autowired
        private PcApiSvipBiz biz;

        /**
         * Svip分页列表接口
         * @param svipPageREQ   分页信息
         * @return
         */
        @ApiOperation(value = "svip分页列表接口", notes = "svip分页列表接口")
        @RequestMapping(value = "/list", method = RequestMethod.POST)
        public Result<Page<SvipPageRESQ>> listForPage(@RequestBody SvipPageREQ svipPageREQ) {
            return biz.listForPage(svipPageREQ);
        }

        /**
        * Svip添加接口
        * @param svipSaveREQ
        * @return
        */
        @ApiOperation(value = "svip添加接口", notes = "svip添加接口")
        @RequestMapping(value = "/save", method = RequestMethod.POST)
        public Result<Integer> save(@RequestBody SvipSaveREQ svipSaveREQ) {
            return biz.save(svipSaveREQ);
        }

        /**
         * Svip更新接口
         * @param svipUpdateREQ
         * @return
         */
        @ApiOperation(value = "svip更新接口", notes = "svip更新接口")
        @RequestMapping(value = "/update", method = RequestMethod.POST)
        public Result<Integer> update(@RequestBody SvipUpdateREQ svipUpdateREQ) {
            return biz.update(svipUpdateREQ);
        }

        /**
         * Svip删除接口
         * @param svipDeleteREQ
         * @return
         */
        @ApiOperation(value = "svip删除接口", notes = "svip删除接口")
        @RequestMapping(value = "/delete", method = RequestMethod.POST)
        public Result<Integer> delete(@RequestBody SvipDeleteREQ svipDeleteREQ) {
            return biz.delete(svipDeleteREQ);
        }

        /**
         * Svip查看接口
         * @param svipViewREQ
         * @return
         */
        @ApiOperation(value = "svip查看接口", notes = "svip查看接口")
        @RequestMapping(value = "/view", method = RequestMethod.POST)
        public Result<SvipViewRESQ> view(@RequestBody SvipViewREQ svipViewREQ) {
            return biz.view(svipViewREQ);
        }


    }

