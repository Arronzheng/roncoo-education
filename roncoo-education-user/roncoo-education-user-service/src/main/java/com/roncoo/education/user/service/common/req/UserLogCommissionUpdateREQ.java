package com.roncoo.education.user.service.common.req;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * UserLogCommissionUpdateREQ 请求对象
 *
 * @author husend
 * @since 2020-06-11
 */
@Data
@Accessors(chain = true)
@ApiModel(value = "UserLogCommission更新请求对象", description = "用户佣金记录表")
public class UserLogCommissionUpdateREQ implements Serializable {

        private static final long serialVersionUID = 1L;

        @ApiModelProperty(value = "主键", required = true)
        @JsonSerialize(using = ToStringSerializer.class)
        private Long id;
        @ApiModelProperty(value = "用户编号")
        private Long userNo;
        @ApiModelProperty(value = "佣金来源用户编号")
        private Long sourceUserNo;
        @ApiModelProperty(value = "订单编号")
        private Long orderNo;
        @ApiModelProperty(value = "佣金")
        private BigDecimal commission;
        @ApiModelProperty(value = "添加时间")
        private LocalDateTime addTime;
}
