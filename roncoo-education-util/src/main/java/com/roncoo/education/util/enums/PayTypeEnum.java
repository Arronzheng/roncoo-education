package com.roncoo.education.util.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 *
 */
@Getter
@AllArgsConstructor
public enum PayTypeEnum {

	WEIXIN(1, "微信支付"), ALIPAY(2, "支付宝支付");

	private Integer code;

	private String desc;

}
