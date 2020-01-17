package com.roncoo.education.user.service.common.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * SvipBuyLogSaveREQ 请求对象
 *
 * @author ${author}
 * @since 2019-12-25
 */
@Data
@Accessors(chain = true)
@ApiModel(value = "SvipBuyLog新增请求对象", description = "会员购买日志表")
public class SvipBuyLogSaveREQ implements Serializable {

        private static final long serialVersionUID = 1L;

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