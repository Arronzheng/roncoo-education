package com.roncoo.education.course.service.common.bo.auth;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
@Data
@Accessors(chain = true)
public class AuthOrderInfoUpdateBO implements Serializable {
    /**
     * 订单编号
     */
    @ApiModelProperty(value = "订单编号")
    @JsonSerialize(using = ToStringSerializer.class)
    private Long orderNo;
    /**
     * 地址编号
     */
    @ApiModelProperty(value = "订单编号")
    @JsonSerialize(using = ToStringSerializer.class)
    private Long shippingAddressId;
}
