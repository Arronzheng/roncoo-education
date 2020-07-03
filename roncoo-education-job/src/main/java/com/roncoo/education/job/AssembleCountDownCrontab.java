package com.roncoo.education.job;

import com.roncoo.education.course.feign.IBossAssemble;
import com.roncoo.education.system.feign.IBossMsg;
import com.roncoo.education.util.base.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * 拼团倒计时-定时发送
 *
 */
@Component
public class AssembleCountDownCrontab extends BaseController {

	private static final Object KEY = new Object();
	private static boolean taskFlag = false;

	@Autowired
	private IBossAssemble bossAssemble;

	/**
	 * 定时任务每分钟执行一次
	 *
	 * @author wuyun
	 */
	@Scheduled(fixedRate = 60000)
	public void pushCancel() {
		synchronized (KEY) {
			if (AssembleCountDownCrontab.taskFlag) {
				logger.warn("拼团倒计时-定时发送已经启动");
				return;
			}
			AssembleCountDownCrontab.taskFlag = true;
		}

		try {
			bossAssemble.handleScheduledTasks();
		} catch (Exception e) {
			logger.error("拼团倒计时-定时发送-执行出错", e);
		}

		AssembleCountDownCrontab.taskFlag = false;

		logger.warn("拼团倒计时-定时发送-任务完成");
	}
}
