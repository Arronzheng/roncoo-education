package com.roncoo.education.course.service.common.resq;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 章节信息分页列表
 *
 */
@Data
@Accessors(chain = true)
public class CourseChapterAuditPageRESQ implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 主键
	 */
	@ApiModelProperty(value = "章节ID")
	@JsonSerialize(using = ToStringSerializer.class)
	private Long id;
	/**
	 * 状态(1:正常，0:禁用)
	 */
	@ApiModelProperty(value = "状态(1:正常，0:禁用)")
	private Integer statusId;
	/**
	 * 排序
	 */
	@ApiModelProperty(value = "排序")
	private Integer sort;
	/**
	 * 课程ID
	 */
	@ApiModelProperty(value = "课程ID")
	@JsonSerialize(using = ToStringSerializer.class)
	private Long courseId;
	/**
	 * 章节名称
	 */
	@ApiModelProperty(value = "章节名称")
	private String chapterName;
	/**
	 * 章节描述
	 */
	@ApiModelProperty(value = "章节描述")
	private String chapterDesc;
	/**
	 * 是否免费
	 */
	@ApiModelProperty(value = "是否免费：1免费，0收费")
	private Integer isFree;
	/**
	 * 原价
	 */
	@ApiModelProperty(value = "原价")
	private BigDecimal chapterOriginal;
	/**
	 * 优惠价
	 */
	@ApiModelProperty(value = "优惠价")
	private BigDecimal chapterDiscount;
	/**
	 * 审核状态
	 */
	@ApiModelProperty(value = "审核状态")
	private Integer auditStatus;
	/**
	 * 审核意见
	 */
	@ApiModelProperty(value = "审核意见")
	private String auditOpinion;
}
