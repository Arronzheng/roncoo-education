package com.roncoo.education.course.service.common.bo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * AssembleViewREQ对象
 *
 * @author husend
 * @since 2020-04-14
 */
@Data
@Accessors(chain = true)
@ApiModel(value = "AssembleRecordBO拼团记录请求对象", description = "拼团记录请求参数实体类")
public class AssembleRecordBO implements Serializable {

    @ApiModelProperty(value = "用户编号", required = true)
    @JsonSerialize(using = ToStringSerializer.class)
    private Long userNo;

}
