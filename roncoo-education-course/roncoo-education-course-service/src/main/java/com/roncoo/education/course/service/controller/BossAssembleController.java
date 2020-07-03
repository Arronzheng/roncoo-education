package com.roncoo.education.course.service.controller;

import com.roncoo.education.course.common.interfaces.BossAssemble;
import com.roncoo.education.course.service.controller.biz.BossAssembleBiz;
import com.roncoo.education.util.base.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

/**
 * 拼团信息表
 */
@RestController
public class BossAssembleController extends BaseController implements BossAssemble {

	@Autowired
	private BossAssembleBiz biz;

	@Override
	public int handleScheduledTasks() throws Exception {
		return biz.handleScheduledTasks();
	}

}
