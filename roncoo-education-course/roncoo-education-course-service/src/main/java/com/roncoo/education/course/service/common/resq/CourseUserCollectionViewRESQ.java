package com.roncoo.education.course.service.common.resq;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * CourseUserCollectionViewRESQ对象
 *
 * @author ${author}
 * @since 2020-01-04
 */
@Data
@Accessors(chain = true)
@ApiModel(value = "CourseUserCollectionViewREQ分页请求对象", description = "")
public class CourseUserCollectionViewRESQ implements Serializable {

        private static final long serialVersionUID = 1L;

        @ApiModelProperty(value = "用户编号")
        private Long userNo;
        @ApiModelProperty(value = "课程ID")
        private Long courseId;
        @ApiModelProperty(value = "课程名称")
        private String courseName;
        @ApiModelProperty(value = "课程封面")
        private String courseLogo;
}