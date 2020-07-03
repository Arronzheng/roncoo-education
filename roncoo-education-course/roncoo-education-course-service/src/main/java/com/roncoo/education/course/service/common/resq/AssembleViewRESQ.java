package com.roncoo.education.course.service.common.resq;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.roncoo.education.user.common.bean.vo.UserExtVO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * AssembleViewRESQ对象
 *
 * @author husend
 * @since 2020-04-14
 */
@Data
@Accessors(chain = true)
@ApiModel(value = "AssembleViewREQ分页请求对象", description = "拼团表")
public class AssembleViewRESQ implements Serializable {

        private static final long serialVersionUID = 1L;

        @ApiModelProperty(value = "id")
        @JsonSerialize(using = ToStringSerializer.class)
        private Long id;
        @ApiModelProperty(value = "用户id")
        @JsonSerialize(using = ToStringSerializer.class)
        private Long uid;
        @ApiModelProperty(value = "拼团id 生成")
        @JsonSerialize(using = ToStringSerializer.class)
        private Long assembleId;
        @ApiModelProperty(value = "订单id  数据库")
        @JsonSerialize(using = ToStringSerializer.class)
        private Long orderId;
        @ApiModelProperty(value = "购买商品个数")
        private Integer totalNum;
        @ApiModelProperty(value = "购买总金额")
        private BigDecimal totalPrice;
        @ApiModelProperty(value = "拼团产品id")
        @JsonSerialize(using = ToStringSerializer.class)
        private Long cid;
        @ApiModelProperty(value = "产品id")
        @JsonSerialize(using = ToStringSerializer.class)
        private Long pid;
        @ApiModelProperty(value = "拼图总人数")
        private Integer people;
        @ApiModelProperty(value = "拼团产品单价")
        private BigDecimal price;
        @ApiModelProperty(value = "开始时间")
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
        private Date addTime;
        @ApiModelProperty(value = "结束时间")
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
        private Date stopTime;
        @ApiModelProperty(value = "是否团长（0：否，1：是）")
        private Integer isAsmer;
        @ApiModelProperty(value = "是否发送模板消息0未发送1已发送")
        private Integer isTpl;
        @ApiModelProperty(value = "是否退款 0未退款 1已退款")
        private Integer isRefund;
        @ApiModelProperty(value = "状态1进行中2已完成3未完成")
        private Integer status;
        @ApiModelProperty(value = "团长用户信息")
        private UserExtVO extVO;
        @ApiModelProperty(value = "参团用户信息")
        private List<UserExtVO> userExtVOs;
}
