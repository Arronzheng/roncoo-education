package com.roncoo.education.util.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 *
 */
@Getter
@AllArgsConstructor
public enum IsDefaultEnum {

	YES(1, "是"), NO(0, "否");

	private Integer code;

	private String desc;

}
