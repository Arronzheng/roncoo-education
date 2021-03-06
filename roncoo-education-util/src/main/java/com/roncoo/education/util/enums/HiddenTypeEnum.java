package com.roncoo.education.util.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 是否显示（1: 显示；0:不显示)
 */
@Getter
@AllArgsConstructor
public enum HiddenTypeEnum {

	YES(1, "显示"), NO(0, "不显示");

	private Integer code;

	private String desc;

}
