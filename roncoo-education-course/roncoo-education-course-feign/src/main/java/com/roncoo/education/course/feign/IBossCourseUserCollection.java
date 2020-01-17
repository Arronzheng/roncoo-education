package com.roncoo.education.course.feign;

import com.roncoo.education.course.common.interfaces.BossCourseUserCollection;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * 课程收藏信息
 */
@FeignClient(value = "roncoo-education-course-service")
public interface IBossCourseUserCollection extends BossCourseUserCollection {
}
