package com.roncoo.education.course.service.common.bo.auth;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @description:
 * @author: husend
 * @time: 2020/5/27
 */
@Data
@Accessors(chain = true)
public class AuthCouponConvertBO implements Serializable {
    /**
     * 用户编号
     */
    private Long userNo;

    /**
     * 邀请码
     */
    private String code;
}
