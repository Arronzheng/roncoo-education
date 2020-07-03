package com.roncoo.education.user.service.common.dto.auth;

import com.roncoo.education.user.service.common.resq.UserLogInviteListRESQ;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

/**
 * @description:
 * @author: husend
 * @time: 2020/5/20
 */
@Data
@Accessors(chain = true)
public class AuthUserExtInviteCodeDTO implements Serializable {
    /**
     * 邀请码
     */
    @ApiModelProperty(value = "邀请码", required = false)
    private String inviteCode;
    /**
     * 邀请记录
     */
    @ApiModelProperty(value = "邀请记录", required = false)
    private List<UserLogInviteListRESQ> userLogInviteListRESQS;
}
