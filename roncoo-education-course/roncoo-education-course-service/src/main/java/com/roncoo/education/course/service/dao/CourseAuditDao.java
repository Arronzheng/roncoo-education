package com.roncoo.education.course.service.dao;

import com.roncoo.education.course.service.dao.impl.mapper.entity.CourseAudit;
import com.roncoo.education.course.service.dao.impl.mapper.entity.CourseAuditExample;
import com.roncoo.education.util.base.Page;

import java.util.List;

public interface CourseAuditDao {
	int save(CourseAudit record);

	int deleteById(Long id);

	int updateById(CourseAudit record);

	CourseAudit getById(Long id);

	Page<CourseAudit> listForPage(int pageCurrent, int pageSize, CourseAuditExample example);

	/**
	 * 根据课程编号更新课程审核状态
	 *
	 * @param auditStatus
	 * @param courseId
	 * @return
	 * @author WY
	 */
	int updateAuditStatusBycourseId(Integer auditStatus, Long courseId);

    CourseAudit getByCourseName(String courseName);

	List<CourseAudit> listByCategoryId(Long courseId, String courseName);

	CourseAudit getByCourseIdAndStatusId(Long courseId, Integer code);

	List<CourseAudit> listByCourseId(Long courseId);
}
