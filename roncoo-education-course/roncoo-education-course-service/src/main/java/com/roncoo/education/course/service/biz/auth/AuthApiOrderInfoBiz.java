package com.roncoo.education.course.service.biz.auth;

import java.math.BigDecimal;
import java.util.*;

import com.alipay.api.response.AlipayTradePrecreateResponse;
import com.alipay.api.response.AlipayTradeQueryResponse;
import com.roncoo.education.util.pay.AlipayUtil;
import com.roncoo.education.util.pay.WeixinPayUtil;
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
import com.roncoo.education.course.service.common.bo.auth.AuthOrderInfoContinuePayBO;
import com.roncoo.education.course.service.common.bo.auth.AuthOrderInfoForChartsBO;
import com.roncoo.education.course.service.common.bo.auth.AuthOrderInfoListBO;
import com.roncoo.education.course.service.common.bo.auth.AuthOrderInfoViewBO;
import com.roncoo.education.course.service.common.bo.auth.AuthOrderPayBO;
import com.roncoo.education.course.service.common.dto.auth.AuthOrderInfoDTO;
import com.roncoo.education.course.service.common.dto.auth.AuthOrderInfoLecturerIncomeDTO;
import com.roncoo.education.course.service.common.dto.auth.AuthOrderInfoListDTO;
import com.roncoo.education.course.service.common.dto.auth.AuthOrderInfoListForLecturerDTO;
import com.roncoo.education.course.service.common.dto.auth.AuthOrderPayDTO;
import com.roncoo.education.course.service.dao.CourseDao;
import com.roncoo.education.course.service.dao.OrderInfoDao;
import com.roncoo.education.course.service.dao.OrderPayDao;
import com.roncoo.education.course.service.dao.impl.mapper.entity.Course;
import com.roncoo.education.course.service.dao.impl.mapper.entity.OrderInfo;
import com.roncoo.education.course.service.dao.impl.mapper.entity.OrderInfoExample;
import com.roncoo.education.course.service.dao.impl.mapper.entity.OrderInfoExample.Criteria;
import com.roncoo.education.course.service.dao.impl.mapper.entity.OrderPay;
import com.roncoo.education.system.common.bean.vo.SysVO;
import com.roncoo.education.system.feign.IBossSys;
import com.roncoo.education.user.common.bean.vo.LecturerVO;
import com.roncoo.education.user.common.bean.vo.UserExtVO;
import com.roncoo.education.user.feign.IBossLecturer;
import com.roncoo.education.user.feign.IBossUserExt;
import com.roncoo.education.util.base.BaseBiz;
import com.roncoo.education.util.base.BaseException;
import com.roncoo.education.util.base.Page;
import com.roncoo.education.util.base.PageUtil;
import com.roncoo.education.util.base.Result;
import com.roncoo.education.util.config.SystemUtil;
import com.roncoo.education.util.enums.IsShowEnum;
import com.roncoo.education.util.enums.IsShowUserEnum;
import com.roncoo.education.util.enums.OrderStatusEnum;
import com.roncoo.education.util.enums.StatusIdEnum;
import com.roncoo.education.util.enums.TradeTypeEnum;
import com.roncoo.education.util.pay.PayUtil;
import com.roncoo.education.util.tools.BeanUtil;
import com.roncoo.education.util.tools.DateUtil;
import com.roncoo.education.util.tools.NOUtil;
import com.xiaoleilu.hutool.util.ObjectUtil;

/**
 * 订单信息表
 *
 * @author wujing
 */
@Component
public class AuthApiOrderInfoBiz extends BaseBiz {

	@Autowired
	private OrderInfoDao orderInfoDao;
	@Autowired
	private OrderPayDao orderPayDao;
	@Autowired
	private CourseDao courseDao;

	@Autowired
	private IBossSys bossSys;
	@Autowired
	private IBossUserExt bossUserExt;
	@Autowired
	private IBossLecturer bossLecturer;

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
			Course course = courseDao.getById(dto.getCourseId());
			dto.setCourseLogo(course.getCourseLogo());
			dto.setCourseId(course.getId());
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
	public Result<AuthOrderPayDTO> pay(AuthOrderPayBO authOrderPayBO) {
		// 参数校验
		verifyParam(authOrderPayBO);

		// 课程信息
		Course course = courseDao.getByCourseIdAndStatusId(authOrderPayBO.getCourseId(), StatusIdEnum.YES.getCode());
		if (StringUtils.isEmpty(course)) {
			return Result.error("courseId不正确");
		}

		// 根据用户编号查找用户信息
		UserExtVO userextVO = bossUserExt.getByUserNo(authOrderPayBO.getUserNo());
		if (ObjectUtil.isNull(userextVO) || StatusIdEnum.NO.getCode().equals(userextVO.getStatusId())) {
			return Result.error("userNo不正确");
		}

		// 获取讲师信息
		LecturerVO lecturerVO = bossLecturer.getByLecturerUserNo(course.getLecturerUserNo());
		if (StringUtils.isEmpty(lecturerVO) || !StatusIdEnum.YES.getCode().equals(lecturerVO.getStatusId())) {
			return Result.error("lecturerUserNo不正确");
		}

		// 判断所要购买的课程是否已经购买------(如果课程为测试支付课程，可以重复下单，不提示已经购买，去掉后面一截判断即可正常使用)
//		if (!checkOrderInfo(authOrderPayBO.getUserNo(), authOrderPayBO.getCourseId()) && !SystemUtil.TEST_COURSE.equals(course.getId().toString())) {
//			return Result.error("已经购买该课程，请勿重复购买");
//		}
		OrderInfo orderInfo = orderInfoDao.getByUserNoAndCourseIdAndOrderStatus(authOrderPayBO.getUserNo(), authOrderPayBO.getCourseId());
		OrderPay orderPay = null;
		if (ObjectUtil.isNull(orderInfo)) {
			// 创建订单信息
			orderInfo = createOrderInfo(authOrderPayBO, course, userextVO, lecturerVO);
			// 创建支付订单
			orderPay = createOrderPay(orderInfo);
		}else{
			if (OrderStatusEnum.SUCCESS.getCode().equals(orderInfo.getOrderStatus())) {
				return Result.error("已经购买该课程，请勿重复购买");
			}
			orderPay = orderPayDao.getByOrderNo(orderInfo.getOrderNo());
			if (ObjectUtil.isNull(orderPay)) {
				return Result.error("orderNo不正确，没有找到流水号");
			}

			// 更新订单信息
			orderInfo.setPayType(authOrderPayBO.getPayType());
			orderInfo.setOrderStatus(OrderStatusEnum.WAIT.getCode());
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

		// 创建订单信息
		//OrderInfo orderInfo = createOrderInfo(authOrderPayBO, course, userextVO, lecturerVO);
		// 创建支付订单
		//OrderPay orderPay = createOrderPay(orderInfo);

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
		if(authOrderPayBO.getPayType() == 1){
			Map<String, String> response = WeixinPayUtil.weixinPay(String.valueOf(orderPay.getSerialNumber()), orderInfo.getCourseName(), orderInfo.getPricePaid(), sys.getNotifyUrl());
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
			AlipayTradePrecreateResponse response = AlipayUtil.alipay(String.valueOf(orderPay.getSerialNumber()), orderInfo.getCourseName(), orderInfo.getPricePaid(), sys.getPayUrl(), sys.getNotifyUrl());
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
	public Result<AuthOrderPayDTO> continuePay(AuthOrderInfoContinuePayBO authOrderInfoContinuePayBO) {
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
		if (!checkOrderInfo(orderInfo.getUserNo(), orderInfo.getCourseId())) {
			return Result.error("已经购买该课程，请勿重复购买");
		}

		OrderPay orderPay = orderPayDao.getByOrderNo(orderInfo.getOrderNo());
		if (ObjectUtil.isNull(orderPay)) {
			return Result.error("orderNo不正确，没有找到流水号");
		}

		// 查找课程信息
		Course course = courseDao.getByCourseIdAndStatusId(orderInfo.getCourseId(), StatusIdEnum.YES.getCode());
		if (StringUtils.isEmpty(course) || !StatusIdEnum.YES.getCode().equals(course.getStatusId())) {
			return Result.error("根据订单的课程编号没找到对应的课程信息!");
		}
		// 根据用户编号查找用户信息
		UserExtVO userExtVO = bossUserExt.getByUserNo(orderInfo.getUserNo());
		if (StringUtils.isEmpty(userExtVO) || !StatusIdEnum.YES.getCode().equals(userExtVO.getStatusId())) {
			return Result.error("根据传入的userNo没找到对应的用户信息!");
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
            Map<String, String> response = WeixinPayUtil.weixinPay(String.valueOf(orderPay.getSerialNumber()), orderInfo.getCourseName(), orderInfo.getPricePaid(), sys.getNotifyUrl());
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
		    AlipayTradePrecreateResponse response = AlipayUtil.alipay(String.valueOf(orderPay.getSerialNumber()), orderInfo.getCourseName(), orderInfo.getPricePaid(), sys.getPayUrl(), sys.getNotifyUrl());
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
							if(!OrderStatusEnum.SUCCESS.getCode().equals(orderPay.getOrderStatus())){
								orderInfo.setOrderStatus(OrderStatusEnum.SUCCESS.getCode());
								orderInfo.setPayTime(DateUtil.parseDate(response.get("time_end"), "yyyy-MM-dd HH:mm:ss"));
								orderPay.setPayTime(DateUtil.parseDate(response.get("time_end"), "yyyy-MM-dd HH:mm:ss"));
								orderPay.setOrderStatus(OrderStatusEnum.SUCCESS.getCode());
								orderInfoDao.updateById(orderInfo);
								orderPayDao.updateById(orderPay);
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
						if(!OrderStatusEnum.SUCCESS.getCode().equals(orderPay.getOrderStatus())){
							orderInfo.setOrderStatus(OrderStatusEnum.SUCCESS.getCode());
							orderInfo.setPayTime(response.getSendPayDate());
							orderPay.setPayTime(response.getSendPayDate());
							orderPay.setOrderStatus(OrderStatusEnum.SUCCESS.getCode());
							orderInfoDao.updateById(orderInfo);
							orderPayDao.updateById(orderPay);
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
		if (StringUtils.isEmpty(authOrderInfoViewBO.getSerialNumber())) {
			return Result.error("流水号不能为空");
		}
		if(authOrderInfoViewBO.getPayType() == 1){
			Map<String, String> response = WeixinPayUtil.orderQuery(String.valueOf(authOrderInfoViewBO.getSerialNumber()));
			if(CollectionUtils.isEmpty(response)){
				return Result.error("调用微信支付失败,请联系商家");
			}
			if("SUCCESS".equals(response.get("return_code"))){
				if("SUCCESS".equals(response.get("result_code"))){
					OrderInfo order = new OrderInfo();
					OrderPay orderPay = new OrderPay();
					order.setOrderNo(authOrderInfoViewBO.getOrderNo());
					orderPay.setSerialNumber(authOrderInfoViewBO.getSerialNumber());
					if("SUCCESS".equals(response.get("trade_state"))){
						//微信已交易成功,商户系统显示未交易成功，则修改商户系统订单状态
						if(!OrderStatusEnum.SUCCESS.getCode().equals(orderPay.getOrderStatus())){
							order.setOrderStatus(OrderStatusEnum.SUCCESS.getCode());
							order.setPayTime(DateUtil.parseDate(response.get("time_end"), "yyyy-MM-dd HH:mm:ss"));
							orderPay.setPayTime(DateUtil.parseDate(response.get("time_end"), "yyyy-MM-dd HH:mm:ss"));
							orderPay.setOrderStatus(OrderStatusEnum.SUCCESS.getCode());
						}
					}else if("NOTPAY".equals(response.get("trade_state"))){
						if(!OrderStatusEnum.WAIT.getCode().equals(orderPay.getOrderStatus())){
							order.setOrderStatus(OrderStatusEnum.WAIT.getCode());
							orderPay.setOrderStatus(OrderStatusEnum.WAIT.getCode());
						}
					}else if("CLOSED".equals(response.get("trade_state"))){
						if(!OrderStatusEnum.CLOSE.getCode().equals(orderPay.getOrderStatus())){
							order.setOrderStatus(OrderStatusEnum.CLOSE.getCode());
							orderPay.setOrderStatus(OrderStatusEnum.CLOSE.getCode());
						}
					}else if("PAYERROR".equals(response.get("trade_state"))){
						if(!OrderStatusEnum.FAIL.getCode().equals(orderPay.getOrderStatus())){
							order.setOrderStatus(OrderStatusEnum.FAIL.getCode());
							orderPay.setOrderStatus(OrderStatusEnum.FAIL.getCode());
						}
					}else{
						if(!OrderStatusEnum.FAIL.getCode().equals(orderPay.getOrderStatus())){
							order.setOrderStatus(OrderStatusEnum.FAIL.getCode());
							orderPay.setOrderStatus(OrderStatusEnum.FAIL.getCode());
						}
					}
					orderInfoDao.updateByOrderNo(order);
					orderPayDao.updateBySerialNumber(orderPay);
				}else{
					return Result.error("调用微信查询失败,请联系商家");
				}
			}else{
				return Result.error("调用微信查询失败,请联系商家");
			}
		}else{
			//查询支付宝交易结果
			AlipayTradeQueryResponse response = AlipayUtil.queryOrder(String.valueOf(authOrderInfoViewBO.getSerialNumber()),null);
			if(null != response){
				if(response.isSuccess()){
					OrderInfo order =  orderInfoDao.getByOrderNo(authOrderInfoViewBO.getOrderNo());
					OrderPay orderPay = orderPayDao.getByOrderNo(authOrderInfoViewBO.getOrderNo());
					handleQueryResponse(response, order, orderPay);
					orderInfoDao.updateByOrderNo(order);
					orderPayDao.updateBySerialNumber(orderPay);
				}
			}
		}
		// 根据订单编号查找订单信息
		OrderInfo order = orderInfoDao.getByOrderNo(authOrderInfoViewBO.getOrderNo());
		if (ObjectUtil.isNull(order)) {
			return Result.error("订单号不正确");
		}
		return Result.success(BeanUtil.copyProperties(order, AuthOrderInfoDTO.class));
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
	private OrderInfo createOrderInfo(AuthOrderPayBO authOrderPayBO, Course course, UserExtVO userextvo, LecturerVO lecturervo) {
		OrderInfo orderInfo = new OrderInfo();
		orderInfo.setCourseName(course.getCourseName());
		orderInfo.setCourseId(course.getId());
		orderInfo.setPricePaid(course.getCourseDiscount());
		orderInfo.setPricePayable(course.getCourseOriginal());
		orderInfo.setLecturerUserNo(lecturervo.getLecturerUserNo());
		orderInfo.setLecturerName(lecturervo.getLecturerName());
		orderInfo.setUserNo(userextvo.getUserNo());
		orderInfo.setMobile(userextvo.getMobile());
		orderInfo.setRegisterTime(userextvo.getGmtCreate());
		orderInfo.setOrderNo(NOUtil.getOrderNo()); // 订单号，不要使用IdWorker生成
		orderInfo.setCourseId(course.getId());
		orderInfo.setCourseName(course.getCourseName());
		orderInfo.setPriceDiscount(BigDecimal.ZERO);
		orderInfo.setPlatformProfit(BigDecimal.ZERO);
		orderInfo.setLecturerProfit(BigDecimal.ZERO);
		orderInfo.setIsShowUser(IsShowUserEnum.YES.getCode());
		orderInfo.setTradeType(TradeTypeEnum.ONLINE.getCode());
		orderInfo.setPayType(authOrderPayBO.getPayType());
		orderInfo.setChannelType(authOrderPayBO.getChannelType());
		orderInfo.setRemarkCus(authOrderPayBO.getRemarkCus());
		orderInfo.setOrderStatus(OrderStatusEnum.WAIT.getCode());
		orderInfoDao.save(orderInfo);
		return orderInfo;
	}

	class updateCount implements Runnable {
		private Course course;

		public updateCount(Course course) {
			this.course = course;
		}

		@Override
		public void run() {
			Course info = new Course();
			info.setCountBuy(course.getCountBuy() + 1);
			info.setId(course.getId());
			courseDao.updateById(course);
		}
	}


	public static void handleQueryResponse(AlipayTradeQueryResponse response, OrderInfo order, OrderPay orderPay){
		if("WAIT_BUYER_PAY".equals(response.getTradeStatus())){
			//交易创建，等待买家付款
			if(!order.getOrderStatus().equals(OrderStatusEnum.WAIT.getCode()) && !orderPay.getOrderStatus().equals(OrderStatusEnum.WAIT.getCode())){
				order.setOrderStatus(OrderStatusEnum.WAIT.getCode());
				orderPay.setOrderStatus(OrderStatusEnum.WAIT.getCode());
			}
		}else if("TRADE_SUCCESS".equals(response.getTradeStatus()) || "TRADE_FINISHED".equals(response.getTradeStatus())){
			//交易支付成功
			if(!order.getOrderStatus().equals(OrderStatusEnum.SUCCESS.getCode()) && !orderPay.getOrderStatus().equals(OrderStatusEnum.SUCCESS.getCode())){
				order.setOrderStatus(OrderStatusEnum.SUCCESS.getCode());
				order.setPayTime(response.getSendPayDate());
				orderPay.setOrderStatus(OrderStatusEnum.SUCCESS.getCode());
				orderPay.setPayTime(response.getSendPayDate());
			}
		}else if("TRADE_CLOSED".equals(response.getTradeStatus())){
			//未付款交易超时关闭，或支付完成后全额退款(交易失败)
			if(!order.getOrderStatus().equals(OrderStatusEnum.FAIL.getCode()) && !orderPay.getOrderStatus().equals(OrderStatusEnum.FAIL.getCode())){
				order.setOrderStatus(OrderStatusEnum.FAIL.getCode());
				orderPay.setOrderStatus(OrderStatusEnum.FAIL.getCode());
			}
		}
	}

}
