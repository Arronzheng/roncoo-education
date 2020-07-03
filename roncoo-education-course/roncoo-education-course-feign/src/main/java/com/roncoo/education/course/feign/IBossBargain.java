package com.roncoo.education.course.feign;

import com.roncoo.education.course.common.interfaces.BossBargain;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * 砍价信息
 */
@FeignClient(value = "roncoo-education-course-service")
public interface IBossBargain extends BossBargain {

}
