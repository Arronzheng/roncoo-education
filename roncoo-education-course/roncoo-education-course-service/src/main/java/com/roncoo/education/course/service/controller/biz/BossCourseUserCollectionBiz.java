package com.roncoo.education.course.service.controller.biz;

import com.roncoo.education.course.common.bean.vo.CourseUserCollectionVO;
import com.roncoo.education.course.service.dao.CourseUserCollectionDao;
import com.roncoo.education.course.service.dao.impl.mapper.entity.CourseUserCollection;
import com.roncoo.education.util.base.BaseBiz;
import com.roncoo.education.util.tools.BeanUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class BossCourseUserCollectionBiz extends BaseBiz {

    @Autowired
    private CourseUserCollectionDao dao;

    public List<CourseUserCollectionVO> getCollectionListByUserNo(Long userNo) {
        List<CourseUserCollection> CourseUserCollectionList = dao.getCollectionListByUserNo(userNo);
        return BeanUtil.copyProperties(CourseUserCollectionList, CourseUserCollectionVO.class);
    }
}
