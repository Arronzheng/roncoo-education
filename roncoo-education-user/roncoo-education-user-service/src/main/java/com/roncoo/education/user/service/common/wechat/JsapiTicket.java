package com.roncoo.education.user.service.common.wechat;

import lombok.Data;

import java.io.Serializable;

/**
 * @description:
 * @author: husend
 * @time: 2020/3/24
 */
@Data
public class JsapiTicket implements Serializable {
    private String ticket;
    private String expireIn;

    public JsapiTicket(String ticket, String  expireIn) {
        this.ticket = ticket;
        this.expireIn = expireIn;
    }

    /**
     * 判断token是否过期
     * @return
     */
//    public boolean isExpireTime() {
//        return System.currentTimeMillis() > expireTime;
//    }
}
