package com.roncoo.education.course.service.common.bo.auth;

import java.io.Serializable;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 订单继续支付
 *
 *
 */
@Data
@Accessors(chain = true)
public class AuthOrderInfoContinuePayBO implements Serializable {

    private static final long serialVersionUID = 1L;
    /***
     * 订单编号
     */
    @ApiModelProperty(value = "订单编号", required = true)
    private long orderNo;
    /***
     * 支付类型
     */
    @ApiModelProperty(value = "支付方式：1微信支付，2支付宝支付", required = true)
    private Integer payType;
    /***
     * 交易类型
     */
    @ApiModelProperty(value = "交易类型：JSAPI -JSAPI支付，NATIVE -Native支付，APP -APP支付", required = true)
    private String tradeType;

    @ApiModelProperty(value = "订单地址id", required = false)
    private Long shippingAddressId;

    @ApiModelProperty(value = "用户优惠券id", required = false)
    private Long couponUserId;

}
