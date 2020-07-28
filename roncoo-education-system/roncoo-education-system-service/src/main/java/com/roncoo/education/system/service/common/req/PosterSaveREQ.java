package com.roncoo.education.system.service.common.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * PosterSaveREQ 请求对象
 *
 * @author husend
 * @since 2020-07-25
 */
@Data
@Accessors(chain = true)
@ApiModel(value = "Poster新增请求对象", description = "")
public class PosterSaveREQ implements Serializable {

        private static final long serialVersionUID = 1L;

        @ApiModelProperty(value = "状态(1有效, 0无效)")
        private Integer statusId;

        @ApiModelProperty(value = "海报")
        private String poster;

        @ApiModelProperty(value = "海报类型（1邀请有礼，2砍价）")
        private Integer posterType;

        @ApiModelProperty(value = "备注")
        private String remark;

}
