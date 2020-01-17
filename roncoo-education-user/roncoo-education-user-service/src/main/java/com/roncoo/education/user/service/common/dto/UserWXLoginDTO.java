package com.roncoo.education.user.service.common.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Data
@Accessors(chain = true)
public class UserWXLoginDTO implements Serializable {
    /**
     * 开放平台appId
     */
    @ApiModelProperty(value = "appId")
    private String appid;
    /**
     * 重定向地址
     */
    @ApiModelProperty(value = "重定向地址")
    private String redirectUri;
    /**
     * 自定义的参数，会原样返回
     */
    @ApiModelProperty(value = "自定义的参数")
    private String state;

}
