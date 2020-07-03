package com.roncoo.education.course.service.common.bo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 专区
 */
@Data
@Accessors(chain = true)
public class ZoneAllBO implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 专区编号
	 */
	@ApiModelProperty(value = "专区编号")
	private Long id;

	/**
	 * 位置(1电脑端，2微信端)
	 */
	@ApiModelProperty(value = "位置(1电脑端，2微信端)", required = true)
	private Integer zoneLocation;

}
