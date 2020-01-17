package com.roncoo.education.util.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 */
@Getter
@AllArgsConstructor
public enum IsVipEnum {

	YES(1, "vip", ""), NO(0, "非vip", "red");

	private Integer code;

	private String desc;

	private String color;

}
