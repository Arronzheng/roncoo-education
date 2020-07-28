package com.roncoo.education.course.service.common.bo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * AssemblePageREQ对象
 *
 * @author husend
 * @since 2020-04-14
 */
@Data
@Accessors(chain = true)
@ApiModel(value = "是否正在拼团请求对象", description = "是否正在拼团请求对象")
public class AssembleIngBO implements Serializable {
    /**
	 * 当前页
	 */
    @ApiModelProperty(value = "用户编号", required = true)
    private Long userNo;
    /**
     * 产品id
     */
    @ApiModelProperty(value = "产品id", required = true)
    private Long pid;

}
