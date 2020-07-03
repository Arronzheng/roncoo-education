package com.roncoo.education.course.feign;

import com.roncoo.education.course.common.interfaces.BossAssemble;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * 拼团信息
 */
@FeignClient(value = "roncoo-education-course-service")
public interface IBossAssemble extends BossAssemble {

}
