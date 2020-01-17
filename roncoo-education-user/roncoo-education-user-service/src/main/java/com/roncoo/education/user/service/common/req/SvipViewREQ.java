package com.roncoo.education.user.service.common.req;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * SvipViewREQ对象
 *
 * @author ${author}
 * @since 2019-12-17
 */
@Data
@Accessors(chain = true)
@ApiModel(value = "SvipView请求对象", description = "会员信息表")
public class SvipViewREQ implements Serializable {

    @ApiModelProperty(value = "主键查询", required = true)
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;

}