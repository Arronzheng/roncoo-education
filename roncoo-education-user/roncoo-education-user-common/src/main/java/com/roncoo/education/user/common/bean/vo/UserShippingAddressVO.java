package com.roncoo.education.user.common.bean.vo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Data
@Accessors(chain = true)
public class UserShippingAddressVO implements Serializable {

    /**
     * 用户编号
     */
    private Long userNo;
    /**
     * 收货人
     */
    private String receiverName;
    /**
     * 手机号码
     */
    private String receiverPhone;
    /**
     * 省份
     */
    private String receiverProvince;
    /**
     * 市
     */
    private String receiverCity;
    /**
     * 区
     */
    private String receiverDistrict;
    /**
     * 详细地址
     */
    private String receiverAddress;
    /**
     * 邮政编码
     */
    private String receiverZip;
    /**
     * 设为默认
     */
    private Integer isToleration;
}
