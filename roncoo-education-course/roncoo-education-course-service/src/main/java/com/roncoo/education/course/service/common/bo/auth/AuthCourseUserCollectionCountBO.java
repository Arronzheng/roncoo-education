package com.roncoo.education.course.service.common.bo.auth;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Data
@Accessors(chain = true)
public class AuthCourseUserCollectionCountBO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "用户编码")
    @JsonSerialize(using = ToStringSerializer.class)
    private Long userNo;

}
