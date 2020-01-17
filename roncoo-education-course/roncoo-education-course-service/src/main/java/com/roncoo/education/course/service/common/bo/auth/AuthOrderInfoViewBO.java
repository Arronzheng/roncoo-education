package com.roncoo.education.course.service.common.bo.auth;

import java.io.Serializable;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * <p>
 * 订单信息表
 * </p>
 *
 * @author YZJ
 */
@Data
@Accessors(chain = true)
public class AuthOrderInfoViewBO implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 订单编号
	 */
	@ApiModelProperty(value = "订单编号 ", required = true)
	private Long orderNo;
	/**
	 * 订单流水号
	 */
	@ApiModelProperty(value = "订单流水号 ", required = true)
	@JsonSerialize(using = ToStringSerializer.class)
	private Long serialNumber;
	/**
	 * 支付类型
	 */
	@ApiModelProperty(value = "支付类型1：微信，2支付宝 ", required = true)
	private Integer payType;
	/**
	 * 用户编号
	 */
	@ApiModelProperty(value = "用户编号", required = true)
	@JsonSerialize(using = ToStringSerializer.class)
	private Long userNo;
}
