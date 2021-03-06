package com.roncoo.education.course.service.common.req;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * CouponListREQ对象
 *
 * @author husend
 * @since 2020-05-28
 */
@Data
@Accessors(chain = true)
@ApiModel(value = "CouponListREQ列表请求对象", description = "优惠券表列表")
public class CouponListREQ implements Serializable {

    @ApiModelProperty(value = "主键查询", required = false)
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;

}
