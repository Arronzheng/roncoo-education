package com.roncoo.education.util.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 是否定时发送
 *
 *
 *
 */
@Getter
@AllArgsConstructor
public enum IsTimeSendEnum {

	YES(1, "是"), NO(0, "否");

	private Integer code;

	private String desc;

}
