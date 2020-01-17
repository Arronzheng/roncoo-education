package com.roncoo.education.user.service.common.resq;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * SvipListRESQ对象
 *
 * @author ${author}
 * @since 2019-12-17
 */
@Data
@Accessors(chain = true)
@ApiModel(value = "SvipListREQ分页请求对象", description = "会员信息表")
public class SvipListRESQ implements Serializable {

        private static final long serialVersionUID = 1L;

        @ApiModelProperty(value = "开始时间")
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
        private Date startTime;
        @ApiModelProperty(value = "到期时间")
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
        private Date dueTime;
        @ApiModelProperty(value = "状态(0:非会员，1:会员，2过期会员)")
        private Integer statusId;
        @ApiModelProperty(value = "用户编号")
        private Long userNo;
}