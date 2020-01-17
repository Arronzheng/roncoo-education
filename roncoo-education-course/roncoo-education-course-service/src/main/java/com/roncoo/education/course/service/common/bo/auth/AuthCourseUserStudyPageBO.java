package com.roncoo.education.course.service.common.bo.auth;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Data
@Accessors(chain = true)
public class AuthCourseUserStudyPageBO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 用户编号
     */
    @ApiModelProperty(value = "用户编号", required = true)
    private Long userNo;
    /**
     * 当前页
     */
    @ApiModelProperty(value = "当前页", required = true)
    private Integer pageCurrent = 1;
    /**
     * 每页记录数
     */
    @ApiModelProperty(value = "每页记录数", required = true)
    private Integer pageSize = 20;
}
