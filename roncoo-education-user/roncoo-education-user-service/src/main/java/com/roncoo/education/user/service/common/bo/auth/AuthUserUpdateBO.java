package com.roncoo.education.user.service.common.bo.auth;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
@Data
@Accessors(chain = true)
public class AuthUserUpdateBO implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
     * 主键
     */
    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty(value = "主键", required = true)
    private Long id;
    /**
     * 手机号码
     */
    @ApiModelProperty(value = "手机号码")
    private String mobile;
    /**
     * 手机验证码
     */
    @ApiModelProperty(value = "手机验证码", required = true)
    private String code;
    /**
     * clientId
     */
    @ApiModelProperty(value = "clientId", required = true)
    private String clientId;
}
