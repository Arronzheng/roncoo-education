package com.roncoo.education.course.service.common.req;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 * 课程信息-审核-保存
 *
 */
@Data
@Accessors(chain = true)
public class CourseAuditSaveREQ implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 排序
	 */
	@ApiModelProperty(value = "排序", required = false)
	private Integer sort;
	/**
	 * 课程名称
	 */
	@ApiModelProperty(value = "课程名称", required = true)
	private String courseName;
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
	 * 原价
	 */
	@ApiModelProperty(value = "原价", required = false)
	private BigDecimal courseOriginal;
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
	 * 课程分类
	 */
	@ApiModelProperty(value = "课程分类", required = true)
	private List<String> categorys;
	/**
	 * 课程封面
	 */
	@ApiModelProperty(value = "课程封面", required = true)
	private String courseLogo;
	/**
	 * 课程简介
	 */
	@ApiModelProperty(value = "课程简介", required = false)
	private String courseDesc;
	/**
	 * 讲师编号
	 */
	@ApiModelProperty(value = "讲师编号", required = true)
	@JsonSerialize(using = ToStringSerializer.class)
	private Long lecturerUserNo;

}
