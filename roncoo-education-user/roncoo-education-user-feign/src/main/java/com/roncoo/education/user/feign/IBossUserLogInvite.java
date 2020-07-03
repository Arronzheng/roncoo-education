package com.roncoo.education.user.feign;

import com.roncoo.education.user.common.interfaces.BossUserLogInvite;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(value = "roncoo-education-user-service")
public interface IBossUserLogInvite extends BossUserLogInvite {
}
