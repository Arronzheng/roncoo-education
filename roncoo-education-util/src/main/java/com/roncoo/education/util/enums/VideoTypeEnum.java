package com.roncoo.education.util.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author wujing
 */
@Getter
@AllArgsConstructor
public enum VideoTypeEnum {

	POLYV(1, "保利威视"), TENCENT(2, "腾讯");

	private Integer code;

	private String desc;

}
