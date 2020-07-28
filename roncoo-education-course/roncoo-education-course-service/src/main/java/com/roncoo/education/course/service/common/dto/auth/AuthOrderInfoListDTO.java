package com.roncoo.education.course.service.common.dto.auth;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;

import com.roncoo.education.course.service.dao.impl.mapper.entity.CouponUser;
import com.roncoo.education.user.common.bean.vo.UserShippingAddressVO;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * <p>
 * 订单信息表
 * </p>
 */
@Data
@Accessors(chain = true)
public class AuthOrderInfoListDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 创建时间
	 */
	@ApiModelProperty(value = "创建时间")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date gmtCreate;
	/**
	 * 用户编号
	 */
	@ApiModelProperty(value = "用户编号")
	@JsonSerialize(using = ToStringSerializer.class)
	private Long userNo;
	/**
	 * 订单号
	 */
	@ApiModelProperty(value = "订单号")
	@JsonSerialize(using = ToStringSerializer.class)
	private Long orderNo;
	/**
	 * 课程id
	 */
	@ApiModelProperty(value = "课程id")
	@JsonSerialize(using = ToStringSerializer.class)
	private Long courseId;
	/**
	 * 课程封面
	 */
	@ApiModelProperty(value = "课程封面")
	private String courseLogo;
	/**
	 * 课程名称
	 */
	@ApiModelProperty(value = "课程名称")
	private String courseName;
	/**
	 * 应付金额
	 */
	@ApiModelProperty(value = "应付金额")
	private BigDecimal pricePayable;
	/**
	 * 实付金额price_payable
	 */
	@ApiModelProperty(value = "实付金额")
	private BigDecimal pricePaid;
	/**
	 * 支付方式：1微信支付，2支付宝支付，3积分支付
	 */
	@ApiModelProperty(value = "支付方式：1微信支付，2支付宝支付")
	private Integer payType;
	/**
	 * 订单状态：1待支付，2成功支付，3支付失败，4已关闭，5已退款, 6订单解绑
	 */
	@ApiModelProperty(value = "订单状态：1待支付，2成功支付，3支付失败，4已关闭")
	private Integer orderStatus;
	/**
	 * 订单类型（1：普通订单，2：拼团订单，3：砍价订单）
	 */
	@ApiModelProperty(value = "订单类型（1：普通订单，2：拼团订单，3：砍价订单）")
	private Integer orderType;
	/**
	 * 订单地址id
	 */
	@ApiModelProperty(value = "订单地址id")
	@JsonSerialize(using = ToStringSerializer.class)
	private Long shippingAddressId;
	/**
	 * 订单地址
	 */
	@ApiModelProperty(value = "订单地址")
	private UserShippingAddressVO shippingAddressVO;
	/**
	 * 用户优惠券id
	 */
	@ApiModelProperty(value = "用户优惠券id")
	private Long couponUserId;
	/**
	 * 订单使用的优惠券
	 */
	@ApiModelProperty(value = "订单使用的优惠券")
	private CouponUser couponUser;
}
