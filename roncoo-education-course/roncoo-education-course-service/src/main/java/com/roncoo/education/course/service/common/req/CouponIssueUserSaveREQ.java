package com.roncoo.education.course.service.common.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * CouponIssueUserSaveREQ 请求对象
 *
 * @author husend
 * @since 2020-05-22
 */
@Data
@Accessors(chain = true)
@ApiModel(value = "CouponIssueUser新增请求对象", description = "优惠券前台用户领取记录表")
public class CouponIssueUserSaveREQ implements Serializable {

        private static final long serialVersionUID = 1L;

        @ApiModelProperty(value = "领取优惠券用户ID")
        private Long uid;

        @ApiModelProperty(value = "优惠券前台领取ID")
        private Long issueCouponId;

        @ApiModelProperty(value = "领取时间")
        private LocalDateTime addTime;

}
