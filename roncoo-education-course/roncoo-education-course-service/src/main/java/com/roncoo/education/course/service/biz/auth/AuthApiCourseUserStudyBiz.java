package com.roncoo.education.course.service.biz.auth;

import com.roncoo.education.course.service.common.bo.auth.AuthCourseUserStudyLogCountBO;
import com.roncoo.education.course.service.common.bo.auth.AuthCourseUserStudyPageBO;
import com.roncoo.education.course.service.common.dto.auth.AuthCourseUserStudyPageDTO;
import com.roncoo.education.course.service.dao.CourseAuditDao;
import com.roncoo.education.course.service.dao.CourseUserStudyDao;
import com.roncoo.education.course.service.dao.CourseUserStudyLogDao;
import com.roncoo.education.course.service.dao.impl.mapper.entity.*;
import com.roncoo.education.util.base.BaseBiz;
import com.roncoo.education.util.base.Page;
import com.roncoo.education.util.base.PageUtil;
import com.roncoo.education.util.base.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AuthApiCourseUserStudyBiz extends BaseBiz {

    @Autowired
    private CourseUserStudyDao courseUserStudyDao;
    @Autowired
    private CourseAuditDao courseAuditDao;
    @Autowired
    private CourseUserStudyLogDao courseUserStudyLogDao;


    public Result<Page<AuthCourseUserStudyPageDTO>> list(AuthCourseUserStudyPageBO authCourseUserStudyPageBO) {
        CourseUserStudyExample example = new CourseUserStudyExample();
        CourseUserStudyExample.Criteria c = example.createCriteria();
        if (authCourseUserStudyPageBO.getUserNo() == null) {
            return Result.error("userNo不能为空");
        }
        c.andUserNoEqualTo(authCourseUserStudyPageBO.getUserNo());
        Page<CourseUserStudy> page = courseUserStudyDao.listForPage(authCourseUserStudyPageBO.getPageCurrent(), authCourseUserStudyPageBO.getPageSize(), example);
        Page<AuthCourseUserStudyPageDTO> dtoList = PageUtil.transform(page, AuthCourseUserStudyPageDTO.class);
        for (AuthCourseUserStudyPageDTO dto : dtoList.getList()) {
            // 查看课程信息
            CourseAudit courseAudit = courseAuditDao.getById(dto.getCourseId());
            // 查看课时信息
            CourseUserStudyLog courseUserStudyLog = courseUserStudyLogDao.getByCourseIdAndSortByTimeDesc(dto.getCourseId());
            if(courseAudit != null && courseUserStudyLog != null){
                dto.setCourseName(courseAudit.getCourseName());
                dto.setCourseImgUrl(courseAudit.getCourseLogo());
                dto.setLastPeriodId(courseUserStudyLog.getPeriodId());
                dto.setLsatPeriodName(courseUserStudyLog.getPeriodName());
                dto.setLastStudyTime(courseUserStudyLog.getGmtCreate());
            }

        }
        return Result.success(dtoList);
    }

    public Result<Integer> count(AuthCourseUserStudyLogCountBO authCourseUserStudyLogCountBO) {
        CourseUserStudyExample example = new CourseUserStudyExample();
        CourseUserStudyExample.Criteria c = example.createCriteria();
        if (authCourseUserStudyLogCountBO.getUserNo() == null) {
            return Result.error("userNo不能为空");
        }
        c.andUserNoEqualTo(authCourseUserStudyLogCountBO.getUserNo());
        int count = courseUserStudyDao.countByUserNo(example);
        return Result.success(count);
    }
}
