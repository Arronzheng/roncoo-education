package com.roncoo.education.course.service.common.bo.auth;

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
public class AuthCouponUserListBO implements Serializable {
    /**
     * 用户编号
     */
    private Long userNo;
}
