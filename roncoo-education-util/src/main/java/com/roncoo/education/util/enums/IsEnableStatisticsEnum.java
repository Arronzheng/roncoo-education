package com.roncoo.education.util.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 是否开启统计
 *
 *
 */
@Getter
@AllArgsConstructor
public enum IsEnableStatisticsEnum {

	YES(1, "开启"), NO(0, "关闭");

	private Integer code;

	private String desc;

}
