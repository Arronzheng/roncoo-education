package com.roncoo.education.course.service.common.req;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * CouponIssueUserViewREQ对象
 *
 * @author husend
 * @since 2020-05-22
 */
@Data
@Accessors(chain = true)
@ApiModel(value = "CouponIssueUserView请求对象", description = "优惠券前台用户领取记录表")
public class CouponIssueUserViewREQ implements Serializable {

    @ApiModelProperty(value = "主键查询", required = true)
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;

}
