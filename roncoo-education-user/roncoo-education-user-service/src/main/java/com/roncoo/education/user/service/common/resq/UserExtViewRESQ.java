package com.roncoo.education.user.service.common.resq;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class UserExtViewRESQ implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 主键
	 */
	@ApiModelProperty(value = "主键")
	@JsonSerialize(using = ToStringSerializer.class)
	private Long id;
	/**
	 * 创建时间
	 */
	@ApiModelProperty(value = "创建时间")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date gmtCreate;
	/**
	 * 修改时间
	 */
	@ApiModelProperty(value = "修改时间")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date gmtModified;
	/**
	 * 状态(1:正常，0:禁用)
	 */
	@ApiModelProperty(value = "状态(1:正常，0:禁用)")
	private Integer statusId;
	/**
	 * 用户编号
	 */
	@ApiModelProperty(value = "用户编号")
	@JsonSerialize(using = ToStringSerializer.class)
	private Long userNo;
	/**
	 * 用户类型
	 */
	@ApiModelProperty(value = "用户类型")
	private Integer userType;
	/**
	 * 手机号码
	 */
	@ApiModelProperty(value = "手机号码")
	private String mobile;
	/**
	 * 性别
	 */
	@ApiModelProperty(value = "性别")
	private Integer sex;
	/**
	 * 年龄
	 */
	@ApiModelProperty(value = "年龄")
	private Integer age;
	/**
	 * 昵称
	 */
	@ApiModelProperty(value = "昵称")
	private String nickname;
	/**
	 * 头像
	 */
	@ApiModelProperty(value = "头像")
	private String headImgUrl;
	/**
	 * 备注
	 */
	@ApiModelProperty(value = "备注")
	private String remark;
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
