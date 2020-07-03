package com.roncoo.education.user.service.common.req;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * UserLogCommissionDeleteREQ对象
 *
 * @author husend
 * @since 2020-06-11
 */
@Data
@Accessors(chain = true)
@ApiModel(value = "UserLogCommissionDeleteREQ删除请求对象", description = "用户佣金记录表删除")
public class UserLogCommissionDeleteREQ implements Serializable {

    @ApiModelProperty(value = "主键删除", required = true)
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;

}
