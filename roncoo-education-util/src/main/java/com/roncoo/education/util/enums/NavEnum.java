package com.roncoo.education.util.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum NavEnum {

	INDEX("/index", "首页"), COURSE("/list", "录播中心"), RECRUIT("/recruit", "讲师招募");

	private String code;

	private String desc;
}
