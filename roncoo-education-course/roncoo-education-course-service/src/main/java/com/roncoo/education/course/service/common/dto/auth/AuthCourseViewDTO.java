package com.roncoo.education.course.service.common.dto.auth;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.roncoo.education.course.service.common.dto.AssemblePageDTO;
import com.roncoo.education.course.service.common.dto.CourseChapterDTO;

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
public class AuthCourseViewDTO implements Serializable {

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
	 * 课程原价
	 */
	@ApiModelProperty(value = "课程原价")
	private BigDecimal courseOriginal;
	/**
	 * 课程优惠价
	 */
	@ApiModelProperty(value = "课程优惠价")
	private BigDecimal courseDiscount;
	/**
	 * 会员价
	 */
	@ApiModelProperty(value = "会员价", required = false)
	private BigDecimal courseSvipDiscount;
	/**
	 * 拼团价(弃用)
	 */
	@ApiModelProperty(value = "拼团价", required = false)
	private BigDecimal courseAssembleDiscount;

	/**
	 * 课程介绍
	 */
	@ApiModelProperty(value = "课程介绍")
	private String introduce;

	/**
	 * 讲师信息
	 */
	@ApiModelProperty(value = "讲师信息")
	private AuthLecturerDTO lecturer;
	/**
	 * 是否购买
	 */
	@ApiModelProperty(value = "是否支付(1:已支付;0:未支付)")
	private int isPay;
	/**
	 * 是否收藏
	 */
	@ApiModelProperty(value = "是否收藏(true:已收藏;false:未收藏)")
	private boolean userCollect;
	/**
	 * 视频播放平台
	 */
	@ApiModelProperty(value = "视频播放平台(1:保利威视;2:腾讯)")
	private int videoType;
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
	 * 砍价课程
	 */
	@ApiModelProperty(value = "拼团课程")
	private AuthBargainCourseViewDTO authBargainCourseViewDTO;
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
	 * 课程学习进度
	 */
	@ApiModelProperty(value = "课程学习进度")
	private AuthCourseUserStudyLogDTO authCourseUserStudyLog;
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
