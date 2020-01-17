package com.roncoo.education.course.service.biz.auth;

import com.roncoo.education.course.service.common.bo.auth.AuthCourseUserCollectionDelBO;
import com.roncoo.education.course.service.common.bo.auth.AuthCourseUserCollectionPageBO;
import com.roncoo.education.course.service.common.bo.auth.AuthCourseUserCollectionSaveBO;
import com.roncoo.education.course.service.common.dto.auth.AuthCourseUserCollectionPageDTO;
import com.roncoo.education.course.service.dao.CourseAuditDao;
import com.roncoo.education.course.service.dao.CourseUserCollectionDao;
import com.roncoo.education.course.service.dao.impl.mapper.entity.CourseAudit;
import com.roncoo.education.course.service.dao.impl.mapper.entity.CourseUserCollection;
import com.roncoo.education.course.service.dao.impl.mapper.entity.CourseUserCollectionExample;
import com.roncoo.education.util.base.BaseBiz;
import com.roncoo.education.util.base.Page;
import com.roncoo.education.util.base.PageUtil;
import com.roncoo.education.util.base.Result;
import com.roncoo.education.util.enums.ResultEnum;
import com.roncoo.education.util.enums.StatusIdEnum;
import com.roncoo.education.util.tools.BeanUtil;
import com.xiaoleilu.hutool.util.ObjectUtil;
import lombok.experimental.Accessors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.Date;

@Component
public class AuthApiCourseUserCollectionBiz extends BaseBiz {

    @Autowired
    private CourseUserCollectionDao courseUserCollectionDao;
    @Autowired
    private CourseAuditDao courseAuditDao;

    public Result<Page<AuthCourseUserCollectionPageDTO>> listForPage(AuthCourseUserCollectionPageBO authCourseUserCollectionPageBO) {
        if (authCourseUserCollectionPageBO.getUserNo() == null) {
            return Result.error("userNo不能为空");
        }
        CourseUserCollectionExample example = new CourseUserCollectionExample();
        CourseUserCollectionExample.Criteria c = example.createCriteria();
        c.andUserNoEqualTo(authCourseUserCollectionPageBO.getUserNo());
        Page<CourseUserCollection> page = courseUserCollectionDao.listForPage(authCourseUserCollectionPageBO.getPageCurrent(), authCourseUserCollectionPageBO.getPageSize(), example);
        Page<AuthCourseUserCollectionPageDTO> dtoList = PageUtil.transform(page, AuthCourseUserCollectionPageDTO.class);
        return Result.success(dtoList);
    }

    public Result<Integer> addCollection(AuthCourseUserCollectionSaveBO authCourseUserCollectionSaveBO) {
        if (StringUtils.isEmpty(authCourseUserCollectionSaveBO.getUserNo())) {
            return Result.error("用户不能为空");
        }
        if (StringUtils.isEmpty(authCourseUserCollectionSaveBO.getCourseId())) {
            return Result.error("课程不能为空");
        }
        CourseAudit courseAudit = courseAuditDao.getByCourseIdAndStatusId(authCourseUserCollectionSaveBO.getCourseId(), StatusIdEnum.YES.getCode());
        if(ObjectUtil.isNull(courseAudit)){
            return Result.error("找不到课程");
        }
        CourseUserCollection courseUserCollection = courseUserCollectionDao.getByUserNoAndCourseId(authCourseUserCollectionSaveBO.getUserNo(), authCourseUserCollectionSaveBO.getCourseId());
        if(ObjectUtil.isNotNull(courseUserCollection)){
            return Result.error(ResultEnum.COLLECTION);
        }
        courseUserCollection = new CourseUserCollection();
        courseUserCollection.setUserNo(authCourseUserCollectionSaveBO.getUserNo());
        courseUserCollection.setCourseId(courseAudit.getId());
        courseUserCollection.setCourseName(courseAudit.getCourseName());
        courseUserCollection.setCourseLogo(courseAudit.getCourseLogo());
        courseUserCollection.setGmtCreate(new Date());

        Integer i = courseUserCollectionDao.save(courseUserCollection);
        // 查询更新后的课程审核信息
        if (i > 0) {
            return Result.success(i);
        }
        return Result.error(ResultEnum.COURSE_SAVE_FAIL);
    }

    public Result<Integer> cancelCollection(AuthCourseUserCollectionDelBO authCourseUserCollectionDelBO) {
        if (StringUtils.isEmpty(authCourseUserCollectionDelBO.getCourseId())) {
            return Result.error("课程不能为空");
        }
        return Result.success(courseUserCollectionDao.deleteByCourseIdAndUserNo(authCourseUserCollectionDelBO));
    }
}
