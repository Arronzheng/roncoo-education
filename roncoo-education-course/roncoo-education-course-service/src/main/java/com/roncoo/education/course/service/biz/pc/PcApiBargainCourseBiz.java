package com.roncoo.education.course.service.biz.pc;

import com.roncoo.education.course.service.common.req.*;
import com.roncoo.education.course.service.common.resq.BargainCoursePageRESQ;
import com.roncoo.education.course.service.common.resq.BargainCourseViewRESQ;
import com.roncoo.education.course.service.dao.BargainCourseDao;
import com.roncoo.education.course.service.dao.CourseAuditDao;
import com.roncoo.education.course.service.dao.CourseCategoryDao;
import com.roncoo.education.course.service.dao.impl.mapper.entity.BargainCourse;
import com.roncoo.education.course.service.dao.impl.mapper.entity.BargainCourseExample;
import com.roncoo.education.course.service.dao.impl.mapper.entity.CourseAudit;
import com.roncoo.education.course.service.dao.impl.mapper.entity.CourseCategory;
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

import java.util.Date;
import java.util.List;

/**
 * 广告信息
 *
 */
@Component
public class PcApiBargainCourseBiz {

    @Autowired
    private BargainCourseDao dao;
    @Autowired
    private CourseAuditDao courseAuditDao;
    @Autowired
    private CourseCategoryDao courseCategoryDao;

    public Result<Page<BargainCoursePageRESQ>> list(BargainCoursePageREQ req) {
        BargainCourseExample example = new BargainCourseExample();
        BargainCourseExample.Criteria c = example.createCriteria();
//        if (!StringUtils.isEmpty(req.getCourseName())) {
//            CourseAudit courseAudit = courseAuditDao.getByCourseName(req.getCourseName());
//            if (ObjectUtil.isNull(courseAudit)) {
//                c.andProductIdEqualTo(0L);
//            } else {
//                c.andProductIdEqualTo(courseAudit.getId());
//            }
//        }
        example.setOrderByClause("sort desc");
        Page<BargainCourse> listForPage = dao.listForPage(req.getPageCurrent(), req.getPageSize(), example);
        Page<BargainCoursePageRESQ> page = PageUtil.transform(listForPage, BargainCoursePageRESQ.class);
        // 根据课程ID获取课程信息
        for (BargainCoursePageRESQ resq : page.getList()) {
            List<CourseAudit> courseAudit = courseAuditDao.listByCourseId(resq.getProductId());
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
     * 添加BargainCourse
     *
     * @param req
     * @return
     */
    public Result<Integer> save(BargainCourseSaveREQ req) {
        if (ObjectUtil.isNull(req.getProductId())) {
            return Result.error("课程ID不能为空");
        }

        // 根据课程编号获取课程信息
        CourseAudit courseAudit = courseAuditDao.getByCourseIdAndStatusId(req.getProductId(), StatusIdEnum.YES.getCode());
        if (ObjectUtil.isNull(courseAudit) && !StatusIdEnum.YES.getCode().equals(courseAudit.getStatusId())) {
            return Result.error("找不到课程信息");
        }

        // 保存分区关联课程信息
        BargainCourse result = BeanUtil.copyProperties(req, BargainCourse.class);
        result.setProductId(courseAudit.getId());
        result.setPrice(courseAudit.getCourseOriginal());
        result.setSort(1);
        result.setAddTime(new Date());
        int results = dao.save(result);
        if (results > 0) {
            return Result.success(results);
        }
        return Result.error(ResultEnum.COURSE_SAVE_FAIL);
    }

    /**
     * 更新BargainCourse信息
     *
     * @param req
     * @return
     */
    public Result<Integer> update(BargainCourseUpdateREQ req) {
        if (StringUtils.isEmpty(req.getId())) {
            return Result.error("ID不能为空");
        }
        BargainCourse record = BeanUtil.copyProperties(req, BargainCourse.class);
        int results = dao.updateById(record);
        if (results > 0) {
            return Result.success(results);
        }
        return Result.error(ResultEnum.COURSE_DELETE_FAIL);
    }

    /**
     * 删除BargainCourse信息
     *
     * @param req
     * @return
     */
    public Result<Integer> delete(BargainCourseDeleteREQ req) {
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
     * BargainCourse查看
     *
     * @param req
     * @return
     */
    public Result<BargainCourseViewRESQ> view(BargainCourseViewREQ req) {
        if (StringUtils.isEmpty(req.getId())) {
            return Result.error("ID不能为空");
        }
        BargainCourse record = dao.getById(req.getId());
        if (ObjectUtil.isNull(record)) {
            return Result.error("找不到专区信息");
        }
        return Result.success(BeanUtil.copyProperties(record, BargainCourseViewRESQ.class));
    }

}
