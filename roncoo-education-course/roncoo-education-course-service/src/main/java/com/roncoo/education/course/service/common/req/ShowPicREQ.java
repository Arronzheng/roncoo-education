package com.roncoo.education.course.service.common.req;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 广告信息-删除
 */
@Data
@Accessors(chain = true)
public class ShowPicREQ implements Serializable {

	private static final long serialVersionUID = 1L;
	/**
	 * imgUrl
	 */
	@ApiModelProperty(value = "图片路径", required = true)
	private String picAddress;

}
