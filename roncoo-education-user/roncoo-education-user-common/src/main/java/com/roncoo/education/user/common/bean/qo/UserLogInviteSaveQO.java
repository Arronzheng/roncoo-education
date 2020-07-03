package com.roncoo.education.user.common.bean.qo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * UserLogInviteSaveREQ 请求对象
 *
 * @author husend
 * @since 2020-05-19
 */
@Data
@Accessors(chain = true)
public class UserLogInviteSaveQO implements Serializable {

        private static final long serialVersionUID = 1L;

        private Long inviteUserNo;

        private Long invitedUserNo;

        private Integer status;

        private LocalDateTime addTime;

}
