package com.roncoo.education.course.service.biz.callback;

import com.github.wxpay.sdk.WXPay;
import com.github.wxpay.sdk.WXPayUtil;
import com.roncoo.education.course.service.common.bo.OrderInfoPayNotifyBO;
import com.roncoo.education.course.service.dao.CourseAuditDao;
import com.roncoo.education.course.service.dao.CourseDao;
import com.roncoo.education.course.service.dao.OrderInfoDao;
import com.roncoo.education.course.service.dao.OrderPayDao;
import com.roncoo.education.course.service.dao.impl.mapper.entity.Course;
import com.roncoo.education.course.service.dao.impl.mapper.entity.CourseAudit;
import com.roncoo.education.course.service.dao.impl.mapper.entity.OrderInfo;
import com.roncoo.education.course.service.dao.impl.mapper.entity.OrderPay;
import com.roncoo.education.user.common.bean.qo.LecturerExtQO;
import com.roncoo.education.user.common.bean.qo.UserExtQO;
import com.roncoo.education.user.common.bean.qo.UserLogCommissionSaveQO;
import com.roncoo.education.user.common.bean.vo.*;
import com.roncoo.education.user.feign.*;
import com.roncoo.education.util.base.BaseBiz;
import com.roncoo.education.util.base.Result;
import com.roncoo.education.util.enums.OrderStatusEnum;
import com.roncoo.education.util.pay.AlipayUtil;
import com.roncoo.education.util.pay.WeixinConfig;
import com.roncoo.education.util.pay.WeixinPayUtil;
import com.roncoo.education.util.tools.BeanUtil;
import com.xiaoleilu.hutool.util.ObjectUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Map;

/**
 *
 */
@Component
public class CallbackAlipayBiz extends BaseBiz {

	@Autowired
	private OrderInfoDao orderInfoDao;
	@Autowired
	private OrderPayDao orderPayDao;
	@Autowired
	private CourseAuditDao courseAuditDao;
	@Autowired
	private IBossLecturer bossLecturer;
	@Autowired
	private IBossLecturerExt bossLecturerExt;
	@Autowired
	private IBossSvipOrder bossSvipOrder;
	@Autowired
	private IBossUserExt bossUserExt;
	@Autowired
	private IBossUserLogInvite bossUserLogInvite;
	@Autowired
	private IBossUserLogCommission bossUserLogCommission;
	@Transactional
	public String alipayNotify(HttpServletRequest request) {
		boolean signVerified = AlipayUtil.checkSign(request.getParameterMap());
		if(signVerified){//验证成功
			String out_trade_no = request.getParameter("out_trade_no"); //商户订单号
			String trade_status = request.getParameter("trade_status");//交易状态
			String body = request.getParameter("body");//商品描述
			if("course".equals(body)) {
				if(handleCourseOrder(out_trade_no, trade_status)){
					return "success";
				}else{
					return "fail";
				}
			}else{
				if(handleVipOrder(out_trade_no, trade_status)){
					return "success";
				}else{
					return "fail";
				}
			}
		}else{
			logger.info("支付宝支付通知-验签失败");
			return "fail";
		}
	}

	public String weixinNotify(HttpServletRequest request) {
		String fail="<xml><return_code><![CDATA[FAIL]]></return_code><return_msg><![CDATA[FAIL]]></return_msg></xml>";
		String success="<xml><return_code><![CDATA[SUCCESS]]></return_code><return_msg><![CDATA[OK]]></return_msg></xml>";
		String notifyData = WeixinPayUtil.readData(request);
		WeixinConfig config = new WeixinConfig();
		WXPay wxpay = new WXPay(config);
		boolean result = false;
		try {
			Map<String, String> notifyMap = WXPayUtil.xmlToMap(notifyData);  // 转换成map
			result = wxpay.isPayResultNotifySignatureValid(notifyMap);
			if(result){
				String out_trade_no = notifyMap.get("out_trade_no"); //商户订单号
				String pay_time = notifyMap.get("time_end");//交易时间
				String attach = request.getParameter("attach");//商品描述
//				String return_code = new String(notifyMap.get("return_code"));
//				String result_code = new String(notifyMap.get("result_code"));
//				String trade_state = new String(notifyMap.get("trade_state"));
				String trade_status;
				if("SUCCESS".equals(notifyMap.get("return_code")) && "SUCCESS".equals(notifyMap.get("result_code"))){
					trade_status = "TRADE_SUCCESS";
				}else if("NOTPAY".equals(notifyMap.get("trade_state"))){
					trade_status = "WAIT_BUYER_PAY";
				}else{
					trade_status = "TRADE_FAIL";
				}
				//判断是否是课程订单
				if("course".equals(attach)) {
					if(handleCourseOrder(out_trade_no, trade_status)){
						return success;
					}else{
						return fail;
					}
				}else{
					if(handleVipOrder(out_trade_no, trade_status)){
						return success;
					}else{
						return fail;
					}
				}
			}else{
				logger.info("微信支付通知-验签失败");
				return fail;
			}
		} catch (Exception e) {
			logger.error("微信支付通知-验签失败，resp={}", e.getMessage());
			return fail;
		}
	}

	/**
	 * 课程订单处理
	 * @param out_trade_no 订单号
	 * @param trade_status 交易状态
	 * @return
	 */
	private boolean handleCourseOrder(String out_trade_no, String trade_status){
		OrderPay orderPay = orderPayDao.getBySerialNumber(Long.valueOf(out_trade_no));
		if (ObjectUtil.isNull(orderPay)) {
			return false; //告诉支付宝成功或失败
		}
		OrderInfo orderInfo = orderInfoDao.getByOrderNo(orderPay.getOrderNo());
		if (ObjectUtil.isNull(orderInfo)) {
			return false;
		}
		// 如果订单状态不是待支付状态证明订单已经处理过,不用再处理
		if (!OrderStatusEnum.WAIT.getCode().equals(orderInfo.getOrderStatus())) {
			return true;
		}
		// 订单状态为成功时处理
		if (trade_status.equals("TRADE_FINISHED") || trade_status.equals("TRADE_SUCCESS")) {
			//交易结束，不可退款 或 交易支付成功
			// 处理课程信息
			course(orderInfo, orderPay);
		} else if(trade_status.equals("WAIT_BUYER_PAY")){
			//不做处理
		}else{
			// 更新订单信息
			orderInfo.setOrderStatus(OrderStatusEnum.FAIL.getCode());
			orderInfoDao.updateById(orderInfo);
			// 更新订单支付信息
			orderPay.setOrderStatus(OrderStatusEnum.FAIL.getCode());
			orderPayDao.updateById(orderPay);
		}
		return true;
	}

	/**
	 * 会员订单处理
	 * @param out_trade_no
	 * @param trade_status
	 * @return
	 */
	private boolean handleVipOrder(String out_trade_no, String trade_status){
		SvipOrderVO svipOrderVO = bossSvipOrder.getBySerialNo(Long.valueOf(out_trade_no));
		if (ObjectUtil.isNull(svipOrderVO)) {
			return false; //告诉支付宝成功或失败
		}
		// 如果订单状态不是待支付状态证明订单已经处理过,不用再处理
		if (!OrderStatusEnum.WAIT.getCode().equals(svipOrderVO.getOrderStatus())) {
			return true;
		}
		// 订单状态为成功时处理
		if (trade_status.equals("TRADE_FINISHED") || trade_status.equals("TRADE_SUCCESS")) {
			//交易结束，不可退款 或 交易支付成功, 处理课程信息
			svipOrderVO.setPayTime(new Date());
			svipOrderVO.setOrderStatus(OrderStatusEnum.SUCCESS.getCode());
		} else if(trade_status.equals("WAIT_BUYER_PAY")){

		}else{
			svipOrderVO.setOrderStatus(OrderStatusEnum.FAIL.getCode());
		}
		// 更新订单信息
		bossSvipOrder.updateById(svipOrderVO);
		return true;
	}

	/**
	 * 课程处理
	 *
	 * @param orderInfo
	 * @param orderPay
	 * @return
	 * @author wuyun
	 */
	private void course(OrderInfo orderInfo, OrderPay orderPay) {
		// 根据课程No查找课程信息
		CourseAudit courseAudit = courseAuditDao.getById(orderInfo.getCourseId());
		if (StringUtils.isEmpty(courseAudit)) {
			return;
		}

		// 根据讲师编号和机构编号查找讲师账户信息
//        LecturerExtVO lecturerExtVO = bossLecturerExt.getByLecturerUserNo(courseAudit.getLecturerUserNo());
//        if (StringUtils.isEmpty(lecturerExtVO)) {
//            return;
//        }
//        LecturerVO lecturerVO = bossLecturer.getByLecturerUserNo(courseAudit.getLecturerUserNo());
//        if (ObjectUtil.isNull(lecturerVO)) {
//            return;
//        }
		// 计算讲师分润
//        orderInfo = countProfit(orderInfo, lecturerExtVO, lecturerVO.getLecturerProportion());
		// 更新讲师账户信息
//        updateLecturerExtVO(orderInfo, lecturerExtVO);

		// 更新课程信息的购买人数
		courseAudit.setCountBuy(courseAudit.getCountBuy() + 1);
		courseAuditDao.updateById(courseAudit);
		// 更新订单信息
		orderInfo.setPayTime(new Date());
		orderInfo.setOrderStatus(OrderStatusEnum.SUCCESS.getCode());
		orderInfoDao.updateById(orderInfo);
		// 更新订单支付信息
		orderPay.setOrderStatus(OrderStatusEnum.SUCCESS.getCode());
		orderPay.setPayTime(new Date());
		orderPayDao.updateById(orderPay);

		UserExtVO userExtVO = bossUserExt.getByUserNo(orderInfo.getUserNo());
		if (!StringUtils.isEmpty(userExtVO.getVipLevel())) {
			userExtVO.setVipLevel(2);
			UserExtQO userExtQO = BeanUtil.copyProperties(userExtVO, UserExtQO.class);
			bossUserExt.updateById(userExtQO);
		}
		//判断是否有邀请人，如果有判断邀请人会员等级，如果为1级，就返佣
		UserLogInviteVO userLogInviteVO = bossUserLogInvite.getByInvitedNo(orderInfo.getUserNo());
		if (!StringUtils.isEmpty(userLogInviteVO)) {
			UserExtVO extVO = bossUserExt.getByUserNo(userLogInviteVO.getInviteUserNo());
			if (extVO.getVipLevel().equals("1")) {
				// 返佣
				UserExtQO userExtQO = new UserExtQO();
				userExtQO.setId(extVO.getId()).setCommission(extVO.getCommission().add(new BigDecimal("5")));
				bossUserExt.updateById(userExtQO);
				// 返佣记录
				UserLogCommissionSaveQO userLogCommissionSaveQO = new UserLogCommissionSaveQO();
				userLogCommissionSaveQO.setAddTime(LocalDateTime.now());
				userLogCommissionSaveQO.setCommission(userExtQO.getCommission());
				userLogCommissionSaveQO.setOrderNo(orderInfo.getOrderNo());
				userLogCommissionSaveQO.setSourceUserNo(userLogInviteVO.getInvitedUserNo());
				userLogCommissionSaveQO.setUserNo(userLogInviteVO.getInviteUserNo());
				bossUserLogCommission.save(userLogCommissionSaveQO);
			}
		}
	}

	/**
	 * 计算处理讲师分润信息
	 *
	 * @param orderInfo
	 * @param lecturerExtVO
	 * @param lecturerProportion
	 * @return
	 * @author wuyun
	 */
	private OrderInfo countProfit(OrderInfo orderInfo, LecturerExtVO lecturerExtVO, BigDecimal lecturerProportion) {
		// 讲师收入 = 订单价格x讲师分成比例
		BigDecimal lecturerProfit = orderInfo.getPricePaid().multiply(lecturerProportion).setScale(2, RoundingMode.DOWN);
		// 平台收入 = 实付金额 - 讲师收入
		BigDecimal platformIncome = orderInfo.getPricePaid().subtract(lecturerProfit).setScale(2, RoundingMode.DOWN);

		orderInfo.setLecturerProfit(lecturerProfit);
		orderInfo.setPlatformProfit(platformIncome);

		return orderInfo;
	}

	/**
	 * 更新讲师账户信息
	 *
	 * @param orderInfo
	 * @param lecturerExtVO
	 * @author wuyun
	 */
	private void updateLecturerExtVO(OrderInfo orderInfo, LecturerExtVO lecturerExtVO) {
		LecturerExtQO lecturerExtQO = new LecturerExtQO();
		lecturerExtQO.setLecturerUserNo(lecturerExtVO.getLecturerUserNo());
		lecturerExtQO.setTotalIncome(orderInfo.getLecturerProfit());
		lecturerExtQO.setEnableBalances(orderInfo.getLecturerProfit());
		bossLecturerExt.updateTotalIncomeByLecturerUserNo(lecturerExtQO);
	}

}
