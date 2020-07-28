package com.roncoo.education.util.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 *
 */
@Getter
@AllArgsConstructor
public enum LoginStatusEnum {

	SUCCESS(1, "登录成功", ""),
	//
	FAIL(0, "登录失败", "red");

	private Integer code;

	private String desc;

	private String color;
}
