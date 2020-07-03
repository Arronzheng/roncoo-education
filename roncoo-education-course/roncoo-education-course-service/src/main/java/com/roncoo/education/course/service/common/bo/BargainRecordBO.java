package com.roncoo.education.course.service.common.bo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * BargainRecordBO对象
 *
 * @author husend
 * @since 2020-04-14
 */
@Data
@Accessors(chain = true)
@ApiModel(value = "BargainRecordBO砍价记录请求对象", description = "砍价记录请求参数实体类")
public class BargainRecordBO implements Serializable {

    @ApiModelProperty(value = "用户编号", required = true)
    @JsonSerialize(using = ToStringSerializer.class)
    private Long userNo;

}
