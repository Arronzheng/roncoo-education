package com.roncoo.education.user.service.common.bo.auth;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @description:
 * @author: husend
 * @time: 2020/5/19
 */
@Data
@Accessors(chain = true)
public class AuthApiUserInviteLogBO implements Serializable {

    /**
     * 用户编号
     */
    @ApiModelProperty(value = "用户编号", required = true)
    private Long userNo;

}
