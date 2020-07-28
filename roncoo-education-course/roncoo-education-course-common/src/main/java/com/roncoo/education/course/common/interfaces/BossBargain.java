package com.roncoo.education.course.common.interfaces;

import com.roncoo.education.course.common.bean.qo.OrderEchartsQO;
import com.roncoo.education.course.common.bean.qo.OrderInfoQO;
import com.roncoo.education.course.common.bean.vo.CountIncomeVO;
import com.roncoo.education.course.common.bean.vo.OrderEchartsVO;
import com.roncoo.education.course.common.bean.vo.OrderInfoVO;
import com.roncoo.education.course.common.bean.vo.OrderReportVO;
import com.roncoo.education.util.base.Page;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * 砍价信息表
 */
public interface BossBargain {

	/**
	 * 砍价倒计时定时任务
	 *
	 *
	 */
	@RequestMapping(value = "/boss/course/bargain/handleScheduledTasks", method = RequestMethod.POST)
	int handleScheduledTasks();

}
