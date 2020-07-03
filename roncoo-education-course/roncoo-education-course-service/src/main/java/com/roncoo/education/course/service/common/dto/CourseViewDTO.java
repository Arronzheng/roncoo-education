package com.roncoo.education.course.service.common.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;

import com.roncoo.education.course.service.common.dto.auth.AuthAssembleCourseViewDTO;
import com.roncoo.education.course.service.common.dto.auth.AuthCourseCommentDTO;
import com.roncoo.education.course.service.dao.impl.mapper.entity.AssembleCourse;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 课程信息
 *
 * @author wujing
 */
@Data
@Accessors(chain = true)
public class CourseViewDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 主键
	 */
	@ApiModelProperty(value = "课程ID")
	@JsonSerialize(using = ToStringSerializer.class)
	private Long id;
	/**
	 * 讲师用户编码
	 */
	@ApiModelProperty(value = "讲师用户编码")
	@JsonSerialize(using = ToStringSerializer.class)
	private Long lecturerUserNo;
	/**
	 * 课程名称
	 */
	@ApiModelProperty(value = "课程名称")
	private String courseName;
	/**
	 * 课程封面
	 */
	@ApiModelProperty(value = "课程封面")
	private String courseLogo;
	/**
	 * 课程介绍ID
	 */
	@ApiModelProperty(value = "课程介绍ID")
	@JsonSerialize(using = ToStringSerializer.class)
	private Long introduceId;
	/**
	 * 是否免费：1免费，0收费
	 */
	@ApiModelProperty(value = "是否免费：1免费，0收费")
	private Integer isFree;
	/**
	 * 是否vip免费：1免费，0收费
	 */
	@ApiModelProperty(value = "是否vip免费：1免费，0收费")
	private Integer isVipFree;
	/**
	 * 是否只有vip能买：1是，0否
	 */
	@ApiModelProperty(value = "是否只有vip能买：1是，0否")
	private Integer isOnlyVipBuy;
	/**
	 * 原价
	 */
	@ApiModelProperty(value = "原价")
	private BigDecimal courseOriginal;
	/**
	 * 优惠价
	 */
	@ApiModelProperty(value = "优惠价")
	private BigDecimal courseDiscount;
	/**
	 * 会员价
	 */
	@ApiModelProperty(value = "会员价", required = false)
	private BigDecimal courseSvipDiscount;
	/**
	 * 拼团价(弃用，使用拼团课程里面的价格)
	 */
	@ApiModelProperty(value = "拼团价", required = false)
	private BigDecimal courseAssembleDiscount;
	/**
	 * 购买人数
	 */
	@ApiModelProperty(value = "购买人数")
	private Integer countBuy;
	/**
	 * 学习人数
	 */
	@ApiModelProperty(value = "学习人数")
	private Integer countStudy;
	/**
	 * 总课时数
	 */
	@ApiModelProperty(value = "总课时数")
	private Integer periodTotal;

	/**
	 * 课程介绍
	 */
	@ApiModelProperty(value = "课程介绍")
	private String introduce;

	/**
	 * 讲师信息
	 */
	@ApiModelProperty(value = "讲师信息")
	private LecturerDTO lecturer;
	/**
	 * 是否拼团课程
	 */
	@ApiModelProperty(value = "是否拼团课程")
	private boolean isAssemble;
	/**
	 * 拼团课程
	 */
	@ApiModelProperty(value = "拼团课程")
	private AuthAssembleCourseViewDTO authAssembleCourseViewDTO;
	/**
	 * 是否砍价课程
	 */
	@ApiModelProperty(value = "是否砍价课程")
	private boolean isBargain;
	/**
	 * 章节信息
	 */
	@ApiModelProperty(value = "章节信息")
	private List<CourseChapterDTO> chapterList;
	/**
	 * 课程评论
	 */
	@ApiModelProperty(value = "课程评论")
	private List<AuthCourseCommentDTO> courseCommentList;

	/**
	 * 拼团列表
	 */
	@ApiModelProperty(value = "拼团列表")
	private List<AssemblePageDTO> assemblePageList;

}
