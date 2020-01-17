package com.roncoo.education.user.service.api.pc;

import com.roncoo.education.user.service.biz.pc.PcApiSvipBuyLogBiz;
import com.roncoo.education.user.service.common.req.*;
import com.roncoo.education.user.service.common.resq.SvipBuyLogPageRESQ;
import com.roncoo.education.user.service.common.resq.SvipBuyLogViewRESQ;
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
 * 会员购买日志表 前端控制器
 *
 * @author ${author}
 * @since 2019-12-25
 */
@RestController
@RequestMapping("/user/pc/svipBuyLog")
    public class PcApiSvipBuyLogController extends BaseController {

        @Autowired
        private PcApiSvipBuyLogBiz biz;

        /**
         * SvipBuyLog分页列表接口
         * @param svipBuyLogPageREQ   分页信息
         * @return
         */
        @ApiOperation(value = "svipBuyLog分页列表接口", notes = "svipBuyLog分页列表接口")
        @RequestMapping(value = "/list", method = RequestMethod.POST)
        public Result<Page<SvipBuyLogPageRESQ>> list(@RequestBody SvipBuyLogPageREQ svipBuyLogPageREQ) {
            return biz.list(svipBuyLogPageREQ);
        }

        /**
        * SvipBuyLog添加接口
        * @param svipBuyLogSaveREQ
        * @return
        */
        @ApiOperation(value = "svipBuyLog添加接口", notes = "svipBuyLog添加接口")
        @RequestMapping(value = "/save", method = RequestMethod.POST)
        public Result<Integer> save(@RequestBody SvipBuyLogSaveREQ svipBuyLogSaveREQ) {
            return biz.save(svipBuyLogSaveREQ);
        }

        /**
         * SvipBuyLog更新接口
         * @param svipBuyLogUpdateREQ
         * @return
         */
        @ApiOperation(value = "svipBuyLog更新接口", notes = "svipBuyLog更新接口")
        @RequestMapping(value = "/update", method = RequestMethod.POST)
        public Result<Integer> update(@RequestBody SvipBuyLogUpdateREQ svipBuyLogUpdateREQ) {
            return biz.update(svipBuyLogUpdateREQ);
        }

        /**
         * SvipBuyLog删除接口
         * @param svipBuyLogDeleteREQ
         * @return
         */
        @ApiOperation(value = "svipBuyLog删除接口", notes = "svipBuyLog删除接口")
        @RequestMapping(value = "/delete", method = RequestMethod.POST)
        public Result<Integer> delete(@RequestBody SvipBuyLogDeleteREQ svipBuyLogDeleteREQ) {
            return biz.delete(svipBuyLogDeleteREQ);
        }

        /**
         * SvipBuyLog查看接口
         * @param svipBuyLogViewREQ
         * @return
         */
        @ApiOperation(value = "svipBuyLog查看接口", notes = "svipBuyLog查看接口")
        @RequestMapping(value = "/view", method = RequestMethod.POST)
        public Result<SvipBuyLogViewRESQ> view(@RequestBody SvipBuyLogViewREQ svipBuyLogViewREQ) {
            return biz.view(svipBuyLogViewREQ);
        }


    }

