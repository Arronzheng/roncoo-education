package com.roncoo.education.course.service.common.req;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * CourseCommentDeleteREQ对象
 *
 * @author hw
 * @since 2019-12-05
 */
@Data
@Accessors(chain = true)
@ApiModel(value = "CourseCommentDeleteREQ删除请求对象", description = "删除")
public class CourseCommentDeleteREQ implements Serializable {

    @ApiModelProperty(value = "主键删除", required = true)
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;
    /**
     * 用户编号
     */
    @ApiModelProperty(value = "用户编号")
    private Long userNo;

}