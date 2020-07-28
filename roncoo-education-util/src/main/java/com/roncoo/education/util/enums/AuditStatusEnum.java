package com.roncoo.education.util.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 *
 */
@Getter
@AllArgsConstructor
public enum AuditStatusEnum {

	WAIT(0, "待审核",""),
	//
	SUCCESS(1, "审核通过","green"),
	//
	FAIL(2, "审核不通过","red");

	private Integer code;

	private String desc;

	private String color;
}
