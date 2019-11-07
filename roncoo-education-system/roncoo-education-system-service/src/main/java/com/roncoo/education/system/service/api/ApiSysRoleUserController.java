package com.roncoo.education.system.service.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.roncoo.education.system.service.biz.ApiSysRoleUserBiz;
import com.roncoo.education.util.base.BaseController;

/**
 * 角色用户关联表
 *
 * @author wujing
 */
@RestController
public class ApiSysRoleUserController extends BaseController {

	@Autowired
	private ApiSysRoleUserBiz biz;

}
