package com.roncoo.education.util.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 *
 */
@Getter
@AllArgsConstructor
public enum TradeTypeEnum {

	ONLINE(1, "线上支付"), OFFLINE(2, "线下支付");

	private Integer code;

	private String desc;

}
