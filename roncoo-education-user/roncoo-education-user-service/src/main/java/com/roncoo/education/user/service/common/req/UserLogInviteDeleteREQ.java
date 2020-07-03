package com.roncoo.education.user.service.common.req;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * UserLogInviteDeleteREQ对象
 *
 * @author husend
 * @since 2020-05-19
 */
@Data
@Accessors(chain = true)
@ApiModel(value = "UserLogInviteDeleteREQ删除请求对象", description = "用户邀请记录表删除")
public class UserLogInviteDeleteREQ implements Serializable {

    @ApiModelProperty(value = "主键删除", required = true)
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;

}
