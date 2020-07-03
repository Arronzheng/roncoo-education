package com.roncoo.education.user.service.common.dto.auth;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.roncoo.education.course.common.bean.vo.OrderInfoVO;
import com.roncoo.education.user.common.bean.vo.UserExtVO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;
import org.bouncycastle.cms.PasswordRecipientId;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * UserLogCommissionViewRESQ对象
 *
 * @author husend
 * @since 2020-06-11
 */
@Data
@Accessors(chain = true)
@ApiModel(value = "AuthUserLogCommissionViewDTO返回对象", description = "用户佣金记录表")
public class AuthUserLogCommissionViewDTO implements Serializable {

        private static final long serialVersionUID = 1L;

        @ApiModelProperty(value = "用户编号")
        @JsonSerialize(using = ToStringSerializer.class)
        private Long userNo;
        @ApiModelProperty(value = "佣金来源用户编号")
        @JsonSerialize(using = ToStringSerializer.class)
        private Long sourceUserNo;
        @ApiModelProperty(value = "订单编号")
        @JsonSerialize(using = ToStringSerializer.class)
        private Long orderNo;
        @ApiModelProperty(value = "佣金")
        private BigDecimal commission;
        @ApiModelProperty(value = "添加时间")
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
        private LocalDateTime addTime;

        private UserExtVO userExtVO;
        private UserExtVO sourceUserExtVO;
        private OrderInfoVO orderInfoVO;
}
