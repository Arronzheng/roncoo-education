package com.roncoo.education.course.service.common.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * CouponIssueSaveREQ 请求对象
 *
 * @author husend
 * @since 2020-05-22
 */
@Data
@Accessors(chain = true)
@ApiModel(value = "CouponIssue新增请求对象", description = "优惠券前台领取表")
public class CouponIssueSaveREQ implements Serializable {

        private static final long serialVersionUID = 1L;

        @ApiModelProperty(value = "优惠券ID")
        private Long cid;

        @ApiModelProperty(value = "优惠券领取开启时间")
        private LocalDateTime startTime;

        @ApiModelProperty(value = "优惠券领取结束时间")
        private LocalDateTime endTime;

        @ApiModelProperty(value = "优惠券领取数量")
        private Integer totalCount;

        @ApiModelProperty(value = "优惠券剩余领取数量")
        private Integer remainCount;

        @ApiModelProperty(value = "是否无限张数")
        private Integer isPermanent;

        @ApiModelProperty(value = "1 正常 0 未开启 -1 已无效")
        private Integer status;

        @ApiModelProperty(value = "是否删除")
        private Integer isDel;

        @ApiModelProperty(value = "优惠券添加时间")
        private LocalDateTime addTime;

}
