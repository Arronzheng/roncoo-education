package com.roncoo.education.course.service.common.bo.auth;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

@Data
@Accessors(chain = true)
public class AuthCourseUserCollectionDelBO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "用户编码")
    @JsonSerialize(using = ToStringSerializer.class)
    private Long userNo;
    @ApiModelProperty(value = "课程ID")
    @JsonSerialize(using = ToStringSerializer.class)
    private Long courseId;

}
