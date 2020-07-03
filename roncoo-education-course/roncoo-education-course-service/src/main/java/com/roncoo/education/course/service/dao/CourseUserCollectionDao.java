
package com.roncoo.education.course.service.dao;

import com.roncoo.education.course.service.common.bo.auth.AuthCourseUserCollectionCountBO;
import com.roncoo.education.course.service.common.bo.auth.AuthCourseUserCollectionDelBO;
import com.roncoo.education.course.service.dao.impl.mapper.entity.CourseUserCollection;
import com.roncoo.education.course.service.dao.impl.mapper.entity.CourseUserCollectionExample;
import com.roncoo.education.util.base.Page;

import java.util.List;

/**
 *  服务类
 *
 * @author ${author}
 * @since 2020-01-04
 */
public interface CourseUserCollectionDao {
            int save(CourseUserCollection record);

            int deleteById(Long id);

            int updateById(CourseUserCollection record);

            CourseUserCollection getById(Long id);

            Page<CourseUserCollection> listForPage(int pageCurrent, int pageSize, CourseUserCollectionExample example);

            CourseUserCollection getByUserNoAndCourseId(Long userNo, Long courseId);

            List<CourseUserCollection> getCollectionListByUserNo(Long userNo);

            int deleteByCourseIdAndUserNo(AuthCourseUserCollectionDelBO authCourseUserCollectionDelBO);

            int countCollectionByUserNo(AuthCourseUserCollectionCountBO authCourseUserCollectionCountBO);
}
