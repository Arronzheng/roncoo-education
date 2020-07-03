package com.roncoo.education.course.service.common.req;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * ActivityCourseListREQ对象
 *
 * @author husend
 * @since 2020-04-10
 */
@Data
@Accessors(chain = true)
@ApiModel(value = "ActivityCourseListREQ列表请求对象", description = "活动专区课程关联表列表")
public class ActivityCourseListREQ implements Serializable {

    @ApiModelProperty(value = "主键查询", required = false)
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;

}
