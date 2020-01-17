package com.roncoo.education.user.service.common.dto.auth;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.roncoo.education.course.common.bean.vo.CourseUserCollectionVO;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

@Data
@Accessors(chain = true)
public class AuthApiUserCollectionsDTO implements Serializable {
    /*
    收藏课程集合
     */
    @ApiModelProperty(value = "收藏课程集合")
    @JsonSerialize(using = ToStringSerializer.class)
    private List<Long>  courseUserCollections;
}
