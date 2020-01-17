package com.roncoo.education.user.service.common.req;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * SvipListREQ对象
 *
 * @author ${author}
 * @since 2019-12-17
 */
@Data
@Accessors(chain = true)
@ApiModel(value = "SvipListREQ列表请求对象", description = "会员信息表列表")
public class SvipListREQ implements Serializable {

    @ApiModelProperty(value = "主键查询", required = false)
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;

}