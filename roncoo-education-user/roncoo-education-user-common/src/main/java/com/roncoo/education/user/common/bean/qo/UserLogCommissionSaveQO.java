package com.roncoo.education.user.common.bean.qo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * UserLogCommissionSaveREQ 请求对象
 *
 * @author husend
 * @since 2020-06-11
 */
@Data
@Accessors(chain = true)
public class UserLogCommissionSaveQO implements Serializable {

        private static final long serialVersionUID = 1L;

        /**
         * 用户编号
         */
        private Long userNo;
        /**
         * 佣金来源用户编号
         */
        private Long sourceUserNo;
        /**
         * 订单编号
         */
        private Long orderNo;
        /**
         * 佣金
         */
        private BigDecimal commission;
        /**
         * 添加时间
         */
        private LocalDateTime addTime;

}
