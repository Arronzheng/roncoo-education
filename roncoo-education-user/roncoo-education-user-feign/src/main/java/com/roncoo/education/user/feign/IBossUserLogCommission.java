package com.roncoo.education.user.feign;

import com.roncoo.education.user.common.interfaces.BossUserLogCommission;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(value = "roncoo-education-user-service")
public interface IBossUserLogCommission extends BossUserLogCommission {
}
