package com.roncoo.education.system.service.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;

import com.roncoo.education.system.common.bean.qo.SysRoleUserQO;
import com.roncoo.education.system.common.bean.vo.SysRoleUserVO;
import com.roncoo.education.system.common.interfaces.BossSysRoleUser;
import com.roncoo.education.system.service.controller.biz.BossSysRoleUserBiz;
import com.roncoo.education.util.base.BaseController;
import com.roncoo.education.util.base.Page;

/**
 * 角色用户关联表 
 *
 * @author wujing
 */
@RestController
public class BossSysRoleUserController extends BaseController implements BossSysRoleUser{

	@Autowired
	private BossSysRoleUserBiz biz;
	
	@Override
	public Page<SysRoleUserVO> listForPage(@RequestBody SysRoleUserQO qo){
		return biz.listForPage(qo);
	}

    @Override
	public int save(@RequestBody SysRoleUserQO qo){
		return biz.save(qo);
	}

    @Override	
	public int deleteById(@RequestBody Long id){
		return biz.deleteById(id);
	}
	
    @Override	
	public int updateById(@RequestBody SysRoleUserQO qo){
		return biz.updateById(qo);
	}
	
    @Override
	public SysRoleUserVO getById(@RequestBody Long id){
		return biz.getById(id);
	}
	
}
