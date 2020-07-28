package com.roncoo.education.course.service.common.req;

import java.io.Serializable;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 课程分类-更新
 */
@Data
@Accessors(chain = true)
public class CourseCategoryUpdateREQ implements Serializable {

	private static final long serialVersionUID = 1L;
	/**
	 * 主键
	 */
	@ApiModelProperty(value = "主键", required = true)
	private Long id;
	/**
	 * 排序
	 */
	@ApiModelProperty(value = "排序", required = false)
	private Integer sort;
	/**
	 * 状态(1:正常，0:禁用)
	 */
	@ApiModelProperty(value = "状态(1:正常，0:禁用)", required = false)
	private Integer statusId;
	/**
	 * 分类名称
	 */
	@ApiModelProperty(value = "分类名称")
	private String categoryName;
	/**
	 * 分类图标
	 */
	@ApiModelProperty(value = "分类图标", required = true)
	private String categoryIcon;
	/**
	 * 备注
	 */
	@ApiModelProperty(value = "备注", required = false)
	private String remark;

}
