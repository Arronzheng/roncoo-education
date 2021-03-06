package com.roncoo.education.user.feign;

import org.springframework.cloud.openfeign.FeignClient;

import com.roncoo.education.user.common.interfaces.BossLecturer;

/**
 * 讲师信息
 */
@FeignClient(value = "roncoo-education-user-service")
public interface IBossLecturer extends BossLecturer {

}
