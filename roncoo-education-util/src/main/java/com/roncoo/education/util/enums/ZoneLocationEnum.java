package com.roncoo.education.util.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 *
 */
@Getter
@AllArgsConstructor
public enum ZoneLocationEnum {

	PC(0, "PC端", ""),
	//
	WX(1, "微信端", "green");

	private Integer code;

	private String desc;

	private String color;
}
