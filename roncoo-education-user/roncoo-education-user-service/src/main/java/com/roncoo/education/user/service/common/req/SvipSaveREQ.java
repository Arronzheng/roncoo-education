package com.roncoo.education.user.service.common.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * SvipSaveREQ 请求对象
 *
 * @author ${author}
 * @since 2019-12-17
 */
@Data
@Accessors(chain = true)
@ApiModel(value = "Svip新增请求对象", description = "会员信息表")
public class SvipSaveREQ implements Serializable {

        private static final long serialVersionUID = 1L;

        @ApiModelProperty(value = "开始时间")
        private Date startTime;

        @ApiModelProperty(value = "到期时间")
        private Date dueTime;

        @ApiModelProperty(value = "状态(0:非会员，1:会员，2过期会员)")
        private Integer statusId;

        @ApiModelProperty(value = "用户编号")
        private Long userNo;

}