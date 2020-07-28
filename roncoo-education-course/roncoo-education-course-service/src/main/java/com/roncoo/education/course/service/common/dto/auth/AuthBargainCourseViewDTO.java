package com.roncoo.education.course.service.common.dto.auth;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * BargainCourseViewRESQ对象
 *
 * @author husend
 * @since 2020-04-14
 */
@Data
@Accessors(chain = true)
@ApiModel(value = "BargainCourseViewREQ分页请求对象", description = "砍价表")
public class AuthBargainCourseViewDTO implements Serializable {

        private static final long serialVersionUID = 1L;

        @ApiModelProperty(value = "id")
        @JsonSerialize(using = ToStringSerializer.class)
        private Long id;
        @ApiModelProperty(value = "关联产品ID")
        @JsonSerialize(using = ToStringSerializer.class)
        private Long productId;
        @ApiModelProperty(value = "砍价活动名称")
        private String title;
        @ApiModelProperty(value = "砍价活动图片")
        private String image;
        @ApiModelProperty(value = "单位名称")
        private String unitName;
        @ApiModelProperty(value = "库存")
        private Integer stock;
        @ApiModelProperty(value = "销量")
        private Integer sales;
        @ApiModelProperty(value = "砍价产品轮播图")
        private String images;
        @ApiModelProperty(value = "砍价开启时间")
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
        private Date startTime;
        @ApiModelProperty(value = "砍价结束时间")
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
        private Date stopTime;
        @ApiModelProperty(value = "砍价产品名称")
        private String storeName;
        @ApiModelProperty(value = "砍价金额")
        private BigDecimal price;
        @ApiModelProperty(value = "砍价商品最低价")
        private BigDecimal minPrice;
        @ApiModelProperty(value = "每次购买的砍价产品数量")
        private Integer num;
        @ApiModelProperty(value = "用户每次砍价的最大金额")
        private BigDecimal bargainMaxPrice;
        @ApiModelProperty(value = "用户每次砍价的最小金额")
        private BigDecimal bargainMinPrice;
        @ApiModelProperty(value = "用户每次砍价的次数")
        private Integer bargainNum;
        @ApiModelProperty(value = "砍价状态 0(到砍价时间不自动开启)  1(到砍价时间自动开启时间)")
        private Integer status;
        @ApiModelProperty(value = "砍价详情")
        private String description;
        @ApiModelProperty(value = "反多少积分")
        private BigDecimal giveIntegral;
        @ApiModelProperty(value = "砍价活动简介")
        private String info;
        @ApiModelProperty(value = "成本价")
        private BigDecimal cost;
        @ApiModelProperty(value = "排序")
        private Integer sort;
        @ApiModelProperty(value = "是否推荐0不推荐1推荐")
        private Integer isHot;
        @ApiModelProperty(value = "添加时间")
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
        private Date addTime;
        @ApiModelProperty(value = "是否包邮 0不包邮 1包邮")
        private Integer isPostage;
        @ApiModelProperty(value = "邮费")
        private BigDecimal postage;
        @ApiModelProperty(value = "砍价规则")
        private String rule;
        @ApiModelProperty(value = "砍价产品浏览量")
        private Integer look;
        @ApiModelProperty(value = "砍价产品分享量")
        private Integer share;
        @ApiModelProperty(value = "第一刀砍价最大比率")
        private String firstBargainMaxRate;
        @ApiModelProperty(value = "第一刀砍价最小比率")
        private String firstBargainMinRate;
        @ApiModelProperty(value = "其他砍价最大比率")
        private String bargainMaxRate;
        @ApiModelProperty(value = "其他砍价最小比率")
        private String bargainMinRate;
}
