package com.roncoo.education.user.service.api.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.roncoo.education.user.service.biz.auth.AuthApiUserExtBiz;
import com.roncoo.education.user.service.common.bo.auth.AuthUserExtBO;
import com.roncoo.education.user.service.common.bo.auth.AuthUserExtViewBO;
import com.roncoo.education.user.service.common.dto.auth.AuthUserExtDTO;
import com.roncoo.education.util.base.BaseController;
import com.roncoo.education.util.base.Result;

import io.swagger.annotations.ApiOperation;

/**
 * 用户教育信息
 *
 * @author wujing
 */
@RestController
@RequestMapping(value = "/user/auth/user/ext")
public class AuthApiUserExtController extends BaseController {

	@Autowired
	private AuthApiUserExtBiz biz;

	/**
	 * 用户信息查看接口
	 */
	@ApiOperation(value = "用户信息查看接口", notes = "根据userNo获取用户信息接口")
	@RequestMapping(value = "/view", method = RequestMethod.POST)
	public Result<AuthUserExtDTO> view(@RequestBody AuthUserExtViewBO authUserExtViewBO) {
		return biz.view(authUserExtViewBO);
	}

	/**
	 * 用户信息更新接口
	 */
	@ApiOperation(value = "用户信息更新接口", notes = "用户信息更新接口")
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public Result<AuthUserExtDTO> update(@RequestBody AuthUserExtBO authUserExtBO) {
		return biz.update(authUserExtBO);
	}

}
