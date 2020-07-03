package com.roncoo.education.user.common.bean.vo;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @description:
 * @author: husend
 * @time: 2020/6/2
 */
@Data
@Accessors(chain = true)
public class UserLogInviteVO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    private Long id;
    /**
     * 邀请人编号
     */
    private Long inviteUserNo;
    /**
     * 被邀请人编号
     */
    private Long invitedUserNo;
    /**
     * 邀请状态
     */
    private Integer status;
    /**
     * 添加时间
     */
    private LocalDateTime addTime;
}
