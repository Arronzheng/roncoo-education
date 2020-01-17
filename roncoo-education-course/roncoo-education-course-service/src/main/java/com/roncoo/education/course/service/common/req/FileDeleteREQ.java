package com.roncoo.education.course.service.common.req;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 文件删除
 *
 * @author wujing
 */
@Data
@Accessors(chain = true)
public class FileDeleteREQ implements Serializable {

	private static final long serialVersionUID = 1L;
	/**
	 * 视频文件url
	 */
	@ApiModelProperty(value = "视频文件url", required = false)
	private String videoFileUrl;
	/**
	 * 文件url
	 */
	@ApiModelProperty(value = "文件url", required = false)
	private String docFileUrl;

}
