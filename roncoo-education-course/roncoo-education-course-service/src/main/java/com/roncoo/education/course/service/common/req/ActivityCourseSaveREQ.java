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
 * ActivityCourseSaveREQ 请求对象
 *
 * @author husend
 * @since 2020-04-10
 */
@Data
@Accessors(chain = true)
@ApiModel(value = "ActivityCourse新增请求对象", description = "活动专区课程关联表")
public class ActivityCourseSaveREQ implements Serializable {

        private static final long serialVersionUID = 1L;

        @ApiModelProperty(value = "课程ID")
        @JsonSerialize(using = ToStringSerializer.class)
        private Long courseId;

        @ApiModelProperty(value = "活动类别（1：拼团，2：砍价，3：秒杀）")
        private Integer activityCategory;

}
