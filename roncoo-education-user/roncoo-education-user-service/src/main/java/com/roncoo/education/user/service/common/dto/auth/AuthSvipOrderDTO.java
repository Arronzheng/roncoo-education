package com.roncoo.education.user.service.common.dto.auth;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
@Accessors(chain = true)
public class AuthSvipOrderDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private String orderNo;

    private String payMessage;

    private String orderName;

    private BigDecimal price;

    private Integer payType;

    private String serialNo;
}
