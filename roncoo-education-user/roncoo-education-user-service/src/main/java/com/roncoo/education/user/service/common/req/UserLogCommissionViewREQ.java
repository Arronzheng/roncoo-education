package com.roncoo.education.user.service.common.req;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * UserLogCommissionViewREQ对象
 *
 * @author husend
 * @since 2020-06-11
 */
@Data
@Accessors(chain = true)
@ApiModel(value = "UserLogCommissionView请求对象", description = "用户佣金记录表")
public class UserLogCommissionViewREQ implements Serializable {

    @ApiModelProperty(value = "主键查询", required = true)
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;

}
