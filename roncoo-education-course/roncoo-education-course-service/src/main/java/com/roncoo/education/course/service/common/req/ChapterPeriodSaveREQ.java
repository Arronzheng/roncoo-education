package com.roncoo.education.course.service.common.req;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 课时信息
 *
 */
@Data
@Accessors(chain = true)
public class ChapterPeriodSaveREQ implements Serializable {

	private static final long serialVersionUID = 1L;
	/**
	 * 主键ID
	 */
	@ApiModelProperty(value = "主键ID", required = true)
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
	 * 章节ID
	 */
	@ApiModelProperty(value = "章节ID", required = false)
	@JsonSerialize(using = ToStringSerializer.class)
	private Long chapterId;
	/**
	 * 章节名称
	 */
	@ApiModelProperty(value = "课时名称", required = false)
	private String periodName;
	/**
	 * 章节描述
	 */
	@ApiModelProperty(value = "课时描述", required = false)
	private String periodDesc;
	/**
	 * 是否免费
	 */
	@ApiModelProperty(value = "是否免费", required = false)
	private Integer isFree;
	/**
	 * 原价
	 */
	@ApiModelProperty(value = "原价", required = false)
	private BigDecimal periodOriginal;
	/**
	 * 优惠价
	 */
	@ApiModelProperty(value = "优惠价", required = false)
	private BigDecimal periodDiscount;
	/**
	 * 是否存在文档
	 */
	@ApiModelProperty(value = "是否存在文档", required = false)
	private Integer isDoc;
	/**
	 * 文档名称
	 */
	@ApiModelProperty(value = "文档名称", required = false)
	private String docName;
	/**
	 * 文档地址
	 */
	@ApiModelProperty(value = "文档地址", required = false)
	private String docUrl;
	/**
	 * 是否存在视频
	 */
	@ApiModelProperty(value = "是否存在视频", required = false)
	private Integer isVideo;
	/**
	 * 视频编号
	 */
	@ApiModelProperty(value = "视频编号", required = false)
	private String videoNo;
}
