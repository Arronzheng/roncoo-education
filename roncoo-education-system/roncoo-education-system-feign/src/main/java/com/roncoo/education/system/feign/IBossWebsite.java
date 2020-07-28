package com.roncoo.education.system.feign;

import org.springframework.cloud.openfeign.FeignClient;

import com.roncoo.education.system.common.interfaces.BossWebsite;

/**
 * 站点信息
 *
 *
 */
@FeignClient(value = "roncoo-education-system-service")
public interface IBossWebsite extends BossWebsite {

}
