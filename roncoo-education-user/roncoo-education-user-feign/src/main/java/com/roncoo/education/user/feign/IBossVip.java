package com.roncoo.education.user.feign;

import com.roncoo.education.user.common.interfaces.BossVip;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * 会员信息
 *
 */
@FeignClient(value = "roncoo-education-user-service")
public interface IBossVip extends BossVip {
}
