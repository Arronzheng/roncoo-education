package com.roncoo.education.user.service.common.wechat;

import lombok.Data;

import java.io.Serializable;

/**
 * @description:
 * @author: husend
 * @time: 2020/3/24
 */
@Data
public class AccessToken implements Serializable {
    private String accessToken;
    private String expireIn;

    public AccessToken(String accessToken, String  expireIn) {
        this.accessToken = accessToken;
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
