package com.roncoo.education.course.service.common.req;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * ActivityCoursePageREQ对象
 *
 * @author husend
 * @since 2020-04-10
 */
@Data
@Accessors(chain = true)
@ApiModel(value = "ActivityCoursePageREQ分页请求对象", description = "活动专区课程关联表")
public class ActivityCoursePageREQ implements Serializable {
    /**
     * 活动类别
     */
    @ApiModelProperty(value = "活动类别", required = true)
    private int activityCategory;
    /**
     * 专区ID
     */
    @ApiModelProperty(value = "专区ID", required = false)
    @JsonSerialize(using = ToStringSerializer.class)
    private Long activityId;
    /**
     * 课程名称
     */
    @ApiModelProperty(value = "课程名称", required = false)
    private String courseName;
    /**
	 * 当前页
	 */
    @ApiModelProperty(value = "当前页", required = true)
    private int pageCurrent = 1;
    /**
     * 每页记录数
     */
    @ApiModelProperty(value = "每页记录数", required = true)
    private int pageSize = 20;
    /**
     * 状态(1:正常，0:禁用)
     */
    @ApiModelProperty(value = "状态(1:正常，0:禁用)", required = false)
    private Integer statusId;
    /**
	 * 位置(0电脑端，1微信端)
	 */
    @ApiModelProperty(value = "位置(0电脑端，1微信端)", required = false)
    private Integer platShow;

}
