package com.roncoo.education.user.service.common.req;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * SvipDeleteREQ对象
 *
 * @author ${author}
 * @since 2019-12-17
 */
@Data
@Accessors(chain = true)
@ApiModel(value = "SvipDeleteREQ删除请求对象", description = "会员信息表删除")
public class SvipDeleteREQ implements Serializable {

    @ApiModelProperty(value = "主键删除", required = true)
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;

}