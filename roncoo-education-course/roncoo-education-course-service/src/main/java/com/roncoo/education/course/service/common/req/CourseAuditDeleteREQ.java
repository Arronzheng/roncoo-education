package com.roncoo.education.course.service.common.req;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 *
 */
@Data
@Accessors(chain = true)
public class CourseAuditDeleteREQ implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 主键ID
	 */
	@ApiModelProperty(value = "主键ID", required = true)
	private Long id;

}
