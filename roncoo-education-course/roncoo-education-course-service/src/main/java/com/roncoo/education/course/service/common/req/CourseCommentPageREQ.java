package com.roncoo.education.course.service.common.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * CourseCommentPageREQ对象
 *
 * @author hw
 * @since 2019-12-05
 */
@Data
@Accessors(chain = true)
@ApiModel(value = "CourseCommentPageREQ分页请求对象", description = "")
public class CourseCommentPageREQ implements Serializable {
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
     * 状态(1:正常，0:禁用)
     */
    @ApiModelProperty(value = "状态(1:正常，0:禁用)", required = false)
    private Integer status;
    /**
	 * 位置(0电脑端，1微信端)
	 */
    @ApiModelProperty(value = "位置(0电脑端，1微信端)", required = false)
    private Integer platShow;
    /**
     * 评论内容
     */
    @ApiModelProperty(value = "评论内容", required = false)
    private String content;
    /**
     * 评论开始时间
     */
    @ApiModelProperty(value = "评论开始时间", required = false)
    private String beginCreateTime;
    /**
     * 评论结束时间
     */
    @ApiModelProperty(value = "评论结束时间", required = false)
    private String endCreateTime;

}