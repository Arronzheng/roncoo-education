
package com.roncoo.education.course.service.dao;

import com.roncoo.education.course.service.dao.impl.mapper.entity.CourseComment;
import com.roncoo.education.course.service.dao.impl.mapper.entity.CourseCommentExample;
import com.roncoo.education.util.base.Page;

import java.util.List;

/**
 *  服务类
 *
 * @author hw
 * @since 2019-12-05
 */
public interface CourseCommentDao {
            int save(CourseComment record);

            int deleteById(Long id);

            int updateById(CourseComment record);

            CourseComment getById(Long id);

            Page<CourseComment> listForPage(int pageCurrent, int pageSize, CourseCommentExample example);

            List<CourseComment> listByParentId(Long parentId);

            int deleteByPid(Long pid);

            List<CourseComment> getByCourseIdAndPid(Long courseId);
}
