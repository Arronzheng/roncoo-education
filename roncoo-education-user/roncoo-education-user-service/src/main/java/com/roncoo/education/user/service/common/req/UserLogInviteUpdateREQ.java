package com.roncoo.education.user.service.common.req;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * UserLogInviteUpdateREQ 请求对象
 *
 * @author husend
 * @since 2020-05-19
 */
@Data
@Accessors(chain = true)
@ApiModel(value = "UserLogInvite更新请求对象", description = "用户邀请记录表")
public class UserLogInviteUpdateREQ implements Serializable {

        private static final long serialVersionUID = 1L;

        @ApiModelProperty(value = "主键", required = true)
        @JsonSerialize(using = ToStringSerializer.class)
        private Long id;
        @ApiModelProperty(value = "邀请人编号")
        private Long inviteUserNo;
        @ApiModelProperty(value = "被邀请用户编号")
        private Long invitedUserNo;
        @ApiModelProperty(value = "邀请状态（1：成功，2：未成功（邀请的人未购买））")
        private Integer status;
        @ApiModelProperty(value = "添加时间")
        private LocalDateTime addTime;
}
