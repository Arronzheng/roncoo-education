package com.roncoo.education.course.service.common.req;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * CourseCommentListREQ对象
 *
 * @author hw
 * @since 2019-12-05
 */
@Data
@Accessors(chain = true)
@ApiModel(value = "CourseCommentListREQ列表请求对象", description = "列表")
public class CourseCommentListREQ implements Serializable {

    @ApiModelProperty(value = "主键查询", required = false)
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;

}