package com.roncoo.education.user.common.bean.qo;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 用户教育信息
 *
 * @author wujing
 */
@Data
@Accessors(chain = true)
public class UserExtInviteQO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 邀请码
     */
    private String inviteCode;

}
