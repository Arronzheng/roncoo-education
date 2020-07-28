package com.roncoo.education.system.feign;

import org.springframework.cloud.openfeign.FeignClient;

import com.roncoo.education.system.common.interfaces.BossSys;

/**
 * 系统配置表
 *
 *
 */
@FeignClient(value = "roncoo-education-system-service")
public interface IBossSys extends BossSys {

}
