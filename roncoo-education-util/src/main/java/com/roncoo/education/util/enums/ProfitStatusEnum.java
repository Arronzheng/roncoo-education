package com.roncoo.education.util.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author LHR
 */
@Getter
@AllArgsConstructor
public enum ProfitStatusEnum {

	CONFIRMING(1, "确认中"), SUCCESS(2, "成功"), FAIL(3, "失败");

	private Integer code;

	private String desc;
}
