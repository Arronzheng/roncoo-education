package com.roncoo.education.user.service.biz.auth;

import com.alipay.api.response.AlipayTradePrecreateResponse;
import com.alipay.api.response.AlipayTradeQueryResponse;
import com.roncoo.education.system.common.bean.vo.SysVO;
import com.roncoo.education.system.common.bean.vo.WebsiteVO;
import com.roncoo.education.system.feign.IBossSys;
import com.roncoo.education.system.feign.IBossWebsite;
import com.roncoo.education.user.common.bean.qo.LecturerExtQO;
import com.roncoo.education.user.common.bean.vo.LecturerExtVO;
import com.roncoo.education.user.common.bean.vo.SvipVO;
import com.roncoo.education.user.common.bean.vo.UserExtVO;
import com.roncoo.education.user.service.common.bo.auth.AuthSvipOrderBO;
import com.roncoo.education.user.service.common.bo.auth.AuthSvipOrderViewBO;
import com.roncoo.education.user.service.common.dto.auth.AuthSvipOrderDTO;
import com.roncoo.education.user.service.common.dto.auth.AuthSvipOrderViewDTO;
import com.roncoo.education.user.service.dao.SvipBuyLogDao;
import com.roncoo.education.user.service.dao.SvipDao;
import com.roncoo.education.user.service.dao.SvipOrderDao;
import com.roncoo.education.user.service.dao.UserExtDao;
import com.roncoo.education.user.service.dao.impl.mapper.entity.Svip;
import com.roncoo.education.user.service.dao.impl.mapper.entity.SvipBuyLog;
import com.roncoo.education.user.service.dao.impl.mapper.entity.SvipOrder;
import com.roncoo.education.user.service.dao.impl.mapper.entity.UserExt;
import com.roncoo.education.util.base.*;
import com.roncoo.education.util.enums.*;
import com.roncoo.education.util.pay.AlipayUtil;
import com.roncoo.education.util.pay.WeixinPayUtil;
import com.roncoo.education.util.tools.BeanUtil;
import com.roncoo.education.util.tools.DateUtil;
import com.roncoo.education.util.tools.NOUtil;
import com.xiaoleilu.hutool.util.ObjectUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;

/**
 * 订单信息表
 *
 * @author wujing
 */
@Component
public class AuthApiSvipOrderBiz extends BaseBiz {

	@Autowired
	private IBossSys bossSys;
	@Autowired
	private IBossWebsite bossWebsite;
	@Autowired
	private SvipOrderDao svipOrderDao;
	@Autowired
	private UserExtDao userExtDao;
	@Autowired
	private SvipDao svipDao;
	@Autowired
	private SvipBuyLogDao svipBuyLogDao;

	/**
	 * 订单下单接口
	 * @param authSvipOrderBO
	 * @return
	 */
	@Transactional
	public Result<AuthSvipOrderDTO> pay(AuthSvipOrderBO authSvipOrderBO) {
		// 参数校验
		verifyParam(authSvipOrderBO);
		// 根据用户编号查找用户信息
		UserExt userExt = userExtDao.getByUserNo(authSvipOrderBO.getUserNo());
		if (ObjectUtil.isNull(userExt) || StatusIdEnum.NO.getCode().equals(userExt.getStatusId())) {
			return Result.error("userNo不正确");
		}
		SvipOrder svipOrder = svipOrderDao.getByUserNoAndOrderStatus(authSvipOrderBO.getUserNo());
		if (ObjectUtil.isNull(svipOrder)) {
			// 创建订单信息
			svipOrder = createOrderInfo(authSvipOrderBO, userExt);
		}else{
			svipOrder.setPayType(authSvipOrderBO.getPayType());
			svipOrder.setOrderStatus(OrderStatusEnum.WAIT.getCode());
			svipOrder.setSerialNo(NOUtil.getSerialNumber());//重新生成流水号
			svipOrder.setGmtCreate(new Date());
			svipOrder.setGmtModified(new Date());
			svipOrderDao.updateById(svipOrder);
		}
		// 查找系统配置信息
		SysVO sys = bossSys.getSys();
		if (ObjectUtil.isNull(sys)) {
			return Result.error("找不到系统配置信息");
		}
		if (StringUtils.isEmpty(sys.getPayKey()) || StringUtils.isEmpty(sys.getPaySecret()) || StringUtils.isEmpty(sys.getPayUrl())) {
			return Result.error("payKey,paySecret或payUrl未配置");
		}
		if(authSvipOrderBO.getPayType() == 1){
			Map<String, String> response = WeixinPayUtil.weixinPay(String.valueOf(svipOrder.getSerialNo()), svipOrder.getOrderName(), svipOrder.getPricePaid(), sys.getNotifyUrl(), "course");
			if(null == response){
				return Result.error("调用微信支付失败，请联系商家");
			}
			// 返回实体
			if("SUCCESS".equals(response.get("return_code"))){
				AuthSvipOrderDTO dto = new AuthSvipOrderDTO();
				if("SUCCESS".equals(response.get("result_code"))){
					dto.setPayMessage(response.get("code_url"));
				}else{
					return Result.error("调用微信支付失败，请联系商家");
				}
				dto.setOrderNo(String.valueOf(svipOrder.getOrderNo()));
				dto.setSerialNo(String.valueOf(svipOrder.getSerialNo()));
				dto.setOrderName(svipOrder.getOrderName());
				dto.setPayType(svipOrder.getPayType());
				dto.setPrice(svipOrder.getPricePaid());
				return Result.success(dto);
			}else{
				return Result.error("调用微信支付失败，请联系商家");
			}
		}else {
			AlipayTradePrecreateResponse response = AlipayUtil.alipay(String.valueOf(svipOrder.getSerialNo()), svipOrder.getOrderName(), svipOrder.getPricePaid(), sys.getPayUrl(), "http://q5dpjm.natappfree.cc/course/api/callback/alipay/notify", "vip");
			if (null == response) {
				return Result.error("调用支付宝支付失败，请联系商家");
			}
			// 返回实体
			if (response.isSuccess()) {
				AuthSvipOrderDTO dto = new AuthSvipOrderDTO();
				dto.setPayMessage(response.getQrCode());
				dto.setOrderNo(String.valueOf(svipOrder.getOrderNo()));
				dto.setSerialNo(String.valueOf(svipOrder.getSerialNo()));
				dto.setOrderName(svipOrder.getOrderName());
				dto.setPayType(svipOrder.getPayType());
				dto.setPrice(svipOrder.getPricePaid());
				return Result.success(dto);
			} else {
				return Result.error("调用支付宝支付失败，请联系商家");
			}
		}
	}

	/**
	 * 订单详情
	 *
	 * @param authSvipOrderViewBO
	 * @return
	 */
	@Transactional
	public Result<AuthSvipOrderViewDTO> view(AuthSvipOrderViewBO authSvipOrderViewBO) {
		if (StringUtils.isEmpty(authSvipOrderViewBO.getOrderNo())) {
			return Result.error("订单号不能为空");
		}
		if (StringUtils.isEmpty(authSvipOrderViewBO.getSerialNo())) {
			return Result.error("流水号不能为空");
		}
//		OrderInfo orderInfo = orderInfoDao.getByOrderNo(authSvipOrderViewBO.getOrderNo());
		SvipOrder vipOrder = svipOrderDao.getByOrderNo(authSvipOrderViewBO.getOrderNo());
		if (ObjectUtil.isNull(vipOrder)) {
			return Result.error("订单号不正确");
		}

		if(authSvipOrderViewBO.getPayType() == 1){
			Map<String, String> response = WeixinPayUtil.orderQuery(String.valueOf(authSvipOrderViewBO.getSerialNo()));
			if(!CollectionUtils.isEmpty(response)){
				if("SUCCESS".equals(response.get("return_code"))){
					if("SUCCESS".equals(response.get("result_code"))){
						if(!"NOTPAY".equals(response.get("trade_state"))){
							if("SUCCESS".equals(response.get("trade_state"))){
								//微信已交易成功
								//处理课程信息
								course(vipOrder);
							}else{
								//微信支付失败
								//更新订单信息
								vipOrder.setOrderStatus(OrderStatusEnum.FAIL.getCode());
								svipOrderDao.updateById(vipOrder);
							}
						}
					}
				}
			}
		}else{
			//查询支付宝交易结果
			AlipayTradeQueryResponse response = AlipayUtil.queryOrder(String.valueOf(authSvipOrderViewBO.getSerialNo()),null);
			if(null != response){
				if(response.isSuccess()){
					if(!response.getTradeStatus().equals("WAIT_BUYER_PAY")){
						// 订单状态为成功时处理
						if (response.getTradeStatus().equals("TRADE_FINISHED") || response.getTradeStatus().equals("TRADE_SUCCESS")) {
							//交易结束，不可退款 或 交易支付成功
							// 处理课程信息
							course(vipOrder);
						}else if(response.getTradeStatus().equals("WAIT_BUYER_PAY")){
							// 不做处理
						}else{
							// 更新订单信息
							vipOrder.setOrderStatus(OrderStatusEnum.FAIL.getCode());
							svipOrderDao.updateById(vipOrder);
						}
					}
				}
			}
		}
		return Result.success(BeanUtil.copyProperties(vipOrder, AuthSvipOrderViewDTO.class));
	}

	/**
	 * 校验下单时传入的参数
	 *
	 * @param authSvipOrderBO
	 */
	private void verifyParam(AuthSvipOrderBO authSvipOrderBO) {
		if (StringUtils.isEmpty(authSvipOrderBO.getUserNo())) {
			throw new BaseException("userNo不能为空");
		}
		if (StringUtils.isEmpty(authSvipOrderBO.getPayType())) {
			throw new BaseException("payType不能为空");
		}
		if (StringUtils.isEmpty(authSvipOrderBO.getChannelType())) {
			throw new BaseException("channelType不能为空");
		}
	}

	/**
	 * 创建订单信息表
	 */
	private SvipOrder createOrderInfo(AuthSvipOrderBO authSvipOrderBO, UserExt userExt) {
		SvipOrder svipOrder = new SvipOrder();
		WebsiteVO websiteVO = bossWebsite.getWebsite();
		svipOrder.setOrderName(authSvipOrderBO.getOrderName());
		svipOrder.setPricePayable(websiteVO.getVipPrice());
		svipOrder.setPricePaid(websiteVO.getVipPrice());
		svipOrder.setPricePaidSource(PricePaidSourceEnum.ORIGINAL.getCode());
//		//是否有会员促销活动
//		if(true){
//			svipOrder.setPricePaid(websiteVO.getVipPrice());
//			svipOrder.setPricePaidSource(PricePaidSourceEnum.ACTIVITY.getCode());
//		}else{
//			svipOrder.setPricePaid(courseAudit.getCourseOriginal());
//			svipOrder.setPricePaidSource(PricePaidSourceEnum.ORIGINAL.getCode());
//		}
		svipOrder.setUserNo(userExt.getUserNo());
		svipOrder.setMobile(userExt.getMobile());
		svipOrder.setRegisterTime(userExt.getGmtCreate());
		svipOrder.setOrderNo(NOUtil.getOrderNo()); // 订单号，不要使用IdWorker生成
		svipOrder.setSerialNo(NOUtil.getOrderNo()); // 流水号，不要使用IdWorker生成
		svipOrder.setPriceDiscount(BigDecimal.ZERO);
		svipOrder.setIsShowUser(IsShowUserEnum.YES.getCode());
		svipOrder.setTradeType(TradeTypeEnum.ONLINE.getCode());
		svipOrder.setPayType(authSvipOrderBO.getPayType());
		svipOrder.setChannelType(authSvipOrderBO.getChannelType());
		svipOrder.setOrderStatus(OrderStatusEnum.WAIT.getCode());
		svipOrderDao.save(svipOrder);
		return svipOrder;
	}

	/**
	 * 课程处理
	 *
	 * @param svipOrder
	 * @return
	 */
	@Transactional
	public void course(SvipOrder svipOrder) {
		// 更新VIP订单信息
		svipOrder.setPayTime(new Date());
		svipOrder.setOrderStatus(OrderStatusEnum.SUCCESS.getCode());
		svipOrderDao.updateById(svipOrder);
		UserExt userExt = userExtDao.getByUserNo(svipOrder.getUserNo());
		//更新VIP表
		Svip vip = svipDao.getByUserNo(userExt.getUserNo());
		if(ObjectUtil.isNull(vip)){
			vip = new Svip();
			vip.setGmtCreate(new Date());
			vip.setStartTime(svipOrder.getPayTime());
			vip.setDueTime(DateUtil.addDate(svipOrder.getPayTime(),365));
			vip.setUserNo(userExt.getUserNo());
			svipDao.save(vip);
		}else{
			if(vip.getDueTime().after(new Date())){//没到期
				vip.setDueTime(DateUtil.addDate(vip.getDueTime(),365));
			}else{//到期
				vip.setStartTime(svipOrder.getPayTime());
				vip.setDueTime(DateUtil.addDate(svipOrder.getPayTime(),365));
			}
			svipDao.updateById(vip);
		}
		//更新VIP记录表
		SvipBuyLog svipBuyLog = new SvipBuyLog();
		svipBuyLog.setStartTime(vip.getStartTime());
		svipBuyLog.setDueTime(vip.getDueTime());
		svipBuyLog.setUserNo(userExt.getUserNo());
		svipBuyLog.setMobile(userExt.getMobile());
		svipBuyLog.setNickname(userExt.getNickname());
		svipBuyLogDao.save(svipBuyLog);
	}

}
