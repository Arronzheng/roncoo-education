package com.roncoo.education.user.service.common.resq;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * SvipBuyLogListRESQ对象
 *
 * @author ${author}
 * @since 2019-12-25
 */
@Data
@Accessors(chain = true)
@ApiModel(value = "SvipBuyLogListREQ分页请求对象", description = "会员购买日志表")
public class SvipBuyLogListRESQ implements Serializable {

        private static final long serialVersionUID = 1L;

        @ApiModelProperty(value = "开始时间")
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
        private Date startTime;
        @ApiModelProperty(value = "到期时间")
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
        private Date dueTime;
        @ApiModelProperty(value = "用户编号")
        private Long userNo;
        @ApiModelProperty(value = "用户昵称")
        private String nickname;
        @ApiModelProperty(value = "用户手机")
        private String mobile;
}