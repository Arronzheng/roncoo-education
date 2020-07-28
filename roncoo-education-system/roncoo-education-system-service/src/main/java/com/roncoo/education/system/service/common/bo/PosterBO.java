package com.roncoo.education.system.service.common.bo;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * 系统配置表
 *
 *
 */
@Data
@Accessors(chain = true)
public class PosterBO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 用户编号
     */
    private Long userNo;
    /**
     * 海报类型
     */
    private Integer posterType;

}
