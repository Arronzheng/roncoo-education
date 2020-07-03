package com.roncoo.education.course.service.common.dto.auth;

import java.io.Serializable;
import java.math.BigDecimal;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 订单支付信息表
 *
 * @author wujing
 */
@Data
@Accessors(chain = true)
public class AuthOrderPayDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private String orderNo;

	private String payMessage;

	private String courseName;

	private BigDecimal price;

	private Integer payType;

	private String serialNumber;

	//下面几个字段是JSAPI支付需要的
	private String prepayId;

	private String appId;

	private String timeStamp;

	private String nonceStr;

	private String signType;

	private String paySign;

}
