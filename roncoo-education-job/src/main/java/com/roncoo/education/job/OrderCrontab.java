package com.roncoo.education.job;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.roncoo.education.course.feign.IBossOrderInfo;
import com.roncoo.education.util.base.BaseController;

/**
 * 定时任务-订单处理
 * 
 * @author wuyun
 */
@Component
public class OrderCrontab extends BaseController {

	private static final Object KEY = new Object();

	private static boolean taskFlag = false;

	@Autowired
	private IBossOrderInfo bossOrderInfo;

	/**
	 * 定时任务每分钟执行一次
	 */
	@Scheduled(fixedRate = 60000)
	public void orderCancel() {
		synchronized (KEY) {
			if (OrderCrontab.taskFlag) {
				logger.warn("订单处理-任务已经启动");
				return;
			}
			OrderCrontab.taskFlag = true;
		}
		logger.warn("订单处理-定时任务开始");

		try {
			bossOrderInfo.handleScheduledTasks();
		} catch (Exception e) {
			logger.error("定时任务-订单处理-执行出错", e);
		}
		OrderCrontab.taskFlag = false;
	}

}
