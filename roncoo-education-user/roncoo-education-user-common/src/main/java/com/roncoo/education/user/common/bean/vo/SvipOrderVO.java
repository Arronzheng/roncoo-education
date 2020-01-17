package com.roncoo.education.user.common.bean.vo;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Data
@Accessors(chain = true)
public class SvipOrderVO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;
    private Date gmtCreate;
    private Date gmtModified;
    private Long userNo;
    private String mobile;
    private Date registerTime;
    private Long orderNo;
    private Long serialNo;
    private String orderName;
    private BigDecimal pricePayable;
    private BigDecimal priceDiscount;
    private BigDecimal pricePaid;
    private Integer pricePaidSource;
    private Integer tradeType;
    private Integer payType;
    private Integer channelType;
    private Integer orderStatus;
    private Integer isShowUser;
    private String remarkCus;
    private String remark;
    private Date payTime;
}
