
package com.roncoo.education.course.service.dao;

import com.roncoo.education.course.service.common.bo.AssembleIngBO;
import com.roncoo.education.course.service.dao.impl.mapper.entity.Assemble;
import com.roncoo.education.course.service.dao.impl.mapper.entity.AssembleCourse;
import com.roncoo.education.course.service.dao.impl.mapper.entity.AssembleExample;
import com.roncoo.education.util.base.Page;

import java.util.List;

/**
 * 拼团表 服务类
 *
 * @author husend
 * @since 2020-04-14
 */
public interface AssembleDao {
            int save(Assemble record);

            int deleteById(Long id);

            int updateById(Assemble record);

            Assemble getById(Long id);

            Page<Assemble> listForPage(int pageCurrent, int pageSize, AssembleExample example);

            void updateByAssembleId(Assemble a);

            List<Assemble> getByAssembleId(Long id);

            List<Assemble> getByCid(Long id, Long userNo);

            List<Assemble> getByUid(Long userNo);

            Assemble getOrderId(Long orderNo);

            Assemble getByUserNoAndPidAndStatus(AssembleIngBO assembleIngBO);

            void updateByOrderId(Assemble assemble);
}
