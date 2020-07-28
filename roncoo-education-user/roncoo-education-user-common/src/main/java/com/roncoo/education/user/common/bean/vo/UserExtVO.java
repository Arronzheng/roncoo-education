package com.roncoo.education.user.common.bean.vo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 用户教育信息
 */
@Data
@Accessors(chain = true)
public class UserExtVO implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 主键
	 */
	private Long id;
	/**
	 * 创建时间
	 */
	private Date gmtCreate;
	/**
	 * 修改时间
	 */
	private Date gmtModified;
	/**
	 * 状态(1:正常，0:禁用)
	 */
	private Integer statusId;
	/**
	 * 用户编号
	 */
	private Long userNo;
	/**
	 * 用户类型(1用户，2讲师)
	 */
	private Integer userType;
	/**
	 * 用户手机
	 */
	private String mobile;
	/**
	 * 性别(1男，2女，3保密)
	 */
	private Integer sex;
	/**
	 * 年龄
	 */
	private Integer age;
	/**
	 * 昵称
	 */
	private String nickname;
	/**
	 * 头像地址
	 */
	private String headImgUrl;
	/**
	 * 备注
	 */
	private String remark;
	/**
	 * 邀请码
	 */
	private String inviteCode;
	/**
	 * 会员等级（1：一级，2：二级）
	 */
	private Integer vipLevel;
	/**
	 * 佣金
	 */
	private BigDecimal commission;

}
