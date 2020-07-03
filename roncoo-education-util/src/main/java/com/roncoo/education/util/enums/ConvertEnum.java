package com.roncoo.education.util.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author LHR
 */
@Getter
@AllArgsConstructor
public enum ConvertEnum {

    YES(1, "可兑换"), NO(2, "不可兑换");


    private Integer code;

    private String desc;
}
