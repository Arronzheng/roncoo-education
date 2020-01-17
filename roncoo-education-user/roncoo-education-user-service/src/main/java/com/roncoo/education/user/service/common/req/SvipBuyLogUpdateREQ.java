package com.roncoo.education.user.service.common.req;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * SvipBuyLogUpdateREQ 请求对象
 *
 * @author ${author}
 * @since 2019-12-25
 */
@Data
@Accessors(chain = true)
@ApiModel(value = "SvipBuyLog更新请求对象", description = "会员购买日志表")
public class SvipBuyLogUpdateREQ implements Serializable {

        private static final long serialVersionUID = 1L;

        @ApiModelProperty(value = "主键", required = true)
        @JsonSerialize(using = ToStringSerializer.class)
        private Long id;
        @ApiModelProperty(value = "开始时间")
        private Date startTime;
        @ApiModelProperty(value = "到期时间")
        private Date dueTime;
        @ApiModelProperty(value = "用户编号")
        private Long userNo;
        @ApiModelProperty(value = "用户昵称")
        private String nickname;
        @ApiModelProperty(value = "用户手机")
        private String mobile;
}