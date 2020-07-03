package com.roncoo.education.course.service.common.resq;

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
 * BargainUserHelpPageRESQ对象
 *
 * @author husend
 * @since 2020-04-14
 */
@Data
@Accessors(chain = true)
@ApiModel(value = "BargainUserHelpPageREQ分页请求对象", description = "砍价用户帮助表")
public class BargainUserHelpPageRESQ implements Serializable {

        private static final long serialVersionUID = 1L;

        @ApiModelProperty(value = "砍价用户帮助表ID")
        @JsonSerialize(using = ToStringSerializer.class)
        private Long id;
        @ApiModelProperty(value = "帮助的用户id")
        @JsonSerialize(using = ToStringSerializer.class)
        private Long uid;
        @ApiModelProperty(value = "砍价产品ID")
        @JsonSerialize(using = ToStringSerializer.class)
        private Long bargainId;
        @ApiModelProperty(value = "用户参与砍价表id")
        @JsonSerialize(using = ToStringSerializer.class)
        private Long bargainUserId;
        @ApiModelProperty(value = "帮助砍价多少金额")
        private BigDecimal price;
        @ApiModelProperty(value = "添加时间")
        private Date addTime;
}
