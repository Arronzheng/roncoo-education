package com.roncoo.education.course.service.common.req;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class CourseChapterUpdateREQ {

    private static final long serialVersionUID = 1L;
    /**
     * 主键
     */
    @ApiModelProperty(value = "主键", required = true)
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;
    /**
     * 状态(1:正常;0:禁用)
     */
    @ApiModelProperty(value = "状态(1:正常;0:禁用)", required = false)
    private Integer statusId;
    /**
     * 排序
     */
    @ApiModelProperty(value = "排序", required = false)
    private Integer sort;
    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间", required = false)
    private Date gmtCreate;
    /**
     * 修改时间
     */
    @ApiModelProperty(value = "修改时间", required = false)
    private Date gmtModified;
    /**
     * 课程ID
     */
    @ApiModelProperty(value = "课程ID", required = false)
    @JsonSerialize(using = ToStringSerializer.class)
    private Long courseId;
    /**
     * 章节名称
     */
    @ApiModelProperty(value = "章节名称", required = false)
    private String chapterName;
    /**
     * 章节描述
     */
    @ApiModelProperty(value = "章节描述", required = false)
    private String chapterDesc;
    /**
     * 是否免费
     */
    @ApiModelProperty(value = "是否免费", required = false)
    private Integer isFree;
    /**
     * 原价
     */
    @ApiModelProperty(value = "原价", required = false)
    private BigDecimal chapterOriginal;
    /**
     * 优惠价
     */
    @ApiModelProperty(value = "优惠价", required = false)
    private BigDecimal chapterDiscount;
}
