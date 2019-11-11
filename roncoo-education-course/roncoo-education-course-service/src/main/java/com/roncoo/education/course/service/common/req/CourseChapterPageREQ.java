package com.roncoo.education.course.service.common.req;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 章节信息-分页列出
 *
 */
@Data
@Accessors(chain = true)
public class CourseChapterPageREQ implements Serializable {

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
	 * 课程名称
	 */
	@ApiModelProperty(value = "章节名称", required = false)
	private String chapterName;
	/**
	 * 是否免费：1免费，0收费
	 */
	@ApiModelProperty(value = "是否免费：1免费，0收费", required = false)
	private Integer isFree;
	/**
	 * 课程ID
	 */
	@ApiModelProperty(value = "课程ID", required = true)
	@JsonSerialize(using = ToStringSerializer.class)
	private Long courseId;
}
