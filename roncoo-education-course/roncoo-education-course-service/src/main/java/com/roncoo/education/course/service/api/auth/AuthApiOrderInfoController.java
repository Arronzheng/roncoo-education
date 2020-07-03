package com.roncoo.education.course.service.api.auth;

import com.roncoo.education.course.service.common.bo.auth.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.github.abel533.echarts.Option;
import com.roncoo.education.course.service.biz.auth.AuthApiOrderInfoBiz;
import com.roncoo.education.course.service.common.bo.OrderInfoCloseBO;
import com.roncoo.education.course.service.common.dto.auth.AuthOrderInfoDTO;
import com.roncoo.education.course.service.common.dto.auth.AuthOrderInfoListDTO;
import com.roncoo.education.course.service.common.dto.auth.AuthOrderInfoListForLecturerDTO;
import com.roncoo.education.course.service.common.dto.auth.AuthOrderPayDTO;
import com.roncoo.education.util.base.BaseController;
import com.roncoo.education.util.base.Page;
import com.roncoo.education.util.base.Result;

import io.swagger.annotations.ApiOperation;

/**
 * 订单信息表
 *
 * @author YZJ
 */
@RestController
@RequestMapping(value = "/course/auth/order/info")
public class AuthApiOrderInfoController extends BaseController {

	@Autowired
	private AuthApiOrderInfoBiz biz;

	/**
	 * 订单列表接口
	 */
	@ApiOperation(value = "订单列表接口", notes = "根据条件分页列出订单信息")
	@RequestMapping(value = "/list", method = RequestMethod.POST)
	public Result<Page<AuthOrderInfoListDTO>> listForPage(@RequestBody AuthOrderInfoListBO authOrderInfoListBO) {
		return biz.listForPage(authOrderInfoListBO);
	}

	/**
	 * 课程下单接口
	 */
	@ApiOperation(value = "课程下单接口", notes = "用户购买课程下单接口")
	@RequestMapping(value = "/pay", method = RequestMethod.POST)
	public Result<AuthOrderPayDTO> pay(@RequestBody AuthOrderPayBO authOrderPayBO) throws Exception {
		return biz.pay(authOrderPayBO);
	}

	/**
	 * 订单继续支付接口
	 */
	@ApiOperation(value = "订单继续支付接口", notes = "订单继续支付接口")
	@RequestMapping(value = "/continue/pay", method = RequestMethod.POST)
	public Result<AuthOrderPayDTO> continuePay(@RequestBody AuthOrderInfoContinuePayBO authOrderInfoContinuePayBO) throws Exception {
		return biz.continuePay(authOrderInfoContinuePayBO);
	}

	/**
	 * 关闭订单接口
	 */
	@ApiOperation(value = "关闭订单接口", notes = "关闭订单接口")
	@RequestMapping(value = "/close", method = RequestMethod.POST)
	public Result<String> close(@RequestBody OrderInfoCloseBO orderInfoCloseBO) {
		return biz.close(orderInfoCloseBO);
	}

	/**
	 * 订单详情信息接口
	 *
	 * @param authOrderInfoViewBO
	 * @return
	 */
	@ApiOperation(value = "订单详情信息接口", notes = "订单详情信息接口")
	@RequestMapping(value = "/view", method = RequestMethod.POST)
	public Result<AuthOrderInfoDTO> view(@RequestBody AuthOrderInfoViewBO authOrderInfoViewBO) {
		return biz.view(authOrderInfoViewBO);
	}

	/**
	 * 补充订单地址接口
	 *
	 * @param authOrderInfoUpdateBO
	 * @return
	 */
	@ApiOperation(value = "补充订单地址接口", notes = "补充订单地址接口")
	@RequestMapping(value = "/complete", method = RequestMethod.POST)
	public Result<Integer> completeOrderSite(@RequestBody AuthOrderInfoUpdateBO authOrderInfoUpdateBO){
		return biz.completeOrderSite(authOrderInfoUpdateBO);
	}

	/**
	 * 讲师订单收益列表
	 *
	 * @param authOrderInfoListBO
	 * @return
	 */
	@ApiOperation(value = "讲师订单收益列表接口", notes = "讲师订单收益列表接口")
	@RequestMapping(value = "/lecturer", method = RequestMethod.POST)
	public Result<Page<AuthOrderInfoListForLecturerDTO>> list(@RequestBody AuthOrderInfoListBO authOrderInfoListBO) {
		return biz.list(authOrderInfoListBO);
	}

	/**
	 * 讲师收益折线图
	 *
	 * @param authOrderInfoForChartsBO
	 * @return
	 */
	@ApiOperation(value = "讲师收益折线图", notes = "讲师收益折线图")
	@RequestMapping(value = "/charts", method = RequestMethod.POST)
	public Result<Option> charts(@RequestBody AuthOrderInfoForChartsBO authOrderInfoForChartsBO) {
		return biz.charts(authOrderInfoForChartsBO);
	}

}
