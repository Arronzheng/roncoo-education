package com.roncoo.education.user.service.api.pc;

import com.roncoo.education.user.service.biz.pc.PcApiUserLogInviteBiz;
import com.roncoo.education.user.service.common.req.*;
import com.roncoo.education.user.service.common.resq.UserLogInvitePageRESQ;
import com.roncoo.education.user.service.common.resq.UserLogInviteViewRESQ;
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
 * 用户邀请记录表 前端控制器
 *
 * @author husend
 * @since 2020-05-19
 */
@RestController
@RequestMapping("/user/pc/api/user/log/invite")
public class PcApiUserLogInviteController extends BaseController {

    @Autowired
    private PcApiUserLogInviteBiz biz;

    /**
     * UserLogInvite分页列表接口
     * @param userLogInvitePageREQ   分页信息
     * @return
     */
    @ApiOperation(value = "userLogInvite分页列表接口", notes = "userLogInvite分页列表接口")
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    public Result<Page<UserLogInvitePageRESQ>> list(@RequestBody UserLogInvitePageREQ userLogInvitePageREQ) {
        return biz.list(userLogInvitePageREQ);
    }

    /**
    * UserLogInvite添加接口
    * @param userLogInviteSaveREQ
    * @return
    */
    @ApiOperation(value = "userLogInvite添加接口", notes = "userLogInvite添加接口")
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public Result<Integer> save(@RequestBody UserLogInviteSaveREQ userLogInviteSaveREQ) {
        return biz.save(userLogInviteSaveREQ);
    }

    /**
     * UserLogInvite更新接口
     * @param userLogInviteUpdateREQ
     * @return
     */
    @ApiOperation(value = "userLogInvite更新接口", notes = "userLogInvite更新接口")
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public Result<Integer> update(@RequestBody UserLogInviteUpdateREQ userLogInviteUpdateREQ) {
        return biz.update(userLogInviteUpdateREQ);
    }

    /**
     * UserLogInvite删除接口
     * @param userLogInviteDeleteREQ
     * @return
     */
    @ApiOperation(value = "userLogInvite删除接口", notes = "userLogInvite删除接口")
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public Result<Integer> delete(@RequestBody UserLogInviteDeleteREQ userLogInviteDeleteREQ) {
        return biz.delete(userLogInviteDeleteREQ);
    }

    /**
     * UserLogInvite查看接口
     * @param userLogInviteViewREQ
     * @return
     */
    @ApiOperation(value = "userLogInvite查看接口", notes = "userLogInvite查看接口")
    @RequestMapping(value = "/view", method = RequestMethod.POST)
    public Result<UserLogInviteViewRESQ> view(@RequestBody UserLogInviteViewREQ userLogInviteViewREQ) {
        return biz.view(userLogInviteViewREQ);
    }


}

