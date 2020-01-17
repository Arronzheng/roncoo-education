package com.roncoo.education.course.common.interfaces;

import com.roncoo.education.course.common.bean.vo.CourseUserCollectionVO;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * 课程收藏信息
 */
public interface BossCourseUserCollection {

    @RequestMapping(value = "/boss/course/course/getCollectionListByUserNo/{userNo}", method = RequestMethod.GET)
    List<CourseUserCollectionVO> getCollectionListByUserNo(@PathVariable(value = "userNo") Long userNo);
}
