package com.roncoo.education.course.service.common.resq;

import java.io.Serializable;
import java.math.BigDecimal;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 课程信息-审核-查看
 */
@Data
@Accessors(chain = true)
public class CourseAuditGetRESQ implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 主键
	 */
	@ApiModelProperty(value = "课程ID")
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
	 * 讲师用户编码
	 */
	@ApiModelProperty(value = "讲师用户编码")
	@JsonSerialize(using = ToStringSerializer.class)
	private Long lecturerUserNo;
	/**
	 * 讲师名称
	 */
	@ApiModelProperty(value = "讲师名称")
	private String lecturerName;

	/**
	 * 一级分类名
	 */
	@ApiModelProperty(value = "一级分类名")
	private String categoryName1;
	/**
	 * 二级分类名
	 */
	@ApiModelProperty(value = "二级分类名")
	private String categoryName2;
	/**
	 * 三级分类名
	 */
	@ApiModelProperty(value = "三级分类名")
	private String categoryName3;
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
	 * 课程介绍ID
	 */
	@ApiModelProperty(value = "课程介绍ID")
	@JsonSerialize(using = ToStringSerializer.class)
	private Long introduceId;
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
	 * 是否只有vip能买：1是，0否
	 */
	@ApiModelProperty(value = "是否只有vip能买：1是，0否")
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
	@ApiModelProperty(value = "会员价", required = false)
	private BigDecimal courseSvipDiscount;
	/**
	 * 拼团价
	 */
	@ApiModelProperty(value = "拼团价", required = false)
	private BigDecimal courseAssembleDiscount;
	/**
	 * 是否上架(1:上架，0:下架)
	 */
	@ApiModelProperty(value = "是否上架(1:上架，0:下架)")
	private Integer isPutaway;
	/**
	 * 课程介绍
	 */
	@ApiModelProperty(value = "课程介绍")
	private String introduce;
}
