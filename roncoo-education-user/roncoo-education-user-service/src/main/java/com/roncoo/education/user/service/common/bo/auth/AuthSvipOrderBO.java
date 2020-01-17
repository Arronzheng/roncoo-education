package com.roncoo.education.user.service.common.bo.auth;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * SvipSaveREQ 请求对象
 *
 * @author ${author}
 * @since 2019-12-17
 */
@Data
@Accessors(chain = true)
public class AuthSvipOrderBO implements Serializable {

        private static final long serialVersionUID = 1L;

        @ApiModelProperty(value = "用户编号")
        private Long userNo;

        @ApiModelProperty(value = "支付方式：1微信支付，2支付宝支付", required = true)
        private Integer payType;

        @ApiModelProperty(value = "购买渠道：1web", required = true)
        private Integer channelType;

        @ApiModelProperty(value = "订单名称", required = true)
        private String orderName;

}