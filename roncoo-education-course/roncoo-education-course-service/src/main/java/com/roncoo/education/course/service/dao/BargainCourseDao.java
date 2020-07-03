
package com.roncoo.education.course.service.dao;

import com.roncoo.education.course.service.dao.impl.mapper.entity.BargainCourse;
import com.roncoo.education.course.service.dao.impl.mapper.entity.BargainCourseExample;
import com.roncoo.education.util.base.Page;

import java.util.List;

/**
 * 砍价表 服务类
 *
 * @author husend
 * @since 2020-04-14
 */
public interface BargainCourseDao {
            int save(BargainCourse record);

            int deleteById(Long id);

            int updateById(BargainCourse record);

            BargainCourse getById(Long id);

            Page<BargainCourse> listForPage(int pageCurrent, int pageSize, BargainCourseExample example);

            List<BargainCourse> getByProductId(Long id);

            BargainCourse getByIdAndStatus(Long bargainId, Integer code);
}
