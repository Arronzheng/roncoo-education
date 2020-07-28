package com.roncoo.education.user.service.common.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * UserLogCommissionPageREQ对象
 *
 * @author husend
 * @since 2020-06-11
 */
@Data
@Accessors(chain = true)
@ApiModel(value = "UserLogCommissionPageREQ分页请求对象", description = "用户佣金记录表")
public class UserLogCommissionPageREQ implements Serializable {
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
    private Integer statusId;
    /**
	 * 位置(0电脑端，1微信端)
	 */
    @ApiModelProperty(value = "位置(0电脑端，1微信端)", required = false)
    private Integer platShow;

}