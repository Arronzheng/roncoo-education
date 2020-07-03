package com.roncoo.education.course.service.controller.biz;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.alipay.api.response.AlipayTradeQueryResponse;
import com.roncoo.education.course.service.biz.auth.AuthApiOrderInfoBiz;
import com.roncoo.education.course.service.dao.CourseAuditDao;
import com.roncoo.education.course.service.dao.impl.mapper.entity.*;
import com.roncoo.education.user.common.bean.qo.LecturerExtQO;
import com.roncoo.education.user.common.bean.qo.UserExtQO;
import com.roncoo.education.user.common.bean.qo.UserLogCommissionSaveQO;
import com.roncoo.education.user.common.bean.vo.LecturerExtVO;
import com.roncoo.education.user.common.bean.vo.LecturerVO;
import com.roncoo.education.user.common.bean.vo.UserExtVO;
import com.roncoo.education.user.common.bean.vo.UserLogInviteVO;
import com.roncoo.education.user.feign.*;
import com.roncoo.education.util.base.Result;
import com.roncoo.education.util.pay.AlipayUtil;
import com.roncoo.education.util.pay.WeixinPayUtil;
import com.xiaoleilu.hutool.util.ObjectUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import com.roncoo.education.course.common.bean.qo.OrderEchartsQO;
import com.roncoo.education.course.common.bean.qo.OrderInfoQO;
import com.roncoo.education.course.common.bean.vo.CountIncomeVO;
import com.roncoo.education.course.common.bean.vo.OrderEchartsVO;
import com.roncoo.education.course.common.bean.vo.OrderInfoVO;
import com.roncoo.education.course.common.bean.vo.OrderReportVO;
import com.roncoo.education.course.service.common.resq.CountIncomeRESQ;
import com.roncoo.education.course.service.dao.OrderInfoDao;
import com.roncoo.education.course.service.dao.OrderPayDao;
import com.roncoo.education.course.service.dao.impl.mapper.entity.OrderInfoExample.Criteria;
import com.roncoo.education.util.base.BaseBiz;
import com.roncoo.education.util.base.Page;
import com.roncoo.education.util.base.PageUtil;
import com.roncoo.education.util.enums.OrderStatusEnum;
import com.roncoo.education.util.tools.BeanUtil;
import com.roncoo.education.util.tools.DateUtil;
import com.xiaoleilu.hutool.util.CollectionUtil;

/**
 * 订单信息表
 *
 * @author wujing
 */
@Component
public class BossOrderInfoBiz extends BaseBiz {

	@Autowired
	private OrderInfoDao dao;
	@Autowired
	private OrderPayDao orderPayDao;
	@Autowired
	private CourseAuditDao courseAuditDao;
	@Autowired
	private IBossLecturerExt bossLecturerExt;
	@Autowired
	private IBossUserExt bossUserExt;
	@Autowired
	private IBossUserLogInvite bossUserLogInvite;
	@Autowired
	private IBossUserLogCommission bossUserLogCommission;

	public Page<OrderInfoVO> listForPage(OrderInfoQO qo) {
		OrderInfoExample example = new OrderInfoExample();
		Criteria c = example.createCriteria();
		if (qo.getOrderNo() != null) {
			c.andOrderNoEqualTo(qo.getOrderNo());
		}
		if (qo.getTradeType() != null) {
			c.andTradeTypeEqualTo(qo.getTradeType());
		}
		if (qo.getPayType() != null) {
			c.andPayTypeEqualTo(qo.getPayType());
		}
		if (qo.getChannelType() != null) {
			c.andChannelTypeEqualTo(qo.getChannelType());
		}
		if (qo.getOrderStatus() != null) {
			c.andOrderStatusEqualTo(qo.getOrderStatus());
		}
		if (StringUtils.hasText(qo.getCourseName())) {
			c.andCourseNameEqualTo(qo.getCourseName());
		}
		if (StringUtils.hasText(qo.getLecturerName())) {
			c.andLecturerNameEqualTo(qo.getLecturerName());
		}
		if (qo.getCourseId() != null) {
			c.andCourseIdEqualTo(qo.getCourseId());
		}
		if (qo.getLecturerUserNo() != null) {
			c.andLecturerUserNoEqualTo(qo.getLecturerUserNo());
		}
		if (StringUtils.hasText(qo.getMobile())) {
			c.andMobileEqualTo(qo.getMobile());
		}
		if (StringUtils.hasText(qo.getRemark())) {
			c.andRemarkEqualTo(qo.getRemark());
		}
		if (StringUtils.hasText(qo.getBeginPayTime())) {
			c.andPayTimeGreaterThanOrEqualTo(DateUtil.parseDate(qo.getBeginPayTime(), "yyyy-MM-dd"));
		}
		if (StringUtils.hasText(qo.getEndPayTime())) {
			c.andPayTimeLessThanOrEqualTo(DateUtil.addDate(DateUtil.parseDate(qo.getEndPayTime(), "yyyy-MM-dd"), 1));
		}
		example.setOrderByClause(" order_status asc , id desc ");
		Page<OrderInfo> page = dao.listForPage(qo.getPageCurrent(), qo.getPageSize(), example);
		return PageUtil.transform(page, OrderInfoVO.class);
	}

	public int save(OrderInfoQO qo) {
		OrderInfo record = BeanUtil.copyProperties(qo, OrderInfo.class);
		return dao.save(record);
	}

	public int deleteById(Long id) {
		return dao.deleteById(id);
	}

	public OrderInfoVO getById(Long id) {
		OrderInfo record = dao.getById(id);
		OrderInfoVO orderInfoVO = BeanUtil.copyProperties(record, OrderInfoVO.class);

		// 根据订单编号查找订单支付信息
		OrderPay orderPay = orderPayDao.getByOrderNo(orderInfoVO.getOrderNo());
		orderInfoVO.setSerialNumber(orderPay.getSerialNumber());

		return orderInfoVO;
	}

	public int updateById(OrderInfoQO qo) {
		OrderInfo record = BeanUtil.copyProperties(qo, OrderInfo.class);
		return dao.updateById(record);
	}

	/**
	 * 订单信息汇总（导出报表）
	 *
	 * @param orderInfoQO
	 * @author wuyun
	 */
	public List<OrderReportVO> listForReport(OrderInfoQO orderInfoQO) {
		return dao.listForReport(orderInfoQO);
	}

	/**
	 * 统计时间段的总订单数
	 *
	 * @param orderEchartsQO
	 * @return
	 * @author wuyun
	 */
	public List<OrderEchartsVO> sumByCountOrders(OrderEchartsQO orderEchartsQO) {
		List<OrderEchartsVO> list = new ArrayList<>();
		List<Integer> countOrders = new ArrayList<>();
		OrderEchartsVO vo = new OrderEchartsVO();
		for (String date : orderEchartsQO.getDateList()) {
			Integer sum = dao.sumByCountOrders(date);
			countOrders.add(sum);
		}
		vo.setCountOrders(countOrders);
		list.add(vo);
		return list;
	}

	/**
	 * 统计时间段的总收入
	 *
	 * @param orderEchartsQO
	 * @return
	 * @author wuyun
	 */
	public List<OrderEchartsVO> sumByPayTime(OrderEchartsQO orderEchartsQO) {
		List<OrderEchartsVO> list = new ArrayList<>();
		List<BigDecimal> countPaidPrice = new ArrayList<>();
		OrderEchartsVO vo = new OrderEchartsVO();
		for (String date : orderEchartsQO.getDateList()) {
			BigDecimal sum = dao.sumByPayTime(date);
			countPaidPrice.add(sum);
		}
		vo.setCountPaidPrice(countPaidPrice);
		list.add(vo);
		return list;
	}

	/**
	 * 统计订单收入情况
	 *
	 * @author wuyun
	 */
	public CountIncomeVO countIncome(OrderInfoQO qo) {
		CountIncomeRESQ resq = dao.countIncome(qo);
		return BeanUtil.copyProperties(resq, CountIncomeVO.class);
	}

	/**
	 * 30分钟后如果订单不支付，就关闭订单和标记订单支付日志，每次处理10条数据
	 *
	 * @return
	 */
	public int handleScheduledTasks() {
		// 1.订单信息的处理
		OrderInfoExample example = new OrderInfoExample();
		Criteria c = example.createCriteria();
		List<Integer> list = new ArrayList<Integer>();
		list.add(OrderStatusEnum.WAIT.getCode());
		list.add(OrderStatusEnum.FAIL.getCode());
		c.andOrderStatusIn(list);
		c.andGmtCreateLessThan(new Date(System.currentTimeMillis() - 1800000L));
		example.setOrderByClause(" id desc ");
		Page<OrderInfo> page = dao.listForPage(1, 10, example);
		if (CollectionUtil.isNotEmpty(page.getList())) {
			for (OrderInfo orderInfo : page.getList()) {
				OrderInfo argOrderInfo = new OrderInfo();
				argOrderInfo.setId(orderInfo.getId());
				argOrderInfo.setOrderStatus(OrderStatusEnum.CLOSE.getCode());
				argOrderInfo.setRemark("系统自动关闭该订单");
				dao.updateById(argOrderInfo);
			}
		}

		// 2.订单支付的处理
		OrderPayExample orderPayExample = new OrderPayExample();
		com.roncoo.education.course.service.dao.impl.mapper.entity.OrderPayExample.Criteria orderPayCriteria = orderPayExample.createCriteria();
		orderPayCriteria.andOrderStatusIn(list);
		orderPayCriteria.andGmtCreateLessThan(new Date(System.currentTimeMillis() - 1800000L));
		Page<OrderPay> orderPayPage = orderPayDao.listForPage(1, 10, orderPayExample);
		if (CollectionUtil.isNotEmpty(orderPayPage.getList())) {
			for (OrderPay orderPay : orderPayPage.getList()) {
				OrderPay argOrderPay = new OrderPay();
				argOrderPay.setId(orderPay.getId());
				argOrderPay.setOrderStatus(OrderStatusEnum.CLOSE.getCode());
				orderPayDao.updateById(argOrderPay);
			}
		}
		//30分钟内轮询支付宝or微信订单状态
		OrderPayExample orderPayExample2 = new OrderPayExample();
		com.roncoo.education.course.service.dao.impl.mapper.entity.OrderPayExample.Criteria orderPayCriteria2 = orderPayExample2.createCriteria();
		orderPayCriteria2.andOrderStatusIn(list);
		orderPayCriteria2.andGmtCreateGreaterThan(new Date(System.currentTimeMillis() - 1800000L));
		Page<OrderPay> orderPayPage2 = orderPayDao.listForPage(1, 10, orderPayExample2);
		if (CollectionUtil.isNotEmpty(orderPayPage2.getList())) {
			for (OrderPay orderPay : orderPayPage2.getList()) {
				if(orderPay.getPayType() ==1){
					Map<String, String> response = WeixinPayUtil.orderQuery(String.valueOf(orderPay.getSerialNumber()));
					if(!CollectionUtils.isEmpty(response)){
						if("SUCCESS".equals(response.get("return_code"))){
							if("SUCCESS".equals(response.get("result_code"))){
								if(!"NOTPAY".equals(response.get("trade_state"))){
									OrderInfo order = dao.getByOrderNo(orderPay.getOrderNo());
									if("SUCCESS".equals(response.get("trade_state"))){
										//微信已交易成功
										//处理课程信息
										course(order, orderPay);
									}else{
										//微信支付失败
										//更新订单信息
										order.setOrderStatus(OrderStatusEnum.FAIL.getCode());
										dao.updateById(order);
										//更新订单支付信息
										orderPay.setOrderStatus(OrderStatusEnum.FAIL.getCode());
										orderPayDao.updateById(orderPay);
									}
								}
							}
						}
					}
				}else{
					AlipayTradeQueryResponse response = AlipayUtil.queryOrder(String.valueOf(orderPay.getSerialNumber()),null);
					if(null != response){
						if(response.isSuccess()){
							if(!response.getTradeStatus().equals("WAIT_BUYER_PAY")){
								OrderInfo order = dao.getByOrderNo(orderPay.getOrderNo());
								// 订单状态为成功时处理
								if (response.getTradeStatus().equals("TRADE_FINISHED") || response.getTradeStatus().equals("TRADE_SUCCESS")) {
									//交易结束，不可退款 或 交易支付成功
									// 处理课程信息
									course(order, orderPay);
								} else if(response.getTradeStatus().equals("WAIT_BUYER_PAY")){

								}else{
									// 更新订单信息
									order.setOrderStatus(OrderStatusEnum.FAIL.getCode());
									dao.updateById(order);
									// 更新订单支付信息
									orderPay.setOrderStatus(OrderStatusEnum.FAIL.getCode());
									orderPayDao.updateById(orderPay);
								}
							}
						}
					}
				}
			}
		}
		return 1;
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
//		LecturerExtVO lecturerExtVO = bossLecturerExt.getByLecturerUserNo(courseAudit.getLecturerUserNo());
//		if (StringUtils.isEmpty(lecturerExtVO)) {
//			return;
//		}
//		LecturerVO lecturerVO = bossLecturer.getByLecturerUserNo(courseAudit.getLecturerUserNo());
//		if (ObjectUtil.isNull(lecturerVO)) {
//			return;
//		}
		// 计算讲师分润
//		orderInfo = countProfit(orderInfo, lecturerExtVO, lecturerVO.getLecturerProportion());
		// 更新讲师账户信息
//		updateLecturerExtVO(orderInfo, lecturerExtVO);

		// 更新课程信息的购买人数
		courseAudit.setCountBuy(courseAudit.getCountBuy() + 1);
		courseAuditDao.updateById(courseAudit);

		// 更新订单信息
		orderInfo.setPayTime(new Date());
		orderInfo.setOrderStatus(OrderStatusEnum.SUCCESS.getCode());
		dao.updateById(orderInfo);

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

    public OrderInfoVO getByOrderNo(Long orderNo) {
		OrderInfo orderInfo = dao.getByOrderNo(orderNo);
		OrderInfoVO orderInfoVO = BeanUtil.copyProperties(orderInfo, OrderInfoVO.class);
		return orderInfoVO;
    }
}
