package com.roncoo.education.course.service.common.req;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * CourseCommentUpdateREQ 请求对象
 *
 * @author hw
 * @since 2019-12-05
 */
@Data
@Accessors(chain = true)
@ApiModel(value = "CourseComment更新请求对象", description = "")
public class CourseCommentUpdateREQ implements Serializable {

        private static final long serialVersionUID = 1L;

        @ApiModelProperty(value = "讨论ID", required = true)
        @JsonSerialize(using = ToStringSerializer.class)
        private Long id;
        @ApiModelProperty(value = "创建时间/注册时间")
        private Date createdAt;
        @ApiModelProperty(value = "更新时间")
        private Date updatedAt;
        @ApiModelProperty(value = "评星级别")
        private Integer star;
        @ApiModelProperty(value = "是否置顶 1.置顶2.不置顶")
        private Integer topping;
        @ApiModelProperty(value = "学员ID")
        private Long userId;
        @ApiModelProperty(value = "留言内容")
        private String content;
        @ApiModelProperty(value = "点赞数")
        private Integer likeNumber;
        @ApiModelProperty(value = "被回复ID，默认为讨论ID")
        private Long pid;
        @ApiModelProperty(value = "被回复人id")
        private Long parentNo;
        @ApiModelProperty(value = "课程ID")
        private Long courseId;
        @ApiModelProperty(value = "对应的章节ID")
        private Long chapterId;
        @ApiModelProperty(value = "对应的课时ID")
        private Long periodId;
        @ApiModelProperty(value = "评论类型 1评价2讨论")
        private Integer commentType;
        @ApiModelProperty(value = "状态 1.显示2.禁用")
        private Integer status;
}