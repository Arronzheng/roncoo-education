package com.roncoo.education.course.service.common.dto.auth;

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
public class AuthCourseUserStudyPageDTO implements Serializable {
    /**
     * 课程编号
     */
    @ApiModelProperty(value = "课程编号")
    @JsonSerialize(using = ToStringSerializer.class)
    private Long courseId;
    /**
     * 课程名称
     */
    @ApiModelProperty(value = "课程名称")
    private String courseName;
    /**
     * 课程封面
     */
    @ApiModelProperty(value = "课程封面")
    private String courseImgUrl;
    /**
     * 用户编号
     */
    @ApiModelProperty(value = "用户编号")
    @JsonSerialize(using = ToStringSerializer.class)
    private Long userNo;
    /**
     * 课时编号
     */
    @ApiModelProperty(value = "课时编号")
    @JsonSerialize(using = ToStringSerializer.class)
    private Long lastPeriodId;
    /**
     * 上次学习课时名称
     */
    @ApiModelProperty(value = "上次学习课时名称")
    private String lsatPeriodName;
    /**
     * 上次学习课时时间
     */
    @ApiModelProperty(value = "上次学习课时时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date lastStudyTime;

}
