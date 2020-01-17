package com.roncoo.education.course.service.common.req;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 课时信息
 *
 */
@Data
@Accessors(chain = true)
public class ChapterPeriodAuditDeleteREQ implements Serializable {

	private static final long serialVersionUID = 1L;
	/**
	 * 主键ID
	 */
	@ApiModelProperty(value = "主键ID", required = true)
	@JsonSerialize(using = ToStringSerializer.class)
	private Long id;
}
