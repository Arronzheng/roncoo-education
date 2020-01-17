package com.roncoo.education.course.service.biz.pc;

import com.roncoo.education.course.service.common.req.*;
import com.roncoo.education.course.service.common.resq.CourseUserCollectionPageRESQ;
import com.roncoo.education.course.service.common.resq.CourseUserCollectionViewRESQ;
import com.roncoo.education.course.service.dao.CourseUserCollectionDao;
import com.roncoo.education.util.base.Page;
import com.roncoo.education.util.base.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 广告信息
 *
 */
@Component
public class PcApiCourseUserCollectionBiz {

    @Autowired
    private CourseUserCollectionDao dao;

    public Result<Page<CourseUserCollectionPageRESQ>> list(CourseUserCollectionPageREQ req) {

        return null;
    }

    /**
     *
     * 添加CourseUserCollection
     *
     * @param req
     * @return
     */
    public Result<Integer> save(CourseUserCollectionSaveREQ req) {

        return null;
    }

    /**
     * 更新CourseUserCollection信息
     *
     * @param req
     * @return
     */
    public Result<Integer> update(CourseUserCollectionUpdateREQ req) {

        return null;
    }

    /**
     * 删除CourseUserCollection信息
     *
     * @param req
     * @return
     */
    public Result<Integer> delete(CourseUserCollectionDeleteREQ req) {

        return null;
    }

    /**
     * CourseUserCollection查看
     *
     * @param req
     * @return
     */
    public Result<CourseUserCollectionViewRESQ> view(CourseUserCollectionViewREQ req) {

        return null;
    }

}