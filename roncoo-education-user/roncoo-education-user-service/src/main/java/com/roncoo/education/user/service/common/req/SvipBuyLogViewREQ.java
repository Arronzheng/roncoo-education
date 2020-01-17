package com.roncoo.education.user.service.common.req;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * SvipBuyLogViewREQ对象
 *
 * @author ${author}
 * @since 2019-12-25
 */
@Data
@Accessors(chain = true)
@ApiModel(value = "SvipBuyLogView请求对象", description = "会员购买日志表")
public class SvipBuyLogViewREQ implements Serializable {

    @ApiModelProperty(value = "主键查询", required = true)
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;

}