package com.roncoo.education.user.service.api;

import com.roncoo.education.user.service.common.WeChatUtil;
import com.roncoo.education.user.service.common.bo.*;
import com.roncoo.education.user.service.common.dto.JssdkConfigDTO;
import com.roncoo.education.user.service.common.dto.UserWXLoginDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.roncoo.education.user.service.biz.ApiUserInfoBiz;
import com.roncoo.education.user.service.common.bo.auth.UserUpdateBO;
import com.roncoo.education.user.service.common.dto.UserLoginDTO;
import com.roncoo.education.util.base.BaseController;
import com.roncoo.education.util.base.Result;

import io.swagger.annotations.ApiOperation;

import javax.servlet.http.HttpServletRequest;

/**
 * 用户基本信息
 * @author
 */
@RestController
@RequestMapping(value = "/user/api/user")
public class ApiUserInfoController extends BaseController {

	@Autowired
	private ApiUserInfoBiz biz;

	/**
	 * 注册接口
	 */
	@ApiOperation(value = "注册接口", notes = "注册成功返回登录信息")
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public Result<UserLoginDTO> register(@RequestBody UserRegisterBO userRegisterBO) {
		return biz.register(userRegisterBO);
	}

	/**
	 * 用户密码登录接口
	 */
	@ApiOperation(value = "用户密码登录接口", notes = "用户密码登录")
	@RequestMapping(value = "/login/password", method = RequestMethod.POST)
	public Result<UserLoginDTO> loginPassword(@RequestBody UserLoginPasswordBO userLoginPasswordBO) {
		return biz.loginPassword(userLoginPasswordBO);
	}

	/**
	 * 用户验证码登录接口
	 */
	@ApiOperation(value = "用户验证码登录接口", notes = "用户验证码登录")
	@RequestMapping(value = "/login/code", method = RequestMethod.POST)
	public Result<UserLoginDTO> loginCode(@RequestBody UserLoginCodeBO userLoginCodeBO) {
		return biz.loginCode(userLoginCodeBO);
	}

	/**
	 * 验证码发送接口
	 */
	@ApiOperation(value = "验证码发送接口", notes = "发送手机验证码")
	@RequestMapping(value = "/send/code", method = RequestMethod.POST)
	public Result<String> sendCode(@RequestBody UserSendCodeBO userSendCodeBO) {
		return biz.sendCode(userSendCodeBO);
	}

	/**
	 * 用户修改密码接口
	 *
	 * @author wuyun
	 */
	@ApiOperation(value = "用户修改密码接口", notes = "用户修改密码接口")
	@RequestMapping(value = "/update/password", method = RequestMethod.POST)
	public Result<Integer> updatePassword(@RequestBody UserUpdateBO userUpdateBO) {
		return biz.updatePassword(userUpdateBO);
	}

	/**
	 * 获取开放平台appId
	 */
	@ApiOperation(value = "微信回调参数接口", notes = "获取微信回调参数")
	@RequestMapping(value = "/getAppId", method = RequestMethod.POST)
	public Result<UserWXLoginDTO> getAppId(){
		return biz.getAppId();
	}

	/**
	 * 获取jssdk配置参数
	 * @param url
	 * @return Result<JssdkConfigDTO>
	 */
	@ApiOperation(value = "jssdk配置参数接口", notes = "获取jssdk配置参数")
	@RequestMapping(value = "/config", method = RequestMethod.GET)
	public Result<JssdkConfigDTO> jssdkConfig(String url) {
		return biz.getConfig(url);
	}

	/**
	 * 微信授权登录
	 * @param weChatCodeBO
	 * @return Result<UserLoginDTO>
	 */
	@ApiOperation(value = "微信授权登录接口", notes = "微信授权登录")
	@RequestMapping(value = "/getCode", method = RequestMethod.POST)
	public Result<UserLoginDTO> getCode(@RequestBody WeChatCodeBO weChatCodeBO){
		return biz.getCode(weChatCodeBO);
	}

	/**
	 * 微信二次登录
	 * @param weChatLoginBO
	 * @return Result<UserLoginDTO>
	 */
	@ApiOperation(value = "微信二次登录接口", notes = "微信二次登录")
	@RequestMapping(value = "/wechatLogin", method = RequestMethod.POST)
	public Result<UserLoginDTO> wechatLogin(@RequestBody WeChatLoginBO weChatLoginBO){
		return biz.wechatLogin(weChatLoginBO);
	}

	/**
	 * 授权登录接口
	 */
	@ApiOperation(value = "接入微信", notes = "接入微信")
	@RequestMapping(value = "/auth", method = RequestMethod.GET)
	public String auth(WeCheckBO weCheckBO) {
		if(WeChatUtil.check(weCheckBO.getSignature(), weCheckBO.getTimestamp(), weCheckBO.getNonce())){
			System.out.println("接入成功");
			return weCheckBO.getEchostr();
		}
		return "接入失败";
	}

}
