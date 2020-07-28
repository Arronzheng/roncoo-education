package com.roncoo.education.system.feign;

import org.springframework.cloud.openfeign.FeignClient;

import com.roncoo.education.system.common.interfaces.BossSysMenu;

/**
 * 菜单信息
 */
@FeignClient(value = "roncoo-education-system-service")
public interface IBossSysMenu extends BossSysMenu {

}
