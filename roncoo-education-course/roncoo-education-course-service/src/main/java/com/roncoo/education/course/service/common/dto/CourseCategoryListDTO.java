package com.roncoo.education.course.service.common.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 课程分类
 *
 * @author wujing
 */
@Data
@Accessors(chain = true)
public class CourseCategoryListDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	@ApiModelProperty(value = "分类列表", required = true)
	private List<CourseCategoryDTO> courseCategoryList = new ArrayList<>();
}
