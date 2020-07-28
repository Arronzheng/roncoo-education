package com.roncoo.education.util.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 *
 */
@Getter
@AllArgsConstructor
public enum IsPayEnum {

    YES(1, "已支付"), NO(0, "未支付");

    private Integer code;

    private String desc;

}
