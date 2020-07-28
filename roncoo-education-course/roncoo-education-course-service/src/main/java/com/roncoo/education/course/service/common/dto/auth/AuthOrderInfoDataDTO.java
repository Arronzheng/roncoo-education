package com.roncoo.education.course.service.common.dto.auth;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * <p>
 * 订单信息表
 * </p>
 */
@Data
@Accessors(chain = true)
public class AuthOrderInfoDataDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 待付款订单数量
	 */
	@ApiModelProperty(value = "待付款订单数量")
	private Integer unpaidCount;
	/**
	 * 待拼成订单数量
	 */
	@ApiModelProperty(value = "待拼成订单数量")
	private Integer unAssembleCount;
	/**
	 * 待发货订单数量
	 */
	@ApiModelProperty(value = "待发货订单数量")
	private Integer unshippedCount;
	/**
	 * 待收货订单数量
	 */
	@ApiModelProperty(value = "待收货订单数量")
	private Integer receivedCount;
	/**
	 * 已完成订单数量
	 */
	@ApiModelProperty(value = "已完成订单数量")
	private Integer completeCount;
}
