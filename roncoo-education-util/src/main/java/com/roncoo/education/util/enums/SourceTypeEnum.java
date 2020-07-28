package com.roncoo.education.util.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 *
 */
@Getter
@AllArgsConstructor
public enum SourceTypeEnum {

	REGISTER(1, "注册"), VIEW(2, "观看");

	private Integer code;

	private String desc;

}
