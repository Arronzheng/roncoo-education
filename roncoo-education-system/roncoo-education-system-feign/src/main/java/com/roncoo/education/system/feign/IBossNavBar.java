package com.roncoo.education.system.feign;

import org.springframework.cloud.openfeign.FeignClient;

import com.roncoo.education.system.common.interfaces.BossNavBar;

/**
 * 头部导航
 *
 *
 */
@FeignClient(value = "roncoo-education-system-service")
public interface IBossNavBar extends BossNavBar {

}
