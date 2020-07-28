package com.roncoo.education.course.service.common.req;

import java.io.Serializable;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 课程信息-审核-分页列出
 */
@Data
@Accessors(chain = true)
public class CourseAuditPageREQ implements Serializable {

	private static final long serialVersionUID = 1L;

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
	 * 一级分类ID
	 */
	@ApiModelProperty(value = "一级分类ID", required = false)
	private Long categoryId1;
	/**
	 * 课程名称
	 */
	@ApiModelProperty(value = "课程名称", required = false)
	private String courseName;
	/**
	 * 是否上架(1:上架，0:下架)
	 */
	@ApiModelProperty(value = "是否上架(1:上架，0:下架)", required = false)
	private Integer isPutaway;
	/**
	 * 是否免费：1免费，0收费
	 */
	@ApiModelProperty(value = "是否免费：1免费，0收费", required = false)
	private Integer isFree;
	/**
	 * 是否vip免费：1免费，0收费
	 */
	@ApiModelProperty(value = "是否免费：1免费，0收费")
	private Integer isVipFree;
	/**
	 * 是否只有vip能买：1是，0否
	 */
	@ApiModelProperty(value = "是否只有vip能买：1是，0否")
	private Integer isOnlyVipBuy;
	/**
	 * 审核状态(0:待审核,1:审核通过,2:审核不通过)
	 */
	@ApiModelProperty(value = "审核状态(0:待审核,1:审核通过,2:审核不通过)", required = false)
	private Integer auditStatus;
	/**
	 * 专区编号
	 */
	@ApiModelProperty(value = "专区编号", required = false)
	private Long zoneId;
	/**
	 * 专区编号
	 */
	@ApiModelProperty(value = "专区编号", required = false)
	private Long activityId;
	/**
	 * 是否有教具（1没有，2有）
	 */
	@ApiModelProperty(value = "是否有教具（1没有，2有）")
	private Integer hasTrainaid;
	/**
	 * 课程简介
	 */
	@ApiModelProperty(value = "课程简介")
	private String courseShortIntroduce;
}
