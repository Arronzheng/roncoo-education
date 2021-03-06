package com.roncoo.education.util.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 *
 */
@Getter
@AllArgsConstructor
public enum AdvTargetEnum {

	BLANK("_blank", "新窗口打开"), SELF("_self", "同窗口打开");

	private String code;

	private String desc;

}
