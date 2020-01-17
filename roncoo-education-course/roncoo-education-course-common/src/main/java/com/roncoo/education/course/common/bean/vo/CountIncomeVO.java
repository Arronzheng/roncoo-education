package com.roncoo.education.course.common.bean.vo;

import java.io.Serializable;
import java.math.BigDecimal;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 收入统计
 *
 * @author forest
 */
@Data
@Accessors(chain = true)
public class CountIncomeVO implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 总收入
	 */
	private BigDecimal totalProfit = BigDecimal.valueOf(0);

	/**
	 * 平台收入
	 */
	private BigDecimal platformProfit = BigDecimal.valueOf(0);

	/**
	 * 讲师收入
	 */
	private BigDecimal lecturerProfit = BigDecimal.valueOf(0);

}
