package com.roncoo.education.course.service.common.req;

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
 * AssembleSaveREQ 请求对象
 *
 * @author husend
 * @since 2020-04-14
 */
@Data
@Accessors(chain = true)
@ApiModel(value = "Assemble新增请求对象", description = "拼团表")
public class AssembleSaveREQ implements Serializable {

        private static final long serialVersionUID = 1L;

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
        private Date addTime;

        @ApiModelProperty(value = "结束时间")
        private Date stopTime;

        @ApiModelProperty(value = "是否团长（0：否，1：是）")
        private Integer isAsmer;

        @ApiModelProperty(value = "是否发送模板消息0未发送1已发送")
        private Integer isTpl;

        @ApiModelProperty(value = "是否退款 0未退款 1已退款")
        private Integer isRefund;

        @ApiModelProperty(value = "状态1进行中2已完成3未完成")
        private Integer status;

}
