package com.roncoo.education.system.service.common.req;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * PosterDeleteREQ对象
 *
 * @author husend
 * @since 2020-07-25
 */
@Data
@Accessors(chain = true)
@ApiModel(value = "PosterDeleteREQ删除请求对象", description = "删除")
public class PosterDeleteREQ implements Serializable {

    @ApiModelProperty(value = "主键删除", required = true)
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;

}
