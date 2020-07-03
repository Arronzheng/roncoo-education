package com.roncoo.education.course.service.controller;

import com.roncoo.education.course.common.interfaces.BossBargain;
import com.roncoo.education.course.service.controller.biz.BossBargainBiz;
import com.roncoo.education.util.base.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

/**
 * 砍价信息表
 */
@RestController
public class BossBargainController extends BaseController implements BossBargain {

	@Autowired
	private BossBargainBiz biz;

	@Override
	public int handleScheduledTasks() {
		return biz.handleScheduledTasks();
	}

}
