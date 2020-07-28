package com.roncoo.education.course.service.common.bo.auth;

import java.io.Serializable;
import java.math.BigDecimal;

import com.roncoo.education.course.service.common.req.AssembleSaveREQ;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 订单支付信息表
 */
@Data
@Accessors(chain = true)
public class AuthOrderPayBO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "用户编号", required = true)
	private Long userNo;

	@ApiModelProperty(value = "课程编号", required = true)
	private Long courseId;

	@ApiModelProperty(value = "课程价格", required = true)
	private BigDecimal pricePaid;

	@ApiModelProperty(value = "支付方式：1微信支付，2支付宝支付", required = true)
	private Integer payType;

	@ApiModelProperty(value = "购买渠道：1web", required = true)
	private Integer channelType;

	@ApiModelProperty(value = "用户备注", required = false)
	private String remarkCus;

	@ApiModelProperty(value = "交易类型：JSAPI -JSAPI支付，NATIVE -Native支付，APP -APP支付", required = true)
	private String tradeType;

	@ApiModelProperty(value = "订单类型（1：普通订单，2：拼团订单，3：砍价订单）", required = true)
	private Integer orderType;

	@ApiModelProperty(value = "订单地址id", required = false)
	private Long shippingAddressId;

	@ApiModelProperty(value = "用户优惠券id", required = false)
	private Long couponUserId;

	@ApiModelProperty(value = "拼团信息", required = false)
	private AssembleSaveREQ assembleSaveREQ;

}
