package com.roncoo.education.course.service.common.dto.auth;

import java.io.Serializable;
import java.math.BigDecimal;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 课程信息-审核
 */
@Data
@Accessors(chain = true)
public class AuthCourseAuditViewDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 主键
	 */
	@ApiModelProperty(value = "课程ID")
	@JsonSerialize(using = ToStringSerializer.class)
	private Long id;
	/**
	 * 一级分类ID
	 */
	@ApiModelProperty(value = "一级分类ID")
	@JsonSerialize(using = ToStringSerializer.class)
	private Long categoryId1;
	/**
	 * 二级分类ID
	 */
	@ApiModelProperty(value = "二级分类ID")
	@JsonSerialize(using = ToStringSerializer.class)
	private Long categoryId2;
	/**
	 * 三级分类ID
	 */
	@ApiModelProperty(value = "三级分类ID")
	@JsonSerialize(using = ToStringSerializer.class)
	private Long categoryId3;
	/**
	 * 课程名称
	 */
	@ApiModelProperty(value = "课程名称")
	private String courseName;
	/**
	 * 课程封面
	 */
	@ApiModelProperty(value = "课程封面")
	private String courseLogo;
	/**
	 * 课程介绍
	 */
	@ApiModelProperty(value = "课程介绍")
	private String introduce;
	/**
	 * 原价
	 */
	@ApiModelProperty(value = "原价")
	private BigDecimal courseOriginal;
	/**
	 * 优惠价
	 */
	@ApiModelProperty(value = "优惠价")
	private BigDecimal courseDiscount;
	/**
	 * 会员价
	 */
	@ApiModelProperty(value = "会员价", required = false)
	private BigDecimal courseSvipDiscount;
	/**
	 * 拼团价
	 */
	@ApiModelProperty(value = "拼团价", required = false)
	private BigDecimal courseAssembleDiscount;
	/**
	 * 是否免费(1:免费, 0:收费)
	 */
	@ApiModelProperty(value = "是否免费(1:免费, 0:收费)")
	private Integer isFree;
	/**
	 * 是否vip免费：1免费，0收费
	 */
	@ApiModelProperty(value = "是否vip免费：1免费，0收费")
	private Integer isVipFree;
	/**
	 * 是否只有vip能买：1是，0否
	 */
	@ApiModelProperty(value = "是否只有vip能买：1是，0否")
	private Integer isOnlyVipBuy;
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
