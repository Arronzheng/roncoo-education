package com.roncoo.education.util.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author wuyun
 */
@Getter
@AllArgsConstructor
public enum IsVideoEnum {

    YES("1", "存在"), NO("0", "否");

    private String code;

    private String desc;

}
