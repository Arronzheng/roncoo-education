package com.roncoo.education.util.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author wujing
 */
@Getter
@AllArgsConstructor
public enum FileTypeEnum {

	ALIYUN(1, "阿里云"), QINIU(2, "七牛"), LOCAL(3, "本地"), TENCENT(4, "腾讯云");

	private Integer code;

	private String desc;

}
