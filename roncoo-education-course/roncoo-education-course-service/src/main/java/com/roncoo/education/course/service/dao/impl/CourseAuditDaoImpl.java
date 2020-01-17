package com.roncoo.education.course.service.dao.impl;

import com.roncoo.education.course.service.dao.CourseAuditDao;
import com.roncoo.education.course.service.dao.impl.mapper.CourseAuditMapper;
import com.roncoo.education.course.service.dao.impl.mapper.entity.Course;
import com.roncoo.education.course.service.dao.impl.mapper.entity.CourseAudit;
import com.roncoo.education.course.service.dao.impl.mapper.entity.CourseAuditExample;
import com.roncoo.education.course.service.dao.impl.mapper.entity.CourseAuditExample.Criteria;
import com.roncoo.education.course.service.dao.impl.mapper.entity.CourseExample;
import com.roncoo.education.util.base.Page;
import com.roncoo.education.util.base.PageUtil;
import com.roncoo.education.util.tools.IdWorker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CourseAuditDaoImpl implements CourseAuditDao {
    @Autowired
    private CourseAuditMapper courseAuditMapper;

    public int save(CourseAudit record) {
        record.setId(IdWorker.getId());
        return this.courseAuditMapper.insertSelective(record);
    }

    public int deleteById(Long id) {
        return this.courseAuditMapper.deleteByPrimaryKey(id);
    }

    public int updateById(CourseAudit record) {
    	record.setGmtCreate(null);
		record.setGmtModified(null);
        return this.courseAuditMapper.updateByPrimaryKeySelective(record);
    }

    public CourseAudit getById(Long id) {
        return this.courseAuditMapper.selectByPrimaryKey(id);
    }

    public Page<CourseAudit> listForPage(int pageCurrent, int pageSize, CourseAuditExample example) {
        int count = this.courseAuditMapper.countByExample(example);
        pageSize = PageUtil.checkPageSize(pageSize);
        pageCurrent = PageUtil.checkPageCurrent(count, pageSize, pageCurrent);
        int totalPage = PageUtil.countTotalPage(count, pageSize);
        example.setLimitStart(PageUtil.countOffset(pageCurrent, pageSize));
        example.setPageSize(pageSize);
        return new Page<CourseAudit>(count, totalPage, pageCurrent, pageSize, this.courseAuditMapper.selectByExample(example));
    }

	@Override
	public int updateAuditStatusBycourseId(Integer auditStatus, Long courseId) {
		CourseAuditExample example = new CourseAuditExample();
		Criteria c = example.createCriteria();
		c.andIdEqualTo(courseId);
		CourseAudit record = new CourseAudit();
		record.setId(courseId);
		record.setAuditStatus(auditStatus);
		record.setGmtCreate(null);
		record.setGmtModified(null);
		return this.courseAuditMapper.updateByExampleSelective(record, example);
	}

    @Override
    public CourseAudit getByCourseName(String courseName) {
        CourseAuditExample example = new CourseAuditExample();
        CourseAuditExample.Criteria c = example.createCriteria();
        c.andCourseNameLike(PageUtil.rightLike(courseName));
        List<CourseAudit> courseAuditList =  this.courseAuditMapper.selectByExample(example);
        if (courseAuditList.isEmpty()) {
            return null;
        }
        return courseAuditList.get(0);
    }

    @Override
    public List<CourseAudit> listByCategoryId(Long courseId) {
        CourseAuditExample example = new CourseAuditExample();
        Criteria c = example.createCriteria();
        c.andIdEqualTo(courseId);
        List<CourseAudit> list = this.courseAuditMapper.selectByExample(example);
        return list;
    }

    @Override
    public CourseAudit getByCourseIdAndStatusId(Long courseId, Integer StatusId) {
        CourseAuditExample example = new CourseAuditExample();
        Criteria c = example.createCriteria();
        c.andIdEqualTo(courseId);
        c.andStatusIdEqualTo(StatusId);
        List<CourseAudit> list = this.courseAuditMapper.selectByExample(example);
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }
}