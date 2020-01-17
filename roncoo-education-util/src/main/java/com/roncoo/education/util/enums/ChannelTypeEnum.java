package com.roncoo.education.util.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author wujing
 */
@Getter
@AllArgsConstructor
public enum ChannelTypeEnum {
	PC(1, "PC端");

	private Integer code;

	private String desc;

}
