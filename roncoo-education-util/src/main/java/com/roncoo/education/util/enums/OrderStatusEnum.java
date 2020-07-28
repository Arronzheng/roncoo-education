package com.roncoo.education.util.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 *
 */
@Getter
@AllArgsConstructor
public enum OrderStatusEnum {

    WAIT(1, "待支付"),
    SUCCESS(2, "支付成功"),
    FAIL(3, "支付失败"),
    CLOSE(4, "已关闭"),
    UNDELIVER(5, "待发货"),
    DELIVERED(6, "待收货"),
    COMPLETE(7, "已完成"),
    WAITASSEMBLE(8, "待拼成");

    private Integer code;

    private String desc;

}
