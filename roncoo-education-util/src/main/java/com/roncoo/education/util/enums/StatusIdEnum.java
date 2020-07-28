package com.roncoo.education.util.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 *
 */
@Getter
@AllArgsConstructor
public enum StatusIdEnum {

	YES(1, "正常", ""), NO(0, "禁用", "red");

	private Integer code;

	private String desc;

	private String color;

}
