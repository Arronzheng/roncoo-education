package com.roncoo.education.user.common.bean.qo;

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
public class SvipQO implements Serializable {

        private static final long serialVersionUID = 1L;

        /**
         * 当前页
         */
        private int pageCurrent;
        /**
         * 每页记录数
         */
        private int pageSize;
        /**
         * 开始时间
         */
        private Date startTime;
        /**
         * 到期时间
         */
        private Date dueTime;
        /**
         * 状态(0:非会员，1:会员，2过期会员)
         */
        private Integer statusId;
        /**
         * 用户编号
         */
        private Long userNo;

}