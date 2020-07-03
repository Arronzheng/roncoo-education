package com.roncoo.education.user.service.common.resq;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * UserLogInviteViewRESQ对象
 *
 * @author husend
 * @since 2020-05-19
 */
@Data
@Accessors(chain = true)
@ApiModel(value = "UserLogInviteViewREQ分页请求对象", description = "用户邀请记录表")
public class UserLogInviteViewRESQ implements Serializable {

        private static final long serialVersionUID = 1L;

        @ApiModelProperty(value = "邀请人编号")
        private Long inviteUserNo;
        @ApiModelProperty(value = "被邀请用户编号")
        private Long invitedUserNo;
        @ApiModelProperty(value = "邀请状态（1：成功，2：未成功（邀请的人未购买））")
        private Integer status;
        @ApiModelProperty(value = "添加时间")
        private LocalDateTime addTime;
}
