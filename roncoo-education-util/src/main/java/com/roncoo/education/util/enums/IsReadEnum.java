package com.roncoo.education.util.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 *
 */
@Getter
@AllArgsConstructor
public enum IsReadEnum {

	YES(1, "已读"), NO(0, "未读");

	private Integer code;

	private String desc;

}
