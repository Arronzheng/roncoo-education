package com.roncoo.education.course.service.common.req;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * ActivityCourseUpdateREQ 请求对象
 *
 * @author husend
 * @since 2020-04-10
 */
@Data
@Accessors(chain = true)
@ApiModel(value = "ActivityCourse更新请求对象", description = "活动专区课程关联表")
public class ActivityCourseUpdateREQ implements Serializable {

        private static final long serialVersionUID = 1L;

        @ApiModelProperty(value = "主键", required = true)
        @JsonSerialize(using = ToStringSerializer.class)
        private Long id;

        @ApiModelProperty(value = "状态(1:正常;0:禁用)")
        private Integer statusId;

        @ApiModelProperty(value = "排序")
        private Integer sort;

        @ApiModelProperty(value = "专区编号")
        @JsonSerialize(using = ToStringSerializer.class)
        private Long activityId;

        @ApiModelProperty(value = "位置(0电脑端，1微信端)")
        private Integer activityLocation;

        @ApiModelProperty(value = "课程ID")
        @JsonSerialize(using = ToStringSerializer.class)
        private Long courseId;

        @ApiModelProperty(value = "活动类别（1：拼团，2：砍价，3：秒杀）")
        private Integer activityCategory;

        @ApiModelProperty(value = "价格（拼团、秒杀）")
        private BigDecimal coursePrice;

        @ApiModelProperty(value = "拼团人数")
        private Integer groupNum;

        @ApiModelProperty(value = "库存（砍价、秒杀）")
        private Integer courseStock;

        @ApiModelProperty(value = "砍价刀数")
        private Integer knifeNum;

        @ApiModelProperty(value = "限时时间（整数小时）")
        private Integer limitTime;
}
