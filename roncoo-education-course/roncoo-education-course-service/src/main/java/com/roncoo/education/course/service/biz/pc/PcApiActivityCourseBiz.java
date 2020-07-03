package com.roncoo.education.course.service.biz.pc;

import com.roncoo.education.course.service.common.req.*;
import com.roncoo.education.course.service.common.resq.ActivityCoursePageRESQ;
import com.roncoo.education.course.service.common.resq.ActivityCourseViewRESQ;
import com.roncoo.education.course.service.dao.ActivityCourseDao;
import com.roncoo.education.course.service.dao.CourseAuditDao;
import com.roncoo.education.course.service.dao.CourseCategoryDao;
import com.roncoo.education.course.service.dao.impl.mapper.entity.*;
import com.roncoo.education.util.base.Page;
import com.roncoo.education.util.base.PageUtil;
import com.roncoo.education.util.base.Result;
import com.roncoo.education.util.enums.ResultEnum;
import com.roncoo.education.util.enums.StatusIdEnum;
import com.roncoo.education.util.tools.BeanUtil;
import com.xiaoleilu.hutool.util.ObjectUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * 广告信息
 *
 */
@Component
public class PcApiActivityCourseBiz {

    @Autowired
    private ActivityCourseDao dao;
    @Autowired
    private CourseAuditDao courseAuditDao;
    @Autowired
    private CourseCategoryDao courseCategoryDao;

    public Result<Page<ActivityCoursePageRESQ>> list(ActivityCoursePageREQ req) {
        ActivityCourseExample example = new ActivityCourseExample();
        ActivityCourseExample.Criteria c = example.createCriteria();
        c.andActivityCategoryEqualTo(req.getActivityCategory());
        if (!StringUtils.isEmpty(req.getCourseName())) {
            CourseAudit courseAudit = courseAuditDao.getByCourseName(req.getCourseName());
            if (ObjectUtil.isNull(courseAudit)) {
                c.andCourseIdEqualTo(0L);
            } else {
                c.andCourseIdEqualTo(courseAudit.getId());
            }
        }
        example.setOrderByClause("status_id desc, sort desc, id desc");
        Page<ActivityCourse> listForPage = dao.listForPage(req.getPageCurrent(), req.getPageSize(), example);
        Page<ActivityCoursePageRESQ> page = PageUtil.transform(listForPage, ActivityCoursePageRESQ.class);
        // 根据课程ID获取课程信息
        for (ActivityCoursePageRESQ resq : page.getList()) {
            List<CourseAudit> courseAudit = courseAuditDao.listByCourseId(resq.getCourseId());
            for (CourseAudit courseAuditInfo : courseAudit) {
                // 获取课程所属分类
                if (courseAuditInfo.getCategoryId1() != null && courseAuditInfo.getCategoryId1() != 0) {
                    CourseCategory courseCategory = courseCategoryDao.getById(courseAuditInfo.getCategoryId1());
                    if (courseCategory != null) {
                        resq.setCategoryName1(courseCategory.getCategoryName());
                    }
                }
                if (courseAuditInfo.getCategoryId2() != null && courseAuditInfo.getCategoryId2() != 0) {
                    CourseCategory courseCategory = courseCategoryDao.getById(courseAuditInfo.getCategoryId2());
                    if (courseCategory != null) {
                        resq.setCategoryName2(courseCategory.getCategoryName());
                    }
                }
                if (courseAuditInfo.getCategoryId3() != null && courseAuditInfo.getCategoryId3() != 0) {
                    CourseCategory courseCategory = courseCategoryDao.getById(courseAuditInfo.getCategoryId3());
                    if (courseCategory != null) {
                        resq.setCategoryName3(courseCategory.getCategoryName());
                    }
                }
                resq.setCourseName(courseAuditInfo.getCourseName());
            }
        }
        return Result.success(page);
    }

    /**
     *
     * 添加ActivityCourse
     *
     * @param req
     * @return
     */
    public Result<Integer> save(ActivityCourseSaveREQ req) {
        if (ObjectUtil.isNull(req.getCourseId())) {
            return Result.error("课程ID不能为空");
        }

        // 根据课程编号获取课程信息
        CourseAudit courseAudit = courseAuditDao.getByCourseIdAndStatusId(req.getCourseId(), StatusIdEnum.YES.getCode());
        if (ObjectUtil.isNull(courseAudit) && !StatusIdEnum.YES.getCode().equals(courseAudit.getStatusId())) {
            return Result.error("找不到课程信息");
        }

        // 保存分区关联课程信息
        ActivityCourse result = new ActivityCourse();
        result.setCourseId(courseAudit.getId());
        result.setActivityCategory(req.getActivityCategory());
        result.setSort(1);
        int results = dao.save(result);
        if (results > 0) {
            return Result.success(results);
        }
        return Result.error(ResultEnum.COURSE_SAVE_FAIL);
    }

    /**
     * 更新ActivityCourse信息
     *
     * @param req
     * @return
     */
    public Result<Integer> update(ActivityCourseUpdateREQ req) {
        if (StringUtils.isEmpty(req.getId())) {
            return Result.error("ID不能为空");
        }
        ActivityCourse record = BeanUtil.copyProperties(req, ActivityCourse.class);
        int results = dao.updateById(record);
        if (results > 0) {
            return Result.success(results);
        }
        return Result.error(ResultEnum.COURSE_DELETE_FAIL);
    }

    /**
     * 删除ActivityCourse信息
     *
     * @param req
     * @return
     */
    public Result<Integer> delete(ActivityCourseDeleteREQ req) {
        if (StringUtils.isEmpty(req.getId())) {
            return Result.error("ID不能为空");
        }
        int results = dao.deleteById(req.getId());
        if (results > 0) {
            return Result.success(results);
        }
        return Result.error(ResultEnum.COURSE_DELETE_FAIL);
    }

    /**
     * ActivityCourse查看
     *
     * @param req
     * @return
     */
    public Result<ActivityCourseViewRESQ> view(ActivityCourseViewREQ req) {
        if (StringUtils.isEmpty(req.getId())) {
            return Result.error("ID不能为空");
        }
        ActivityCourse record = dao.getById(req.getId());
        if (ObjectUtil.isNull(record)) {
            return Result.error("找不到专区信息");
        }
        return Result.success(BeanUtil.copyProperties(record, ActivityCourseViewRESQ.class));
    }

}
