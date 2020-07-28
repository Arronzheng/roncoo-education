package com.roncoo.education.util.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 *
 */
@Getter
@AllArgsConstructor
public enum UserTypeEnum {

	USER(1, "用户", ""), LECTURER(2, "讲师", "blue");

	private Integer code;

	private String desc;

	private String color;
}
