package com.roncoo.education.util.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author
 */
@Getter
@AllArgsConstructor
public enum PricePaidSourceEnum {
	ORIGINAL(0,"应付金额"), VIP(1,"会员"), ASSEMBLE(2,"拼团价"), BARGAIN(3,"砍价"), ACTIVITY(4,"活动价");

	private Integer code;

	private String desc;
}
