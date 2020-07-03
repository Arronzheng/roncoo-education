package com.roncoo.education.user.service.common.dto;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

/**
 * @description: jssdk配置参数
 * @author: husend
 * @time: 2020/3/25
 */
@Data
@Accessors(chain = true)
public class JssdkConfigDTO implements Serializable {
    private String signature;
    private String url;
    private String nonceStr;
    private String timestamp;
    private String appId;
    private boolean debug;
    private String state;
    private List<String> jsApiList;
}
