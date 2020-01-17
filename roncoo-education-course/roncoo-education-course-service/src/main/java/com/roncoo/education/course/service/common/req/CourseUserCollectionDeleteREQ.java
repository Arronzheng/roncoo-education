package com.roncoo.education.course.service.common.req;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * CourseUserCollectionDeleteREQ对象
 *
 * @author ${author}
 * @since 2020-01-04
 */
@Data
@Accessors(chain = true)
@ApiModel(value = "CourseUserCollectionDeleteREQ删除请求对象", description = "删除")
public class CourseUserCollectionDeleteREQ implements Serializable {

    @ApiModelProperty(value = "主键删除", required = true)
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;

}