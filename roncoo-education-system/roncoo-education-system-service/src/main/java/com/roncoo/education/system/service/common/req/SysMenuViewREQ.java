package com.roncoo.education.system.service.common.req;

import java.io.Serializable;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 菜单信息-删除
 */
@Data
@Accessors(chain = true)
public class SysMenuViewREQ implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 主键ID
	 */
	@ApiModelProperty(value = "主键ID", required = true)
	private Long id;

}
