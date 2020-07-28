package com.roncoo.education.course.service.common.bo.auth;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 订单信息分页列表
 *
 *
 */
@Data
@Accessors(chain = true)
public class AuthOrderInfoDataBO implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 用户编号
	 */
	@ApiModelProperty(value = "用户编号（用户订单用到）")
	private Long userNo;
}
