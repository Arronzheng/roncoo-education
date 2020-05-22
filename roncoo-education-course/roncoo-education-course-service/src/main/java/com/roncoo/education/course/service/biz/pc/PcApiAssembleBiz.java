package com.roncoo.education.course.service.biz.pc;

import com.roncoo.education.course.service.common.req.AssemblePageREQ;
import com.roncoo.education.course.service.common.req.AssembleSaveREQ;
import com.roncoo.education.course.service.common.req.AssembleViewREQ;
import com.roncoo.education.course.service.common.resq.AssembleCoursePageRESQ;
import com.roncoo.education.course.service.common.resq.AssemblePageRESQ;
import com.roncoo.education.course.service.common.resq.AssembleViewRESQ;
import com.roncoo.education.course.service.dao.AssembleCourseDao;
import com.roncoo.education.course.service.dao.AssembleDao;
import com.roncoo.education.course.service.dao.CourseAuditDao;
import com.roncoo.education.course.service.dao.CourseCategoryDao;
import com.roncoo.education.course.service.dao.impl.mapper.entity.*;
import com.roncoo.education.user.common.bean.vo.UserExtVO;
import com.roncoo.education.user.feign.IBossUserExt;
import com.roncoo.education.util.base.Page;
import com.roncoo.education.util.base.PageUtil;
import com.roncoo.education.util.base.Result;
import com.roncoo.education.util.enums.ResultEnum;
import com.roncoo.education.util.enums.StatusIdEnum;
import com.roncoo.education.util.tools.DateUtil;
import com.roncoo.education.util.tools.IdWorker;
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
public class PcApiAssembleBiz {

    @Autowired
    private AssembleDao dao;
    @Autowired
    private CourseAuditDao courseAuditDao;
    @Autowired
    private AssembleCourseDao assembleCourseDao;
    @Autowired
    private IBossUserExt bossUserExt;
    @Autowired
    private CourseCategoryDao courseCategoryDao;

    public Result<Page<AssemblePageRESQ>> list(AssemblePageREQ req) {
        AssembleExample example = new AssembleExample();
        AssembleExample.Criteria c = example.createCriteria();
//        if (!StringUtils.isEmpty(req.getCourseName())) {
//            CourseAudit courseAudit = courseAuditDao.getByCourseName(req.getCourseName());
//            if (ObjectUtil.isNull(courseAudit)) {
//                c.andProductIdEqualTo(0L);
//            } else {
//                c.andProductIdEqualTo(courseAudit.getId());
//            }
//        }
        Page<Assemble> listForPage = dao.listForPage(req.getPageCurrent(), req.getPageSize(), example);
        Page<AssemblePageRESQ> page = PageUtil.transform(listForPage, AssemblePageRESQ.class);

        // 根据课程ID获取课程信息
        for (AssemblePageRESQ resq : page.getList()) {
            UserExtVO userExtVO = bossUserExt.getByUserNo(resq.getUid());
            resq.setUserName(userExtVO.getNickname());
            List<CourseAudit> courseAudit = courseAuditDao.listByCourseId(resq.getPid());
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
     * 添加Assemble
     * @param req
     * @return
     */
    public Result<Integer> save(AssembleSaveREQ req) {
        // 根据课程编号获取课程信息
        CourseAudit courseAudit = courseAuditDao.getByCourseIdAndStatusId(req.getPid(), StatusIdEnum.YES.getCode());
        if (ObjectUtil.isNull(courseAudit) && !StatusIdEnum.YES.getCode().equals(courseAudit.getStatusId())) {
            return Result.error("找不到课程信息");
        }
        //根据拼团课程id获取拼团课程信息
        AssembleCourse assembleCourse = assembleCourseDao.getById(req.getCid());
        if(req.getPeople() == assembleCourse.getPeople()){
            return Result.error("拼团已成功");
        }
        Assemble result = new Assemble();
        result.setPid(courseAudit.getId());
        if(StringUtils.isEmpty(req.getAssembleId())){
            result.setAssembleId(IdWorker.getId());//发起拼团
            result.setIsAsmer(1);
            result.setPeople(1);
        } else {
            result.setAssembleId(req.getAssembleId());//参团
            result.setIsAsmer(0);
            result.setPeople(req.getPeople()+1);
        }
//        result.setOrderId();
        result.setAddTime(new Date());
        result.setStopTime(DateUtil.addDateByhour(result.getAddTime(), assembleCourse.getEffectiveTime()));
        result.setStatus(1);

        int results = dao.save(result);
        if (result.getPeople() == assembleCourse.getPeople()) {
            //拼团成功，修改状态
            Assemble a = new Assemble();
            a.setAssembleId(req.getAssembleId());
            a.setStatus(2);
            dao.updateByAssembleId(a);
        }
        if (results > 0) {
            return Result.success(results);
        }
        return Result.error(ResultEnum.COURSE_SAVE_FAIL);
    }

    /**
     * Assemble查看
     *
     * @param req
     * @return
     */
    public Result<AssembleViewRESQ> view(AssembleViewREQ req) {
        //根据id查询拼团表信息
        Assemble assemble = dao.getById(req.getId());
        //根据拼团表的uid查用户基本信息
        UserExtVO userVO = bossUserExt.getByUserNo(assemble.getUid());
        return null;
    }

}
