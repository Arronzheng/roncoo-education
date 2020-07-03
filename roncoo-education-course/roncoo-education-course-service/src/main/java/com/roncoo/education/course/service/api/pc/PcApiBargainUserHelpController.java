package com.roncoo.education.course.service.api.pc;

import com.roncoo.education.course.service.biz.pc.PcApiBargainUserHelpBiz;
import com.roncoo.education.course.service.common.req.*;
import com.roncoo.education.course.service.common.resq.BargainUserHelpPageRESQ;
import com.roncoo.education.course.service.common.resq.BargainUserHelpViewRESQ;
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
 * 砍价用户帮助表 前端控制器
 *
 * @author husend
 * @since 2020-04-14
 */
@RestController
@RequestMapping("/course/bargainUserHelp")
    public class PcApiBargainUserHelpController extends BaseController {

        @Autowired
        private PcApiBargainUserHelpBiz biz;

        /**
         * BargainUserHelp分页列表接口
         * @param bargainUserHelpPageREQ   分页信息
         * @return
         */
        @ApiOperation(value = "bargainUserHelp分页列表接口", notes = "bargainUserHelp分页列表接口")
        @RequestMapping(value = "/list", method = RequestMethod.POST)
        public Result<Page<BargainUserHelpPageRESQ>> list(@RequestBody BargainUserHelpPageREQ bargainUserHelpPageREQ) {
            return biz.list(bargainUserHelpPageREQ);
        }

        /**
        * BargainUserHelp添加接口
        * @param bargainUserHelpSaveREQ
        * @return
        */
        @ApiOperation(value = "bargainUserHelp添加接口", notes = "bargainUserHelp添加接口")
        @RequestMapping(value = "/save", method = RequestMethod.POST)
        public Result<Integer> save(@RequestBody BargainUserHelpSaveREQ bargainUserHelpSaveREQ) {
            return biz.save(bargainUserHelpSaveREQ);
        }

        /**
         * BargainUserHelp更新接口
         * @param bargainUserHelpUpdateREQ
         * @return
         */
        @ApiOperation(value = "bargainUserHelp更新接口", notes = "bargainUserHelp更新接口")
        @RequestMapping(value = "/update", method = RequestMethod.POST)
        public Result<Integer> update(@RequestBody BargainUserHelpUpdateREQ bargainUserHelpUpdateREQ) {
            return biz.update(bargainUserHelpUpdateREQ);
        }

        /**
         * BargainUserHelp删除接口
         * @param bargainUserHelpDeleteREQ
         * @return
         */
        @ApiOperation(value = "bargainUserHelp删除接口", notes = "bargainUserHelp删除接口")
        @RequestMapping(value = "/delete", method = RequestMethod.POST)
        public Result<Integer> delete(@RequestBody BargainUserHelpDeleteREQ bargainUserHelpDeleteREQ) {
            return biz.delete(bargainUserHelpDeleteREQ);
        }

        /**
         * BargainUserHelp查看接口
         * @param bargainUserHelpViewREQ
         * @return
         */
        @ApiOperation(value = "bargainUserHelp查看接口", notes = "bargainUserHelp查看接口")
        @RequestMapping(value = "/view", method = RequestMethod.POST)
        public Result<BargainUserHelpViewRESQ> view(@RequestBody BargainUserHelpViewREQ bargainUserHelpViewREQ) {
            return biz.view(bargainUserHelpViewREQ);
        }


    }

