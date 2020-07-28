package com.roncoo.education.course.service.biz.auth;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;

import com.alipay.api.response.AlipayTradePrecreateResponse;
import com.alipay.api.response.AlipayTradeQueryResponse;
import com.github.wxpay.sdk.WXPay;
import com.github.wxpay.sdk.WXPayConfig;
import com.github.wxpay.sdk.WXPayConstants;
import com.github.wxpay.sdk.WXPayUtil;
import com.roncoo.education.course.service.common.bo.auth.*;
import com.roncoo.education.course.service.common.dto.auth.*;
import com.roncoo.education.course.service.dao.*;
import com.roncoo.education.course.service.dao.impl.mapper.entity.*;
import com.roncoo.education.user.common.bean.qo.LecturerExtQO;
import com.roncoo.education.user.common.bean.vo.*;
import com.roncoo.education.user.feign.*;
import com.roncoo.education.util.enums.*;
import com.roncoo.education.util.pay.AlipayUtil;
import com.roncoo.education.util.pay.WeixinConfig;
import com.roncoo.education.util.pay.WeixinPayUtil;
import com.xiaoleilu.hutool.util.CollectionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import com.github.abel533.echarts.Option;
import com.github.abel533.echarts.axis.CategoryAxis;
import com.github.abel533.echarts.axis.ValueAxis;
import com.github.abel533.echarts.code.AxisType;
import com.github.abel533.echarts.code.Magic;
import com.github.abel533.echarts.code.Tool;
import com.github.abel533.echarts.code.Trigger;
import com.github.abel533.echarts.feature.MagicType;
import com.github.abel533.echarts.series.Line;
import com.roncoo.education.course.service.common.bo.OrderInfoCloseBO;
import com.roncoo.education.course.service.dao.impl.mapper.entity.OrderInfoExample.Criteria;
import com.roncoo.education.system.common.bean.vo.SysVO;
import com.roncoo.education.system.feign.IBossSys;
import com.roncoo.education.util.base.BaseBiz;
import com.roncoo.education.util.base.BaseException;
import com.roncoo.education.util.base.Page;
import com.roncoo.education.util.base.PageUtil;
import com.roncoo.education.util.base.Result;
import com.roncoo.education.util.config.SystemUtil;
import com.roncoo.education.util.pay.PayUtil;
import com.roncoo.education.util.tools.BeanUtil;
import com.roncoo.education.util.tools.DateUtil;
import com.roncoo.education.util.tools.NOUtil;
import com.xiaoleilu.hutool.util.ObjectUtil;

/**
 * 订单信息表
 */
@Component
public class AuthApiOrderInfoBiz extends BaseBiz {

	@Autowired
	private OrderInfoDao orderInfoDao;
	@Autowired
	private OrderPayDao orderPayDao;
	@Autowired
	private CourseAuditDao courseAuditDao;
	@Autowired
	private CouponUserDao couponUserDao;
	@Autowired
	private IBossSys bossSys;
	@Autowired
	private IBossUserExt bossUserExt;
	@Autowired
	private IBossUser bossUser;
	@Autowired
	private IBossLecturer bossLecturer;
	@Autowired
	private IBossVip bossVip;
	@Autowired
	private IBossLecturerExt bossLecturerExt;
	@Autowired
	private IBossShipAddress bossShipAddress;
	@Autowired
	private AuthApiAssembleBiz authApiAssembleBiz;
	@Autowired
	private AssembleDao assembleDao;

	/**
	 * 订单列表接口
	 *
	 * @param authOrderInfoListBO
	 * @return
	 */
	public Result<Page<AuthOrderInfoListDTO>> listForPage(AuthOrderInfoListBO authOrderInfoListBO) {
		OrderInfoExample Example = new OrderInfoExample();
		Criteria c = Example.createCriteria();
		c.andUserNoEqualTo(authOrderInfoListBO.getUserNo());
		c.andIsShowUserEqualTo(IsShowEnum.YES.getCode());
		// 初始化页数
		if (StringUtils.isEmpty(authOrderInfoListBO.getPageCurrent())) {
			authOrderInfoListBO.setPageCurrent(1);
		}
		if (StringUtils.isEmpty(authOrderInfoListBO.getPageSize())) {
			authOrderInfoListBO.setPageSize(20);
		}
		// 0用于判断前端,查出除了关闭状态的所有订单
		if (authOrderInfoListBO.getOrderStatus() != null && !authOrderInfoListBO.getOrderStatus().equals(Integer.valueOf(0))) {
			c.andOrderStatusEqualTo(authOrderInfoListBO.getOrderStatus());
		} else {
			c.andOrderStatusNotEqualTo(OrderStatusEnum.CLOSE.getCode());
		}
		Example.setOrderByClause(" id desc ");
		Page<OrderInfo> page = orderInfoDao.listForPage(authOrderInfoListBO.getPageCurrent(), authOrderInfoListBO.getPageSize(), Example);
		Page<AuthOrderInfoListDTO> dtopage = PageUtil.transform(page, AuthOrderInfoListDTO.class);
		for (AuthOrderInfoListDTO dto : dtopage.getList()) {
			CourseAudit courseAudit = courseAuditDao.getById(dto.getCourseId());
			dto.setCourseLogo(courseAudit.getCourseLogo());
			dto.setCourseId(courseAudit.getId());
			if (!StringUtils.isEmpty(dto.getShippingAddressId())) {
				UserShippingAddressVO userShippingAddressVO = bossShipAddress.getById(dto.getShippingAddressId());
				dto.setShippingAddressVO(userShippingAddressVO);
			}
			if (!StringUtils.isEmpty(dto.getCouponUserId())) {
				CouponUser couponUser = couponUserDao.getById(dto.getCouponUserId());
				dto.setCouponUser(couponUser);
			}
		}
		return Result.success(dtopage);

	}

	/**
	 * 订单下单接口
	 *
	 * @param authOrderPayBO
	 * @return
	 */
	@Transactional
	public Result<AuthOrderPayDTO> pay(AuthOrderPayBO authOrderPayBO) throws Exception {
		// 参数校验
		verifyParam(authOrderPayBO);

		// 课程信息
		CourseAudit courseAudit = courseAuditDao.getByCourseIdAndStatusId(authOrderPayBO.getCourseId(), StatusIdEnum.YES.getCode());
		if (StringUtils.isEmpty(courseAudit)) {
			return Result.error("courseId不正确");
		}

		UserVO userVO = bossUser.getByUserNo(authOrderPayBO.getUserNo());
		if (ObjectUtil.isNull(userVO) || StatusIdEnum.NO.getCode().equals(userVO.getStatusId())) {
			return Result.error("userNo不正确");
		}

		// 获取讲师信息
		LecturerVO lecturerVO = bossLecturer.getByLecturerUserNo(courseAudit.getLecturerUserNo());
		if (StringUtils.isEmpty(lecturerVO) || !StatusIdEnum.YES.getCode().equals(lecturerVO.getStatusId())) {
			return Result.error("lecturerUserNo不正确");
		}

		// 判断所要购买的课程是否已经购买------(如果课程为测试支付课程，可以重复下单，不提示已经购买，去掉后面一截判断即可正常使用)
		if (!checkOrderInfo(authOrderPayBO.getUserNo(), authOrderPayBO.getCourseId()) && !SystemUtil.TEST_COURSE.equals(authOrderPayBO.getCourseId().toString())) {
			return Result.error("已经购买该课程，请勿重复购买");
		}
		OrderInfo orderInfo = orderInfoDao.getByUserNoAndCourseIdAndOrderStatus(authOrderPayBO.getUserNo(), authOrderPayBO.getCourseId(), authOrderPayBO.getOrderType());
		OrderPay orderPay = null;
		if (ObjectUtil.isNull(orderInfo)) {
			// 创建订单信息
			orderInfo = createOrderInfo(authOrderPayBO, courseAudit, userVO, lecturerVO);
			// 创建支付订单
			orderPay = createOrderPay(orderInfo);
		}else{
			if (OrderStatusEnum.SUCCESS.getCode().equals(orderInfo.getOrderStatus()) && !SystemUtil.TEST_COURSE.equals(authOrderPayBO.getCourseId().toString())) {
				return Result.error("已经购买该课程，请勿重复购买");
			}
			orderPay = orderPayDao.getByOrderNo(orderInfo.getOrderNo());
			if (ObjectUtil.isNull(orderPay)) {
				return Result.error("orderNo不正确，没有找到流水号");
			}
			if(orderInfo.getPricePaidSource().equals(PricePaidSourceEnum.VIP.getCode())){
				SvipVO svipVO = bossVip.getByUserNo(userVO.getUserNo());
				if(ObjectUtil.isNull(svipVO)){
					orderInfo.setPricePaidSource(PricePaidSourceEnum.ORIGINAL.getCode());
					orderInfo.setPricePaid(orderInfo.getPricePayable());
				}
			}
			// 更新订单信息
			orderInfo.setPayType(authOrderPayBO.getPayType());
			orderInfo.setOrderStatus(OrderStatusEnum.WAIT.getCode());
			orderInfo.setShippingAddressId(authOrderPayBO.getShippingAddressId());
			orderInfo.setGmtCreate(new Date());
			orderInfo.setGmtModified(new Date());
			orderInfoDao.updateById(orderInfo);

			// 重新生成新的支付流水
			orderPay.setPayType(authOrderPayBO.getPayType());
			orderPay.setOrderStatus(OrderStatusEnum.WAIT.getCode());
			orderPay.setSerialNumber(NOUtil.getSerialNumber());
			orderPay.setGmtCreate(new Date());
			orderPayDao.updateById(orderPay);
		}
		// 如果有拼团信息则不生成
		Assemble assemble = assembleDao.getOrderId(orderInfo.getOrderNo());
		if (ObjectUtils.isEmpty(assemble)) {
			if (!ObjectUtils.isEmpty(authOrderPayBO.getAssembleSaveREQ())) {
				//生成拼团信息
				authOrderPayBO.getAssembleSaveREQ().setOrderId(orderInfo.getOrderNo());
				authApiAssembleBiz.save(authOrderPayBO.getAssembleSaveREQ());
			}
		}

		if (authOrderPayBO.getPricePaid().compareTo(new BigDecimal("0")) == 0) {
			//直接处理成功
			course(orderInfo, orderPay);
			return Result.error(202, orderInfo.getOrderNo()+""); // 不用支付，直接成功
		}

		// 查找系统配置信息
		SysVO sys = bossSys.getSys();
		if (ObjectUtil.isNull(sys)) {
			return Result.error("找不到系统配置信息");
		}
		if (StringUtils.isEmpty(sys.getPayKey()) || StringUtils.isEmpty(sys.getPaySecret()) || StringUtils.isEmpty(sys.getPayUrl())) {
			return Result.error("payKey,paySecret或payUrl未配置");
		}

		if(authOrderPayBO.getPayType() == 1){
			Map<String, String> response = WeixinPayUtil.weixinPay(String.valueOf(orderPay.getSerialNumber()), orderInfo.getCourseName(), authOrderPayBO.getPricePaid(), sys.getNotifyUrl(), authOrderPayBO.getTradeType(), "course", userVO.getOpenId());
			if(null == response){
				return Result.error("调用微信支付失败，请联系商家");
			}
			// 返回实体
			if("SUCCESS".equals(response.get("return_code"))){
				AuthOrderPayDTO dto = new AuthOrderPayDTO();
				if("SUCCESS".equals(response.get("result_code"))){
					dto.setPayMessage(response.get("code_url"));
				}else{
					return Result.error("调用微信支付失败，请联系商家");
				}
				if ("JSAPI".equals(authOrderPayBO.getTradeType())) {
					String timeStamp = String.valueOf(System.currentTimeMillis());
					WeixinConfig config = new WeixinConfig();
					dto.setAppId(config.getAppID());
					dto.setPrepayId(response.get("prepay_id"));
					dto.setTimeStamp(timeStamp);
					dto.setNonceStr(response.get("nonce_str"));
					dto.setSignType(WXPayConstants.MD5);
					Map<String,String> data = new HashMap<>();
					data.put("appId", config.getAppID());
					data.put("timeStamp", timeStamp);
					data.put("nonceStr", response.get("nonce_str"));
					data.put("signType", WXPayConstants.MD5);
					data.put("package", "prepay_id=" + response.get("prepay_id"));
					dto.setPaySign(WXPayUtil.generateSignature(data, config.getKey()));
				}
				dto.setOrderNo(String.valueOf(orderInfo.getOrderNo()));
				dto.setSerialNumber(String.valueOf(orderPay.getSerialNumber()));
				dto.setCourseName(orderInfo.getCourseName());
				dto.setPayType(orderInfo.getPayType());
				dto.setPrice(orderInfo.getPricePaid());
				return Result.success(dto);
			}else{
				return Result.error("调用微信支付失败，请联系商家");
			}
		}else {
			AlipayTradePrecreateResponse response = AlipayUtil.alipay(String.valueOf(orderPay.getSerialNumber()), orderInfo.getCourseName(), orderInfo.getPricePaid(), sys.getPayUrl(), sys.getNotifyUrl(), "course");
			if (null == response) {
				return Result.error("调用支付宝支付失败，请联系商家");
			}
			// 返回实体
			if (response.isSuccess()) {
				AuthOrderPayDTO dto = new AuthOrderPayDTO();
				dto.setPayMessage(response.getQrCode());
				dto.setOrderNo(String.valueOf(orderInfo.getOrderNo()));
				dto.setSerialNumber(String.valueOf(orderPay.getSerialNumber()));
				dto.setCourseName(orderInfo.getCourseName());
				dto.setPayType(orderInfo.getPayType());
				dto.setPrice(orderInfo.getPricePaid());
				return Result.success(dto);
			} else {
				return Result.error("调用支付宝支付失败，请联系商家");
			}
		}
	}

	/**
	 * 订单继续支付接口
	 *
	 * @param authOrderInfoContinuePayBO
	 * @return
	 */
	@Transactional
	public Result<AuthOrderPayDTO> continuePay(AuthOrderInfoContinuePayBO authOrderInfoContinuePayBO) throws Exception {
		if (StringUtils.isEmpty(authOrderInfoContinuePayBO.getOrderNo())) {
			return Result.error("orderNo不能为空");
		}
		if (StringUtils.isEmpty(authOrderInfoContinuePayBO.getPayType())) {
			return Result.error("payType不能为空");
		}

		// 订单校验
		OrderInfo orderInfo = orderInfoDao.getByOrderNo(authOrderInfoContinuePayBO.getOrderNo());
		if (ObjectUtil.isNull(orderInfo)) {
			return Result.error("orderNo不正确，没有找到订单信息");
		}


		OrderPay orderPay = orderPayDao.getByOrderNo(orderInfo.getOrderNo());
		if (ObjectUtil.isNull(orderPay)) {
			return Result.error("orderNo不正确，没有找到流水号");
		}

		// 查找课程信息
		CourseAudit courseAudit = courseAuditDao.getByCourseIdAndStatusId(orderInfo.getCourseId(), StatusIdEnum.YES.getCode());
		if (StringUtils.isEmpty(courseAudit) || !StatusIdEnum.YES.getCode().equals(courseAudit.getStatusId())) {
			return Result.error("根据订单的课程编号没找到对应的课程信息!");
		}
		if (OrderStatusEnum.SUCCESS.getCode().equals(orderInfo.getOrderStatus()) && !SystemUtil.TEST_COURSE.equals(courseAudit.getId().toString())) {
			return Result.error("已经购买该课程，请勿重复购买");
		}
		// 根据用户编号查找用户信息
		UserVO userVO = bossUser.getByUserNo(orderInfo.getUserNo());
		if (StringUtils.isEmpty(userVO) || StatusIdEnum.NO.getCode().equals(userVO.getStatusId())) {
			return Result.error("根据传入的userNo没找到对应的用户信息!");
		}

		SvipVO svipVO = bossVip.getByUserNo(userVO.getUserNo());
		if(orderInfo.getPricePaidSource().equals(PricePaidSourceEnum.VIP.getCode())){
			if (StringUtils.isEmpty(svipVO)) {
				orderInfo.setOrderStatus(OrderStatusEnum.CLOSE.getCode());
				orderInfo.setRemark("会员过期，订单失效");
				orderInfoDao.updateById(orderInfo);

				orderPay.setOrderStatus(OrderStatusEnum.CLOSE.getCode());
				orderPayDao.updateById(orderPay);
				return Result.error("会员过期，请重新下单!");
			}
		}else{
			if (!StringUtils.isEmpty(svipVO)) {
				// 更新订单信息
				orderInfo.setOrderStatus(OrderStatusEnum.CLOSE.getCode());
				orderInfo.setRemark("已购买会员，订单失效");
				orderInfoDao.updateById(orderInfo);

				// 重新生成新的支付流水
				orderPay.setOrderStatus(OrderStatusEnum.CLOSE.getCode());
				orderPayDao.updateById(orderPay);
				return Result.error("已购买会员，请重新下单!");
			}
		}

		// 更新订单信息
		orderInfo.setPayType(authOrderInfoContinuePayBO.getPayType());
		orderInfo.setOrderStatus(OrderStatusEnum.WAIT.getCode());
		orderInfo.setRemark(null);
		orderInfoDao.updateById(orderInfo);

		// 重新生成新的支付流水
		orderPay.setPayType(authOrderInfoContinuePayBO.getPayType());
		orderPay.setOrderStatus(OrderStatusEnum.WAIT.getCode());
		orderPay.setSerialNumber(NOUtil.getSerialNumber());
		orderPayDao.updateById(orderPay);

		// 查找系统配置信息
		SysVO sys = bossSys.getSys();
		if (ObjectUtil.isNull(sys)) {
			return Result.error("找不到系统配置信息");
		}
		if (StringUtils.isEmpty(sys.getPayKey()) || StringUtils.isEmpty(sys.getPaySecret()) || StringUtils.isEmpty(sys.getPayUrl())) {
			return Result.error("payKey,paySecret或payUrl未配置");
		}

		// 调用支付接口
//		String payMessage = PayUtil.roncooPay(String.valueOf(orderPay.getSerialNumber()), orderInfo.getCourseName(), orderInfo.getPricePaid(), orderInfo.getPayType(), sys.getPayKey(), sys.getPaySecret(), sys.getPayUrl(), sys.getNotifyUrl());
//		if (StringUtils.isEmpty(payMessage)) {
//			return Result.error("系统繁忙，请稍后再试");
//		}
        if(authOrderInfoContinuePayBO.getPayType() == 1){
            Map<String, String> response = WeixinPayUtil.weixinPay(String.valueOf(orderPay.getSerialNumber()), orderInfo.getCourseName(), orderInfo.getPricePaid(), sys.getNotifyUrl(), authOrderInfoContinuePayBO.getTradeType(), "course", userVO.getOpenId());
			if(CollectionUtils.isEmpty(response)){
				return Result.error("调用微信支付失败，请联系商家");
			}
			// 返回实体
			if("SUCCESS".equals(response.get("return_code"))){
				AuthOrderPayDTO dto = new AuthOrderPayDTO();
				if("SUCCESS".equals(response.get("result_code"))){
					dto.setPayMessage(response.get("code_url"));
				}else{
					return Result.error("调用微信支付失败，请联系商家");
				}
				if ("JSAPI".equals(authOrderInfoContinuePayBO.getTradeType())) {
					WeixinConfig config = new WeixinConfig();
					String timeStamp = String.valueOf(System.currentTimeMillis());
					dto.setAppId(config.getAppID());
					dto.setPrepayId(response.get("prepay_id"));
					dto.setTimeStamp(timeStamp);
					dto.setNonceStr(response.get("nonce_str"));
					dto.setSignType(WXPayConstants.MD5);
					Map<String,String> data = new HashMap<>();
					data.put("appId", config.getAppID());
					data.put("timeStamp", timeStamp);
					data.put("nonceStr", response.get("nonce_str"));
					data.put("signType", WXPayConstants.MD5);
					data.put("package", "prepay_id=" + response.get("prepay_id"));
					dto.setPaySign(WXPayUtil.generateSignature(data, config.getKey()));
				}
				dto.setOrderNo(String.valueOf(orderInfo.getOrderNo()));
				dto.setSerialNumber(String.valueOf(orderPay.getSerialNumber()));
				dto.setCourseName(orderInfo.getCourseName());
				dto.setPayType(orderInfo.getPayType());
				dto.setPrice(orderInfo.getPricePaid());
				return Result.success(dto);
			}else{
				return Result.error("调用微信支付失败，请联系商家");
			}
        }else{
		    AlipayTradePrecreateResponse response = AlipayUtil.alipay(String.valueOf(orderPay.getSerialNumber()), orderInfo.getCourseName(), orderInfo.getPricePaid(), sys.getPayUrl(), sys.getNotifyUrl(), "course");
            if(null == response){
                return Result.error("调用支付宝支付失败，请联系商家");
            }
            // 返回实体
            if(response.isSuccess()){
                AuthOrderPayDTO dto = new AuthOrderPayDTO();
                dto.setPayMessage(response.getQrCode());
                dto.setOrderNo(String.valueOf(orderInfo.getOrderNo()));
                dto.setSerialNumber(String.valueOf(orderPay.getSerialNumber()));
                dto.setCourseName(orderInfo.getCourseName());
                dto.setPayType(orderInfo.getPayType());
                dto.setPrice(orderInfo.getPricePaid());
                return Result.success(dto);
            }else{
                return Result.error("调用支付宝支付失败，请联系商家");
            }
        }

	}

	/**
	 * 关闭订单信息接口
	 *
	 * @param orderInfoCloseBO
	 * @return
	 */
	@Transactional
	public Result<String> close(OrderInfoCloseBO orderInfoCloseBO) {
		if (StringUtils.isEmpty(orderInfoCloseBO.getOrderNo())) {
			return Result.error("orderNo不能为空");
		}
		OrderInfo orderInfo = orderInfoDao.getByOrderNo(orderInfoCloseBO.getOrderNo());
		if (ObjectUtil.isNull(orderInfo)) {
			return Result.error("orderNo不正确,找不到订单信息");
		}
		OrderPay orderPay = orderPayDao.getByOrderNo(orderInfo.getOrderNo());
		if (ObjectUtil.isNull(orderPay)) {
			return Result.error("orderNo不正确,找不到流水号");
		}
		if(orderPay.getPayType() == 1){
			Map<String, String> response = WeixinPayUtil.orderQuery(String.valueOf(orderPay.getSerialNumber()));
			if(!CollectionUtils.isEmpty(response)){
				if("SUCCESS".equals(response.get("return_code"))){
					if("SUCCESS".equals(response.get("result_code"))){
						if("SUCCESS".equals(response.get("trade_state"))){
							//微信已交易成功,商户系统显示未交易成功，则修改商户系统订单状态
							if (OrderStatusEnum.WAIT.getCode().equals(orderInfo.getOrderStatus())) {
								course(orderInfo, orderPay);
							}
							return Result.error("订单已交易成功，不需要再处理");
						}
					}
				}
			}
		}else{
			AlipayTradeQueryResponse response = AlipayUtil.queryOrder(String.valueOf(orderPay.getSerialNumber()),null);
			if(null != response) {
				if (response.isSuccess()) {
					//支付宝已交易成功
					if("TRADE_SUCCESS".equals(response.getTradeStatus()) || "TRADE_FINISHED".equals(response.getTradeStatus())){
						//商户系统显示未交易成功，则修改商户系统订单状态
						if (OrderStatusEnum.WAIT.getCode().equals(orderInfo.getOrderStatus())) {
							course(orderInfo, orderPay);
						}
						return Result.error("订单已交易成功，不需要再处理");
					}
				}
			}
		}
		if (!OrderStatusEnum.WAIT.getCode().equals(orderInfo.getOrderStatus())) {
			return Result.error("该订单已经处理完成，不需要再处理");
		}
		// 根据用户编号查找用户信息
		UserExtVO userExtVO = bossUserExt.getByUserNo(orderInfo.getUserNo());
		if (StringUtils.isEmpty(userExtVO) || !StatusIdEnum.YES.getCode().equals(userExtVO.getStatusId())) {
			return Result.error("根据传入的userNo没找到对应的用户信息!");
		}
		orderInfo.setOrderStatus(OrderStatusEnum.CLOSE.getCode());
		orderInfo.setRemark("取消订单");
		int orderNum = orderInfoDao.updateById(orderInfo);
		if (orderNum < 1) {
			throw new BaseException("订单信息更新失败");
		}
		orderPay.setOrderStatus(OrderStatusEnum.CLOSE.getCode());
		int orderPayNum = orderPayDao.updateById(orderPay);
		if (orderPayNum < 1) {
			throw new BaseException("订单流水号更新失败");
		}
		return Result.success("订单关闭成功");
	}

	/**
	 * 订单详情
	 *
	 * @param authOrderInfoViewBO
	 * @return
	 */
	@Transactional
	public Result<AuthOrderInfoDTO> view(AuthOrderInfoViewBO authOrderInfoViewBO) {
		if (StringUtils.isEmpty(authOrderInfoViewBO.getOrderNo())) {
			return Result.error("订单号不能为空");
		}
//		if (StringUtils.isEmpty(authOrderInfoViewBO.getSerialNumber())) {
//			return Result.error("流水号不能为空");
//		}

//		OrderInfo orderInfo = orderInfoDao.getByOrderNo(authOrderInfoViewBO.getOrderNo());

		OrderInfo order = orderInfoDao.getByOrderNo(authOrderInfoViewBO.getOrderNo());
		OrderPay orderPay = orderPayDao.getByOrderNo(authOrderInfoViewBO.getOrderNo());
		if (ObjectUtil.isNull(order)) {
			return Result.error("订单号不正确");
		}

		if(PricePaidSourceEnum.VIP.getCode().equals(order.getPricePaidSource())){
			SvipVO svipVO = bossVip.getByUserNo(authOrderInfoViewBO.getUserNo());
			if (StringUtils.isEmpty(svipVO)) {
				// 更新订单信息
				order.setOrderStatus(OrderStatusEnum.CLOSE.getCode());
				order.setRemark("会员过期，订单失效");
				orderInfoDao.updateById(order);

				orderPay.setOrderStatus(OrderStatusEnum.CLOSE.getCode());
				orderPayDao.updateById(orderPay);
				return Result.error("会员过期，请重新下单!");
			}
		}

		if(order.getPayType() == 1){
			Map<String, String> response = WeixinPayUtil.orderQuery(String.valueOf(orderPay.getSerialNumber()));
			if(!CollectionUtils.isEmpty(response)){
				if("SUCCESS".equals(response.get("return_code"))){
					if("SUCCESS".equals(response.get("result_code"))){
						if(!"NOTPAY".equals(response.get("trade_state"))){
							if("SUCCESS".equals(response.get("trade_state"))){
								//微信已交易成功
								//处理课程信息
								// 如果订单状态不是待支付状态证明订单已经处理过,不用再处理
								if (OrderStatusEnum.WAIT.getCode().equals(order.getOrderStatus())) {
									course(order, orderPay);
								}
							}else{
								//微信支付失败
								//更新订单信息
								order.setOrderStatus(OrderStatusEnum.FAIL.getCode());
								orderInfoDao.updateById(order);
								//更新订单支付信息
								orderPay.setOrderStatus(OrderStatusEnum.FAIL.getCode());
								orderPayDao.updateById(orderPay);
							}
						}
					}
				}
			}
		}else{
			//查询支付宝交易结果
			AlipayTradeQueryResponse response = AlipayUtil.queryOrder(String.valueOf(orderPay.getSerialNumber()),null);
			if(null != response){
				if(response.isSuccess()){
					if(!response.getTradeStatus().equals("WAIT_BUYER_PAY")){
						// 订单状态为成功时处理
						if (response.getTradeStatus().equals("TRADE_FINISHED") || response.getTradeStatus().equals("TRADE_SUCCESS")) {
							//交易结束，不可退款 或 交易支付成功
							// 处理课程信息
							// 如果订单状态不是待支付状态证明订单已经处理过,不用再处理
							if (OrderStatusEnum.WAIT.getCode().equals(order.getOrderStatus())) {
								course(order, orderPay);
							}
						}else{
							// 更新订单信息
							order.setOrderStatus(OrderStatusEnum.FAIL.getCode());
							orderInfoDao.updateById(order);
							// 更新订单支付信息
							orderPay.setOrderStatus(OrderStatusEnum.FAIL.getCode());
							orderPayDao.updateById(orderPay);
						}
					}
				}
			}
		}
        AuthOrderInfoDTO authOrderInfoDTO = BeanUtil.copyProperties(order, AuthOrderInfoDTO.class);
		//获取课程信息
        CourseAudit courseAudit = courseAuditDao.getByCourseIdAndStatusId(order.getCourseId(),  StatusIdEnum.YES.getCode());
        authOrderInfoDTO.setCourseAudit(courseAudit);
		if (courseAudit.getHasTrainaid() == 2) {
        //根据是否有实物获取用户收货地址信息
			UserShippingAddressVO userShippingAddressVO = bossShipAddress.getById(order.getShippingAddressId());
			authOrderInfoDTO.setShippingAddress(userShippingAddressVO);
		}
		return Result.success(authOrderInfoDTO);
	}

	/**
	 * 查找订单信息列表信息
	 *
	 * @param authOrderInfoListBO
	 * @return
	 */
	public Result<Page<AuthOrderInfoListForLecturerDTO>> list(AuthOrderInfoListBO authOrderInfoListBO) {
		if (StringUtils.isEmpty(authOrderInfoListBO.getLecturerUserNo())) {
			return Result.error("lecturerUserNo不正确");
		}
		OrderInfoExample example = new OrderInfoExample();
		OrderInfoExample.Criteria c = example.createCriteria();
		c.andLecturerUserNoEqualTo(authOrderInfoListBO.getLecturerUserNo());
		c.andIsShowUserEqualTo(IsShowUserEnum.YES.getCode());
		c.andPricePaidGreaterThanOrEqualTo(BigDecimal.valueOf(0.5));
		// 不查找已经关闭了的订单
		c.andOrderStatusEqualTo(OrderStatusEnum.SUCCESS.getCode());
		example.setOrderByClause(" id desc ");
		Page<OrderInfo> page = orderInfoDao.listForPage(authOrderInfoListBO.getPageCurrent(), authOrderInfoListBO.getPageSize(), example);
		Page<AuthOrderInfoListForLecturerDTO> dtoPage = PageUtil.transform(page, AuthOrderInfoListForLecturerDTO.class);
		for (AuthOrderInfoListForLecturerDTO dto : dtoPage.getList()) {
			dto.setPhone(dto.getMobile().substring(0, 3) + "****" + dto.getMobile().substring(7, dto.getMobile().length()));
		}
		return Result.success(dtoPage);
	}

	/**
	 * 讲师收益折线图
	 *
	 * @param authOrderInfoForChartsBO
	 * @return
	 */
	public Result<Option> charts(AuthOrderInfoForChartsBO authOrderInfoForChartsBO) {
		Option option = new Option();
		option.legend().data("订单收益", "日期时间");
		option.tooltip().trigger(Trigger.axis).axisPointer();
		option.calculable(true);
		// 设置x轴数据
		CategoryAxis categoryAxis = new CategoryAxis();
		List<String> xData = new ArrayList<>();
		payTime(authOrderInfoForChartsBO, xData);
		for (String x : xData) {
			categoryAxis.data(x);
		}
		option.xAxis(categoryAxis);

		// 设置y轴数据
		ValueAxis valueAxis = new ValueAxis();
		valueAxis.type(AxisType.value);
		valueAxis.splitArea().show(true);
		valueAxis.axisLabel().formatter("{value}元");
		option.yAxis(valueAxis);
		// 第一条线为当天收益
		Line line1 = new Line();
		List<AuthOrderInfoLecturerIncomeDTO> dtoList = sumByLecturerUserNoAndData(authOrderInfoForChartsBO.getLecturerUserNo(), xData);
		for (AuthOrderInfoLecturerIncomeDTO dto : dtoList) {
			for (BigDecimal bi : dto.getLecturerProfit()) {
				line1.data(bi);
			}
		}
		line1.name("讲师	");
		option.series(line1);
		option.toolbox().show(true).feature(new MagicType(Magic.line, Magic.bar), Tool.restore, Tool.saveAsImage);
		return Result.success(option);
	}

	private List<AuthOrderInfoLecturerIncomeDTO> sumByLecturerUserNoAndData(Long lecturerUserNo, List<String> xData) {
		List<AuthOrderInfoLecturerIncomeDTO> list = new ArrayList<>();
		AuthOrderInfoLecturerIncomeDTO dto = new AuthOrderInfoLecturerIncomeDTO();
		List<BigDecimal> countPaidPrice = new ArrayList<>();
		for (String date : xData) {
			BigDecimal sum = orderInfoDao.sumLecturerUserNoAndData(lecturerUserNo, date);
			countPaidPrice.add(sum);
		}
		dto.setLecturerProfit(countPaidPrice);
		list.add(dto);
		return list;
	}

	private List<String> payTime(AuthOrderInfoForChartsBO authOrderInfoForChartsBO, List<String> xData) {
		// 如果时间为空，则传入现在当前时间七天前的订单
		if (authOrderInfoForChartsBO.getBeginCreate() == null && authOrderInfoForChartsBO.getEndCreate() == null) {
			authOrderInfoForChartsBO.setBeginCreate(DateUtil.format(DateUtil.addDate(new Date(), -7)));
			authOrderInfoForChartsBO.setEndCreate(DateUtil.format(new Date()));
		}
		Calendar tempStart = Calendar.getInstance();
		tempStart.setTime(DateUtil.parseDate(authOrderInfoForChartsBO.getBeginCreate(), "yyyy-MM-dd"));
		tempStart.add(Calendar.DAY_OF_YEAR, 0);
		Calendar tempEnd = Calendar.getInstance();
		tempEnd.setTime(DateUtil.parseDate(authOrderInfoForChartsBO.getEndCreate(), "yyyy-MM-dd"));
		tempEnd.add(Calendar.DAY_OF_YEAR, 1);
		while (tempStart.before(tempEnd)) {
			xData.add(DateUtil.formatDate(tempStart.getTime()));
			tempStart.add(Calendar.DAY_OF_YEAR, 1);
		}
		return xData;
	}

	/**
	 * 校验下单时传入的参数
	 *
	 * @param authOrderPayBO
	 */
	private void verifyParam(AuthOrderPayBO authOrderPayBO) {
		if (StringUtils.isEmpty(authOrderPayBO.getUserNo())) {
			throw new BaseException("userNo不能为空");
		}
		if (StringUtils.isEmpty(authOrderPayBO.getCourseId())) {
			throw new BaseException("courseId不能为空");
		}
		if (StringUtils.isEmpty(authOrderPayBO.getPayType())) {
			throw new BaseException("payType不能为空");
		}
		if (StringUtils.isEmpty(authOrderPayBO.getChannelType())) {
			throw new BaseException("channelType不能为空");
		}
	}

	/**
	 * 判断课程是否已经支付
	 */
	private boolean checkOrderInfo(long userNo, long courseId) {
		OrderInfo orderInfo = orderInfoDao.getByUserNoAndCourseId(userNo, courseId);
		if (ObjectUtil.isNull(orderInfo)) {
			return true;
		} else if (!OrderStatusEnum.SUCCESS.getCode().equals(orderInfo.getOrderStatus())) {
			return true;
		}
		return false;
	}

	/**
	 * 创建订单支付信息
	 */
	private OrderPay createOrderPay(OrderInfo retrunOrderInfo) {
		OrderPay orderpay = new OrderPay();
		orderpay.setOrderNo(retrunOrderInfo.getOrderNo());
		orderpay.setOrderStatus(retrunOrderInfo.getOrderStatus());
		orderpay.setPayType(retrunOrderInfo.getPayType());
		orderpay.setSerialNumber(NOUtil.getSerialNumber());
		orderPayDao.save(orderpay);
		return orderpay;
	}

	/**
	 * 创建订单信息表
	 */
	private OrderInfo createOrderInfo(AuthOrderPayBO authOrderPayBO, CourseAudit courseAudit, UserVO userVO, LecturerVO lecturervo) {
		SvipVO svipVO = bossVip.getByUserNo(userVO.getUserNo());
		OrderInfo orderInfo = new OrderInfo();
		orderInfo.setCourseName(courseAudit.getCourseName());
		orderInfo.setCourseId(courseAudit.getId());
		if(ObjectUtil.isNull(svipVO)){
			orderInfo.setPricePaid(authOrderPayBO.getPricePaid());
			orderInfo.setPricePaidSource(PricePaidSourceEnum.ORIGINAL.getCode());
		}else{
			orderInfo.setPricePaid(authOrderPayBO.getPricePaid());
			orderInfo.setPricePaidSource(PricePaidSourceEnum.VIP.getCode());
		}
		orderInfo.setPricePayable(authOrderPayBO.getPricePaid());
		orderInfo.setLecturerUserNo(lecturervo.getLecturerUserNo());
		orderInfo.setLecturerName(lecturervo.getLecturerName());
		orderInfo.setUserNo(userVO.getUserNo());
		orderInfo.setMobile(userVO.getMobile());
		orderInfo.setRegisterTime(userVO.getGmtCreate());
		orderInfo.setOrderNo(NOUtil.getOrderNo()); // 订单号，不要使用IdWorker生成
		orderInfo.setCourseId(courseAudit.getId());
		orderInfo.setCourseName(courseAudit.getCourseName());
		orderInfo.setPriceDiscount(BigDecimal.ZERO);
		orderInfo.setPlatformProfit(BigDecimal.ZERO);
		orderInfo.setLecturerProfit(BigDecimal.ZERO);
		orderInfo.setIsShowUser(IsShowUserEnum.YES.getCode());
		orderInfo.setTradeType(TradeTypeEnum.ONLINE.getCode());
		orderInfo.setPayType(authOrderPayBO.getPayType());
		orderInfo.setChannelType(authOrderPayBO.getChannelType());
		orderInfo.setRemarkCus(authOrderPayBO.getRemarkCus());
		orderInfo.setOrderStatus(OrderStatusEnum.WAIT.getCode());
		orderInfo.setOrderType(authOrderPayBO.getOrderType());
		orderInfo.setCouponUserId(authOrderPayBO.getCouponUserId());
		orderInfo.setShippingAddressId(authOrderPayBO.getShippingAddressId());
		orderInfoDao.save(orderInfo);
		return orderInfo;
	}

	/**
	 * 补充订单地址信息
	 * @param authOrderInfoUpdateBO
	 * @return
	 */
	public Result<Integer> completeOrderSite(AuthOrderInfoUpdateBO authOrderInfoUpdateBO) {
		if(StringUtils.isEmpty(authOrderInfoUpdateBO.getOrderNo())){
			return Result.error("订单不能为空！");
		}
		if(StringUtils.isEmpty(authOrderInfoUpdateBO.getShippingAddressId())){
			return Result.error("地址不能为空！");
		}
		OrderInfo orderInfo = BeanUtil.copyProperties(authOrderInfoUpdateBO, OrderInfo.class);
		int result = orderInfoDao.updateByOrderNo(orderInfo);
		return Result.success(result);
	}

	/**
	 *统计各订单数量
	 */
	public Result<AuthOrderInfoDataDTO> getData(AuthOrderInfoDataBO authOrderInfoDataBO) {
		if (StringUtils.isEmpty(authOrderInfoDataBO.getUserNo())) {
			return Result.error("用户编号不能为空！");
		}
		int unPaid = orderInfoDao.getByUserNoForCount(authOrderInfoDataBO.getUserNo(), OrderStatusEnum.WAIT.getCode());
		int unDeliver = orderInfoDao.getByUserNoForCount(authOrderInfoDataBO.getUserNo(), OrderStatusEnum.UNDELIVER.getCode());
		int delivered = orderInfoDao.getByUserNoForCount(authOrderInfoDataBO.getUserNo(), OrderStatusEnum.DELIVERED.getCode());
		int complete = orderInfoDao.getByUserNoForCount(authOrderInfoDataBO.getUserNo(), OrderStatusEnum.COMPLETE.getCode());
		int unAssemble = orderInfoDao.getByUserNoForCount(authOrderInfoDataBO.getUserNo(), OrderStatusEnum.WAITASSEMBLE.getCode());
		AuthOrderInfoDataDTO authOrderInfoDataDTO = new AuthOrderInfoDataDTO();
		authOrderInfoDataDTO.setCompleteCount(complete);
		authOrderInfoDataDTO.setReceivedCount(delivered);
		authOrderInfoDataDTO.setUnshippedCount(unDeliver);
		authOrderInfoDataDTO.setUnpaidCount(unPaid);
		authOrderInfoDataDTO.setUnAssembleCount(unAssemble);
		return Result.success(authOrderInfoDataDTO);
	}

	class updateCount implements Runnable {
		private CourseAudit courseAudit;

		public updateCount(CourseAudit courseAudit) {
			this.courseAudit = courseAudit;
		}

		@Override
		public void run() {
			Course info = new Course();
			info.setCountBuy(courseAudit.getCountBuy() + 1);
			info.setId(courseAudit.getId());
			courseAuditDao.updateById(courseAudit);
		}
	}


	/**
	 * 课程处理
	 *
	 * @param orderInfo
	 * @param orderPay
	 * @return
	 *
	 */
	private void course(OrderInfo orderInfo, OrderPay orderPay) {
		// 根据课程No查找课程信息
		CourseAudit courseAudit = courseAuditDao.getById(orderInfo.getCourseId());
		if (StringUtils.isEmpty(courseAudit)) {
			return;
		}

		// 更新课程信息的购买人数
		courseAudit.setCountBuy(courseAudit.getCountBuy() + 1);
		courseAuditDao.updateById(courseAudit);

		// 计算讲师分润
//		orderInfo = countProfit(orderInfo, lecturerExtVO, lecturerVO.getLecturerProportion());
		orderInfo.setPayTime(new Date());

		// 更新拼团信息
		Assemble assemble = assembleDao.getOrderId(orderInfo.getOrderNo());
		if (!ObjectUtils.isEmpty(assemble)) {
			assemble.setStatus(1);
			assembleDao.updateById(assemble);
			// 设置为8待拼成状态
			orderInfo.setOrderStatus(OrderStatusEnum.WAITASSEMBLE.getCode());
			orderPay.setOrderStatus(OrderStatusEnum.WAITASSEMBLE.getCode());
		}
		// 根据拼团id查询状态为1(1表示已支付的拼团)的拼团记录判断拼团是否完成
		List<Assemble> assembleList = assembleDao.getByAssembleId(assemble.getAssembleId());
		if (assembleList.size() > 1) {
			for (Assemble assemble1: assembleList) {
				assemble1.setStatus(2); //2表示已完成的拼团
				assembleDao.updateById(assemble1);

				if (courseAudit.getHasTrainaid() == 2) {
					orderInfo.setOrderStatus(OrderStatusEnum.UNDELIVER.getCode());
					orderPay.setOrderStatus(OrderStatusEnum.UNDELIVER.getCode());
				} else {
					orderInfo.setOrderStatus(OrderStatusEnum.COMPLETE.getCode());
					orderPay.setOrderStatus(OrderStatusEnum.COMPLETE.getCode());
				}
			}
		}

		// 更新订单信息
		orderInfoDao.updateById(orderInfo);

		// 更新订单支付信息
		orderPay.setPayTime(new Date());
		orderPayDao.updateById(orderPay);

		// 更新优惠券信息
		if (!StringUtils.isEmpty(orderInfo.getCouponUserId())) {
			CouponUser record = new CouponUser();
			record.setId(orderInfo.getCouponUserId());
			record.setStatus(1);
			couponUserDao.updateById(record);
		}
	}

	/**
	 * 计算处理讲师分润信息
	 *
	 * @param orderInfo
	 * @param lecturerExtVO
	 * @param lecturerProportion
	 * @return
	 *
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
	 *
	 */
	private void updateLecturerExtVO(OrderInfo orderInfo, LecturerExtVO lecturerExtVO) {
		LecturerExtQO lecturerExtQO = new LecturerExtQO();
		lecturerExtQO.setLecturerUserNo(lecturerExtVO.getLecturerUserNo());
		lecturerExtQO.setTotalIncome(orderInfo.getLecturerProfit());
		lecturerExtQO.setEnableBalances(orderInfo.getLecturerProfit());
		bossLecturerExt.updateTotalIncomeByLecturerUserNo(lecturerExtQO);
	}

}
