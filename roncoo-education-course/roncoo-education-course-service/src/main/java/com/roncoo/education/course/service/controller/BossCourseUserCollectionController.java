package com.roncoo.education.course.service.controller;

import com.roncoo.education.course.common.bean.vo.CourseUserCollectionVO;
import com.roncoo.education.course.common.interfaces.BossCourseUserCollection;
import com.roncoo.education.course.service.controller.biz.BossCourseUserCollectionBiz;
import com.roncoo.education.util.base.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class BossCourseUserCollectionController extends BaseController implements BossCourseUserCollection {

    @Autowired
    private BossCourseUserCollectionBiz biz;

    @Override
    public List<CourseUserCollectionVO> getCollectionListByUserNo(@PathVariable(value = "userNo") Long userNo) {
        return biz.getCollectionListByUserNo(userNo);
    }
}
