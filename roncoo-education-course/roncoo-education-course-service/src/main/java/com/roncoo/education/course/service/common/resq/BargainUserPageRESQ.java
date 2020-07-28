package com.roncoo.education.course.service.common.resq;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * BargainUserPageRESQ对象
 *
 * @author husend
 * @since 2020-04-14
 */
@Data
@Accessors(chain = true)
@ApiModel(value = "BargainUserPageREQ分页请求对象", description = "用户参与砍价表")
public class BargainUserPageRESQ implements Serializable {

        private static final long serialVersionUID = 1L;

        @ApiModelProperty(value = "用户参与砍价表ID")
        @JsonSerialize(using = ToStringSerializer.class)
        private Long id;
        @ApiModelProperty(value = "用户ID")
        @JsonSerialize(using = ToStringSerializer.class)
        private Long uid;
        @ApiModelProperty(value = "砍价产品id")
        @JsonSerialize(using = ToStringSerializer.class)
        private Long bargainId;
        @ApiModelProperty(value = "砍价的最低价")
        private BigDecimal bargainPriceMin;
        @ApiModelProperty(value = "砍价金额")
        private BigDecimal bargainPrice;
        @ApiModelProperty(value = "砍掉的价格")
        private BigDecimal price;
        @ApiModelProperty(value = "状态 1参与中 2 活动结束参与失败 3活动结束参与成功")
        private Integer status;
        @ApiModelProperty(value = "参与时间")
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
        private Date addTime;
        @ApiModelProperty(value = "地址id")
        @JsonSerialize(using = ToStringSerializer.class)
        private Long addressId;
        @ApiModelProperty(value = "是否取消")
        private Integer isDel;
        @ApiModelProperty(value = "是否取消")
        private Boolean isStart;
}
