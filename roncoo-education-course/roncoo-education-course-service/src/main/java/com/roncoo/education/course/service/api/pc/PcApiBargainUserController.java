package com.roncoo.education.course.service.api.pc;

import com.roncoo.education.course.service.biz.pc.PcApiBargainUserBiz;
import com.roncoo.education.course.service.common.req.*;
import com.roncoo.education.course.service.common.resq.BargainUserPageRESQ;
import com.roncoo.education.course.service.common.resq.BargainUserViewRESQ;
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
 * 用户参与砍价表 前端控制器
 *
 * @author husend
 * @since 2020-04-14
 */
@RestController
@RequestMapping("/course/bargainUser")
    public class PcApiBargainUserController extends BaseController {

        @Autowired
        private PcApiBargainUserBiz biz;

        /**
         * BargainUser分页列表接口
         * @param bargainUserPageREQ   分页信息
         * @return
         */
        @ApiOperation(value = "bargainUser分页列表接口", notes = "bargainUser分页列表接口")
        @RequestMapping(value = "/list", method = RequestMethod.POST)
        public Result<Page<BargainUserPageRESQ>> list(@RequestBody BargainUserPageREQ bargainUserPageREQ) {
            return biz.list(bargainUserPageREQ);
        }

        /**
        * BargainUser添加接口
        * @param bargainUserSaveREQ
        * @return
        */
        @ApiOperation(value = "bargainUser添加接口", notes = "bargainUser添加接口")
        @RequestMapping(value = "/save", method = RequestMethod.POST)
        public Result<Integer> save(@RequestBody BargainUserSaveREQ bargainUserSaveREQ) {
            return biz.save(bargainUserSaveREQ);
        }

        /**
         * BargainUser更新接口
         * @param bargainUserUpdateREQ
         * @return
         */
        @ApiOperation(value = "bargainUser更新接口", notes = "bargainUser更新接口")
        @RequestMapping(value = "/update", method = RequestMethod.POST)
        public Result<Integer> update(@RequestBody BargainUserUpdateREQ bargainUserUpdateREQ) {
            return biz.update(bargainUserUpdateREQ);
        }

        /**
         * BargainUser删除接口
         * @param bargainUserDeleteREQ
         * @return
         */
        @ApiOperation(value = "bargainUser删除接口", notes = "bargainUser删除接口")
        @RequestMapping(value = "/delete", method = RequestMethod.POST)
        public Result<Integer> delete(@RequestBody BargainUserDeleteREQ bargainUserDeleteREQ) {
            return biz.delete(bargainUserDeleteREQ);
        }

        /**
         * BargainUser查看接口
         * @param bargainUserViewREQ
         * @return
         */
        @ApiOperation(value = "bargainUser查看接口", notes = "bargainUser查看接口")
        @RequestMapping(value = "/view", method = RequestMethod.POST)
        public Result<BargainUserViewRESQ> view(@RequestBody BargainUserViewREQ bargainUserViewREQ) {
            return biz.view(bargainUserViewREQ);
        }


    }

