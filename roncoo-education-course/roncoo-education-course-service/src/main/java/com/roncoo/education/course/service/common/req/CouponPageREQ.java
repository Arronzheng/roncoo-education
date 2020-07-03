package com.roncoo.education.course.service.common.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * CouponPageREQ对象
 *
 * @author husend
 * @since 2020-05-28
 */
@Data
@Accessors(chain = true)
@ApiModel(value = "CouponPageREQ分页请求对象", description = "优惠券表")
public class CouponPageREQ implements Serializable {
    /**
	 * 当前页
	 */
    @ApiModelProperty(value = "当前页", required = true)
    private int pageCurrent = 1;
    /**
     * 每页记录数
     */
    @ApiModelProperty(value = "每页记录数", required = true)
    private int pageSize = 20;
    /**
     * 状态(1:正常，0:禁用)
     */
    @ApiModelProperty(value = "状态(1:正常，0:禁用)", required = false)
    private Integer status;
    /**
	 * 优惠券名称
	 */
    @ApiModelProperty(value = "优惠券名称", required = false)
    private String title;

}
