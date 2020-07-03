package com.roncoo.education.user.service.common.req;

import java.io.Serializable;
import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class UserExtPageREQ implements Serializable {

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
	 * 当前分页的开始记录的索引
	 */
	@ApiModelProperty(value = "当前分页的开始记录的索引", required = true)
	private int limitStart = 0;
	/**
	 * 用户编号
	 */
	@ApiModelProperty(value = "用户编号", required = false)
	private Long userNo;
	/**
	 * 手机号码
	 */
	@ApiModelProperty(value = "手机号码", required = false)
	private String mobile;
	/**
	 * 状态(1:正常，0:禁用)
	 */
	@ApiModelProperty(value = "状态(1:正常，0:禁用)", required = false)
	private Integer statusId;
	/**
	 * 昵称
	 */
	@ApiModelProperty(value = "昵称", required = false)
	private String nickname;
	/**
	 * 开始时间
	 */
	@ApiModelProperty(value = "开始时间", required = false)
	@JsonFormat(pattern = "yyyy-MM-dd")
	private String beginGmtCreate;
	/**
	 * 结束时间
	 */
	@ApiModelProperty(value = "结束时间", required = false)
	@JsonFormat(pattern = "yyyy-MM-dd")
	private String endGmtCreate;
	/**
	 * 是否vip
	 */
	@ApiModelProperty(value = "是否vip", required = false)
	private Integer isVip;
	/**
	 * 邀请码
	 */
	@ApiModelProperty(value = "邀请码", required = false)
	private String inviteCode;
	/**
	 * 会员等级（1：一级，2：二级）
	 */
	@ApiModelProperty(value = "会员等级（1：一级，2：二级）", required = false)
	private Integer vipLevel;
	/**
	 * 佣金
	 */
	@ApiModelProperty(value = "佣金", required = false)
	private BigDecimal commission;


}
