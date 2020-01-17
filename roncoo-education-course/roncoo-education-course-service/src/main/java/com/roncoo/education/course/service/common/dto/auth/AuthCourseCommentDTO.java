package com.roncoo.education.course.service.common.dto.auth;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.roncoo.education.course.service.dao.impl.mapper.entity.CourseAudit;
import com.roncoo.education.user.common.bean.vo.UserExtVO;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
@Data
@Accessors(chain = true)
public class AuthCourseCommentDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "讨论ID")
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;
    @ApiModelProperty(value = "创建时间/注册时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createdAt;
    @ApiModelProperty(value = "更新时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updatedAt;
    @ApiModelProperty(value = "评星级别")
    private Integer star;
    @ApiModelProperty(value = "是否置顶 1.置顶2.不置顶")
    private Integer topping;
    @ApiModelProperty(value = "学员ID")
    @JsonSerialize(using = ToStringSerializer.class)
    private Long userId;
    @ApiModelProperty(value = "留言内容")
    private String content;
    @ApiModelProperty(value = "点赞数")
    private Integer likeNumber;
    @ApiModelProperty(value = "被回复ID，默认为讨论ID")
    @JsonSerialize(using = ToStringSerializer.class)
    private Long pid;
    @ApiModelProperty(value = "被回复人id")
    @JsonSerialize(using = ToStringSerializer.class)
    private Long parentNo;
    @ApiModelProperty(value = "课程ID")
    @JsonSerialize(using = ToStringSerializer.class)
    private Long courseId;
    @ApiModelProperty(value = "对应的章节ID")
    @JsonSerialize(using = ToStringSerializer.class)
    private Long chapterId;
    @ApiModelProperty(value = "对应的课时ID")
    @JsonSerialize(using = ToStringSerializer.class)
    private Long periodId;
    @ApiModelProperty(value = "评论类型 1评价2讨论")
    private Integer commentType;
    @ApiModelProperty(value = "状态 1.显示2.禁用")
    private Integer status;
    @ApiModelProperty(value = "课程")
    private CourseAudit courseAudit;
    @ApiModelProperty(value = "用户")
    private UserExtVO userExt;
    @ApiModelProperty(value = "被回复用户")
    private UserExtVO parentUserExt;
    /**
     * 评论回复集合
     */
    private List<AuthCourseCommentDTO> children;
    private Integer isPackup;
}
