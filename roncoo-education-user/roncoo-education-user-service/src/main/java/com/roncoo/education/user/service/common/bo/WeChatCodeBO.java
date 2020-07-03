package com.roncoo.education.user.service.common.bo;

import lombok.Data;

import java.io.Serializable;

/**
 * @description:
 * @author: husend
 * @time: 2020/3/27
 */
@Data
public class WeChatCodeBO implements Serializable {
    private String code;
    private String state;
}
