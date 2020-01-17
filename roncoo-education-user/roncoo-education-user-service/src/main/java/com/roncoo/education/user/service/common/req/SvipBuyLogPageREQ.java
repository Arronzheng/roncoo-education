package com.roncoo.education.user.service.common.req;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * SvipBuyLogPageREQ对象
 *
 * @author ${author}
 * @since 2019-12-25
 */
@Data
@Accessors(chain = true)
@ApiModel(value = "SvipBuyLogPageREQ分页请求对象", description = "会员购买日志表")
public class SvipBuyLogPageREQ implements Serializable {
    /**
	 * 当前页
	 */
    @ApiModelProperty(value = "当前页", required = true)
    private int pageCurrent = 1;
    /**
     * 每页记录数
     */
    @ApiModelProperty(value = "每页记录数", required = true)
    private int pageSize = 20;
    /**
     * 状态(1:正常，0:禁用)
     */
    @ApiModelProperty(value = "状态(1:正常，0:禁用)", required = false)
    private Integer statusId;
    /**
	 * 用户昵称
	 */
    @ApiModelProperty(value = "用户昵称")
    private String nickname;
    /**
     * 用户手机
     */
    @ApiModelProperty(value = "用户手机")
    private String mobile;
    /**
     * 开始时间
     */
    @ApiModelProperty(value = "开始时间", required = false)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private String beginGmtCreate;
    /**
     * 结束时间
     */
    @ApiModelProperty(value = "结束时间", required = false)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private String endGmtCreate;

}