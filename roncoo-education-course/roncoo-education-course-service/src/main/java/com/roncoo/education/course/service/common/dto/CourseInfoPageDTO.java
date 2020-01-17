package com.roncoo.education.course.service.common.dto;

import java.io.Serializable;
import java.math.BigDecimal;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 课程信息
 *
 * @author wujing
 */
@Data
@Accessors(chain = true)
public class CourseInfoPageDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 主键
	 */
	@ApiModelProperty(value = "课程ID")
	@JsonSerialize(using = ToStringSerializer.class)
	private Long id;
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
	 * 是否免费：1免费，0收费
	 */
	@ApiModelProperty(value = "是否免费：1免费，0收费")
	private Integer isFree;
	/**
	 * 是否vip免费：1免费，0收费
	 */
	@ApiModelProperty(value = "是否免费：1免费，0收费")
	private Integer isVipFree;
	/**
	 * 是否限vip购买：1是，0否
	 */
	@ApiModelProperty(value = "是否限vip购买：1是，0否")
	private Integer isOnlyVipBuy;
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
	@ApiModelProperty(value = "会员价")
	private BigDecimal courseSvipDiscount;
	/**
	 * 拼团价
	 */
	@ApiModelProperty(value = "拼团价")
	private BigDecimal courseAssembleDiscount;
}
