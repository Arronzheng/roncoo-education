package com.roncoo.education.course.common.interfaces;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 拼团信息表
 */
public interface BossAssemble {

	/**
	 * 拼团倒计时定时任务
	 */
	@RequestMapping(value = "/boss/course/assemble/handleScheduledTasks", method = RequestMethod.POST)
	int handleScheduledTasks();

}
