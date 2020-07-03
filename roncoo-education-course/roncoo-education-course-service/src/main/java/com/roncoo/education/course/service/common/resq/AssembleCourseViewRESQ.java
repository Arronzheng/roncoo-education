package com.roncoo.education.course.service.common.resq;

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
 * AssembleCourseViewRESQ对象
 *
 * @author husend
 * @since 2020-04-14
 */
@Data
@Accessors(chain = true)
@ApiModel(value = "AssembleCourseViewREQ分页请求对象", description = "拼团产品表")
public class AssembleCourseViewRESQ implements Serializable {

        private static final long serialVersionUID = 1L;

        @ApiModelProperty(value = "id")
        @JsonSerialize(using = ToStringSerializer.class)
        private Long id;
        @ApiModelProperty(value = "商品id")
        @JsonSerialize(using = ToStringSerializer.class)
        private Long productId;
        @ApiModelProperty(value = "推荐图")
        private String image;
        @ApiModelProperty(value = "轮播图")
        private String images;
        @ApiModelProperty(value = "活动标题")
        private String title;
        @ApiModelProperty(value = "活动属性")
        private String attr;
        @ApiModelProperty(value = "参团人数")
        private Integer people;
        @ApiModelProperty(value = "简介")
        private String info;
        @ApiModelProperty(value = "价格")
        private BigDecimal price;
        @ApiModelProperty(value = "排序")
        private Integer sort;
        @ApiModelProperty(value = "销量")
        private Integer sales;
        @ApiModelProperty(value = "库存")
        private Integer stock;
        @ApiModelProperty(value = "添加时间")
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
        private Date addTime;
        @ApiModelProperty(value = "推荐")
        private Integer isHost;
        @ApiModelProperty(value = "产品状态")
        private Integer isShow;
        @ApiModelProperty(value = "商户是否可用1可用0不可用")
        private Integer merUse;
        @ApiModelProperty(value = "是否包邮1是0否")
        private Integer isPostage;
        @ApiModelProperty(value = "邮费")
        private BigDecimal postage;
        @ApiModelProperty(value = "拼团内容")
        private String description;
        @ApiModelProperty(value = "拼团开始时间")
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
        private Date startTime;
        @ApiModelProperty(value = "拼团结束时间")
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
        private Date stopTime;
        @ApiModelProperty(value = "拼团订单有效时间")
        private Integer effectiveTime;
        @ApiModelProperty(value = "拼图产品成本")
        private Integer cost;
        @ApiModelProperty(value = "浏览量")
        private Integer browse;
        @ApiModelProperty(value = "单位名")
        private String unitName;
}
