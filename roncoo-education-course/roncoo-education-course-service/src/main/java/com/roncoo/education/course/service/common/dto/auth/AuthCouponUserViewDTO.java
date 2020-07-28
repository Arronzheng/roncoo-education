package com.roncoo.education.course.service.common.dto.auth;

import com.fasterxml.jackson.annotation.JsonFormat;
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
 * AuthCouponUserViewDTO
 *
 * @author husend
 * @since 2020-05-22
 */
@Data
@Accessors(chain = true)
@ApiModel(value = "AuthCouponUserViewDTO", description = "优惠券发放记录表")
public class AuthCouponUserViewDTO implements Serializable {

        private static final long serialVersionUID = 1L;

        @ApiModelProperty(value = "用户优惠券id")
        @JsonSerialize(using = ToStringSerializer.class)
        private Long id;
        @ApiModelProperty(value = "兑换的项目id")
        @JsonSerialize(using = ToStringSerializer.class)
        private Long cid;
        @ApiModelProperty(value = "优惠券所属用户")
        @JsonSerialize(using = ToStringSerializer.class)
        private Long uid;
        @ApiModelProperty(value = "优惠券名称")
        private String couponTitle;
        @ApiModelProperty(value = "优惠券的面值")
        private BigDecimal couponPrice;
        @ApiModelProperty(value = "最低消费多少金额可用优惠券")
        private BigDecimal useMinPrice;
        @ApiModelProperty(value = "优惠券创建时间")
        @JsonFormat(pattern = "yyyy/MM/dd")
        private LocalDateTime addTime;
        @ApiModelProperty(value = "优惠券结束时间")
        @JsonFormat(pattern = "yyyy/MM/dd")
        private LocalDateTime endTime;
        @ApiModelProperty(value = "使用时间")
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
        private LocalDateTime useTime;
        @ApiModelProperty(value = "获取方式")
        private String type;
        @ApiModelProperty(value = "状态（0：未使用，1：已使用, 2:已过期）")
        private Integer status;
        @ApiModelProperty(value = "是否有效")
        private Integer isFail;
}
