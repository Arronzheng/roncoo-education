package com.roncoo.education.system.feign;

import org.springframework.cloud.openfeign.FeignClient;

import com.roncoo.education.system.common.interfaces.BossWebsiteLink;

/**
 * 站点友情链接
 *
 *
 */
@FeignClient(value = "roncoo-education-system-service")
public interface IBossWebsiteLink extends BossWebsiteLink {

}
