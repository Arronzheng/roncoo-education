package com.roncoo.education.util.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 *
 */
@Getter
@AllArgsConstructor
public enum ChannelTypeEnum {
	PC(1, "PC端");

	private Integer code;

	private String desc;

}
