package com.roncoo.education.util.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 *
 */
@Getter
@AllArgsConstructor
public enum CategoryTypeEnum {

    COURSE(1, "课程分类"), RESOURCE(2, "资源分类");

    private Integer code;

    private String desc;

}
