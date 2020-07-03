package com.roncoo.education.course.service.common.bo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @description:
 * @author: husend
 * @time: 2020/5/14
 */
@Data
@Accessors(chain = true)
public class BargainUserHelpSaveBO implements Serializable {
    @ApiModelProperty(value = "用户id", required = true)
    @JsonSerialize(using = ToStringSerializer.class)
    private Long uid;

    @ApiModelProperty(value = "用户参与砍价表id", required = true)
    @JsonSerialize(using = ToStringSerializer.class)
    private Long bargainUserId;
}
