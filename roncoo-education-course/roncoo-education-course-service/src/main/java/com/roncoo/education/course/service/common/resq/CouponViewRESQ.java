package com.roncoo.education.course.service.common.resq;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * CouponViewRESQ对象
 *
 * @author husend
 * @since 2020-05-28
 */
@Data
@Accessors(chain = true)
@ApiModel(value = "CouponViewREQ分页请求对象", description = "优惠券表")
public class CouponViewRESQ implements Serializable {

        private static final long serialVersionUID = 1L;

        @ApiModelProperty(value = "优惠券名称")
        private String title;
        @ApiModelProperty(value = "兑换消耗积分值")
        private Integer integral;
        @ApiModelProperty(value = "兑换的优惠券面值")
        private BigDecimal couponPrice;
        @ApiModelProperty(value = "最低消费多少金额可用优惠券")
        private BigDecimal useMinPrice;
        @ApiModelProperty(value = "优惠券有效期限（单位：天）")
        private Integer couponTime;
        @ApiModelProperty(value = "排序")
        private Integer sort;
        @ApiModelProperty(value = "状态（0：关闭，1：开启）")
        private Integer status;
        @ApiModelProperty(value = "兑换项目添加时间")
        private LocalDateTime addTime;
        @ApiModelProperty(value = "是否删除")
        private Integer isDel;
        @ApiModelProperty(value = "是否是用于兑换的（0：否，1：是）")
        private Integer isConvert;
}
