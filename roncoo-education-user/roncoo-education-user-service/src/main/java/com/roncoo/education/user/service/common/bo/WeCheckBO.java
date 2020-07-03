package com.roncoo.education.user.service.common.bo;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class WeCheckBO {
    private String signature;
    private String timestamp;
    private String nonce;
    private String echostr;
}
