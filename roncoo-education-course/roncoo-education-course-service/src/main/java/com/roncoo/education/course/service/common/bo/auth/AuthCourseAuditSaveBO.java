package com.roncoo.education.course.service.common.bo.auth;

import java.io.Serializable;
import java.math.BigDecimal;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author WY
 */
@Data
@Accessors(chain = true)
public class AuthCourseAuditSaveBO implements Serializable{

	private static final long serialVersionUID = 1L;

	/**
     * 一级分类ID
     */
	@ApiModelProperty(value = "一级分类ID", required = false)
    private Long categoryId1;
    /**
     * 二级分类ID
     */
	@ApiModelProperty(value = "二级分类ID", required = false)
    private Long categoryId2;
    /**
     * 三级分类ID
     */
	@ApiModelProperty(value = "三级分类ID", required = false)
    private Long categoryId3;
    /**
     * 课程名称
     */
	@ApiModelProperty(value = "课程名称", required = true)
    private String courseName;
    /**
     * 课程封面
     */
	@ApiModelProperty(value = "课程封面", required = true)
    private String courseLogo;
    /**
     * 课程简介
     */
	@ApiModelProperty(value = "课程简介", required = true)
    private String introduce;
	/**
     * 原价
     */
	@ApiModelProperty(value = "原价", required = true)
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
     * 用户编号
     */
	@ApiModelProperty(value = "用户编号", required = true)
    private Long userNo;
	/**
	 * 是否免费：1免费，0收费
	 */
	@ApiModelProperty(value = "是否免费：1免费，0收费", required = true)
	private Integer isFree;
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
