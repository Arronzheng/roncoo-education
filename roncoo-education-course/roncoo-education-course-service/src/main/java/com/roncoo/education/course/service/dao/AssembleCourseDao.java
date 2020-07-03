
package com.roncoo.education.course.service.dao;

import com.roncoo.education.course.service.dao.impl.mapper.entity.AssembleCourse;
import com.roncoo.education.course.service.dao.impl.mapper.entity.AssembleCourseExample;
import com.roncoo.education.util.base.Page;

import java.util.List;

/**
 * 拼团产品表 服务类
 *
 * @author husend
 * @since 2020-04-14
 */
public interface AssembleCourseDao {
            int save(AssembleCourse record);

            int deleteById(Long id);

            int updateById(AssembleCourse record);

            AssembleCourse getById(Long id);

            Page<AssembleCourse> listForPage(int pageCurrent, int pageSize, AssembleCourseExample example);

            List<AssembleCourse> getByProductId(Long id);
}
