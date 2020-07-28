package com.roncoo.education.user.common.bean.qo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 讲师提现日志表
 *
 *
 */
@Data
@Accessors(chain = true)
public class LecturerProfitQO implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 当前页
	 */
	private int pageCurrent;
	/**
	 * 每页记录数
	 */
	private int pageSize;
	/**
	 * 主键
	 */
	private Long id;
	/**
	 * 创建时间
	 */
	private Date gmtCreate;
	/**
	 * 讲师用户编号
	 */
	private Long lecturerUserNo;
	/**
	 * 银行卡号
	 */
	private String bankCardNo;
	/**
	 * 银行名称
	 */
	private String bankName;
	/**
	 * 银行支行名称
	 */
	private String bankBranchName;
	/**
	 * 银行开户名
	 */
	private String bankUserName;
	/**
	 * 银行身份证号
	 */
	private String bankIdCardNo;
	/**
	 * 讲师收入
	 */
	private BigDecimal lecturerProfit;
	/**
	 * 平台收入
	 */
	private BigDecimal platformProfit;
	/**
	 * 分润状态（1确认中，2成功，3失败）
	 */
	private Integer profitStatus;
	/**
	 * 备注
	 */
	private String remark;
	/**
	 * 讲师名称
	 */
	private String lecturerName;

	private String beginDate;
	private String endDate;
}
