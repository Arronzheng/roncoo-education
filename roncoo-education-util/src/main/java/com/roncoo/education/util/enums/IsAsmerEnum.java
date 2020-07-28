package com.roncoo.education.util.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 *
 */
@Getter
@AllArgsConstructor
public enum IsAsmerEnum {

    YES(1, "团长"), NO(0, "团员");

    private Integer code;

    private String desc;

}
