package com.roncoo.education.course.service.common.resq;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 课时信息分页列表
 *
 */
@Data
@Accessors(chain = true)
public class ChapterPeriodAuditPageRESQ implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 主键
	 */
	@ApiModelProperty(value = "课时ID")
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
	 * 章节ID
	 */
	@ApiModelProperty(value = "章节ID")
	@JsonSerialize(using = ToStringSerializer.class)
	private Long chapterId;
	/**
	 * 课时名称
	 */
	@ApiModelProperty(value = "课时名称")
	private String periodName;
	/**
	 * 课时描述
	 */
	@ApiModelProperty(value = "课时描述")
	private String periodDesc;
	/**
	 * 是否免费
	 */
	@ApiModelProperty(value = "是否免费：1免费，0收费")
	private Integer isFree;
	/**
	 * 原价
	 */
	@ApiModelProperty(value = "原价")
	private BigDecimal periodOriginal;
	/**
	 * 优惠价
	 */
	@ApiModelProperty(value = "优惠价")
	private BigDecimal periodDiscount;
	/**
	 * 是否存在文档
	 */
	@ApiModelProperty(value = "是否存在文档")
	private Integer isDoc;
	/**
	 * 文档名称
	 */
	@ApiModelProperty(value = "文档名称")
	private String docName;
	/**
	 * 文档url
	 */
	@ApiModelProperty(value = "文档url")
	private String docUrl;
	/**
	 * 是否存在视频
	 */
	@ApiModelProperty(value = "是否存在视频")
	private String isVideo;
	/**
	 * 视频名称
	 */
	@ApiModelProperty(value = "视频名称")
	private String videoName;
	/**
	 * 视频名称
	 */
	@ApiModelProperty(value = "视频名称")
	private Long videoNo;
	/**
	 * 视频url
	 */
	@ApiModelProperty(value = "视频url")
	private String videoUrl;
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
