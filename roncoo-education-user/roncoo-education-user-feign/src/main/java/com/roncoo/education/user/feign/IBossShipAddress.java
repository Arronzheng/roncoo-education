package com.roncoo.education.user.feign;

import com.roncoo.education.user.common.interfaces.BossShipAddress;
import com.roncoo.education.user.common.interfaces.BossSvipOrder;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(value = "roncoo-education-user-service")
public interface IBossShipAddress extends BossShipAddress {
}
