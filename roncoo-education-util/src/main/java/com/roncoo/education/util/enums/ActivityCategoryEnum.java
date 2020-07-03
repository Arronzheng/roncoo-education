package com.roncoo.education.util.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author LHR
 */
@Getter
@AllArgsConstructor
public enum ActivityCategoryEnum {

    ASSEMBLE(1, "拼团"), BARGAIN(2, "砍价"), SECKILL(3, "秒杀");


    private Integer code;

    private String desc;
}
