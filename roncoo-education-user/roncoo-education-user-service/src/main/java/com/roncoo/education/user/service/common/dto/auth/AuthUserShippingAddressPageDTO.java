package com.roncoo.education.user.service.common.dto.auth;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
@Data
@Accessors(chain = true)
public class AuthUserShippingAddressPageDTO implements Serializable {
    /**
     * 主键
     */
    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty(value = "主键", required = true)
    private Long id;
    /**
     * 收货人
     */
    @ApiModelProperty(value = "收货人", required = true)
    private String receiverName;
    /**
     * 手机号码
     */
    @ApiModelProperty(value = "手机号码", required = true)
    private String receiverPhone;
    /**
     * 省份
     */
    @ApiModelProperty(value = "省份", required = true)
    private String receiverProvince;
    /**
     * 市
     */
    @ApiModelProperty(value = "市", required = true)
    private String receiverCity;
    /**
     * 区
     */
    @ApiModelProperty(value = "县/区", required = true)
    private String receiverDistrict;
    /**
     * 详细地址
     */
    @ApiModelProperty(value = "详细地址", required = true)
    private String receiverAddress;
    /**
     * 设为默认
     */
    @ApiModelProperty(value = "设为默认", required = false)
    private Integer isToleration;
}
