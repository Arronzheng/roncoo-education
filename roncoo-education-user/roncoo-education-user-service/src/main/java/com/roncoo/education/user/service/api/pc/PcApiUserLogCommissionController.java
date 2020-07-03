package com.roncoo.education.user.service.api.pc;

import com.roncoo.education.user.service.biz.pc.PcApiUserLogCommissionBiz;
import com.roncoo.education.user.service.common.req.*;
import com.roncoo.education.user.service.common.resq.UserLogCommissionPageRESQ;
import com.roncoo.education.user.service.common.resq.UserLogCommissionViewRESQ;
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
 * 用户佣金记录表 前端控制器
 *
 * @author husend
 * @since 2020-06-11
 */
@RestController
@RequestMapping("/user/userLogCommission")
    public class PcApiUserLogCommissionController extends BaseController {

        @Autowired
        private PcApiUserLogCommissionBiz biz;

        /**
         * UserLogCommission分页列表接口
         * @param userLogCommissionPageREQ   分页信息
         * @return
         */
        @ApiOperation(value = "userLogCommission分页列表接口", notes = "userLogCommission分页列表接口")
        @RequestMapping(value = "/list", method = RequestMethod.POST)
        public Result<Page<UserLogCommissionPageRESQ>> list(@RequestBody UserLogCommissionPageREQ userLogCommissionPageREQ) {
            return biz.list(userLogCommissionPageREQ);
        }

        /**
        * UserLogCommission添加接口
        * @param userLogCommissionSaveREQ
        * @return
        */
        @ApiOperation(value = "userLogCommission添加接口", notes = "userLogCommission添加接口")
        @RequestMapping(value = "/save", method = RequestMethod.POST)
        public Result<Integer> save(@RequestBody UserLogCommissionSaveREQ userLogCommissionSaveREQ) {
            return biz.save(userLogCommissionSaveREQ);
        }

        /**
         * UserLogCommission更新接口
         * @param userLogCommissionUpdateREQ
         * @return
         */
        @ApiOperation(value = "userLogCommission更新接口", notes = "userLogCommission更新接口")
        @RequestMapping(value = "/update", method = RequestMethod.POST)
        public Result<Integer> update(@RequestBody UserLogCommissionUpdateREQ userLogCommissionUpdateREQ) {
            return biz.update(userLogCommissionUpdateREQ);
        }

        /**
         * UserLogCommission删除接口
         * @param userLogCommissionDeleteREQ
         * @return
         */
        @ApiOperation(value = "userLogCommission删除接口", notes = "userLogCommission删除接口")
        @RequestMapping(value = "/delete", method = RequestMethod.POST)
        public Result<Integer> delete(@RequestBody UserLogCommissionDeleteREQ userLogCommissionDeleteREQ) {
            return biz.delete(userLogCommissionDeleteREQ);
        }

        /**
         * UserLogCommission查看接口
         * @param userLogCommissionViewREQ
         * @return
         */
        @ApiOperation(value = "userLogCommission查看接口", notes = "userLogCommission查看接口")
        @RequestMapping(value = "/view", method = RequestMethod.POST)
        public Result<UserLogCommissionViewRESQ> view(@RequestBody UserLogCommissionViewREQ userLogCommissionViewREQ) {
            return biz.view(userLogCommissionViewREQ);
        }


    }

