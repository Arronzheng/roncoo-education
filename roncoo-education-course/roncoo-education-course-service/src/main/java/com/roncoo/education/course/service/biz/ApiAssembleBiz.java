package com.roncoo.education.course.service.biz;

import com.roncoo.education.course.service.common.bo.AssemblePageBO;
import com.roncoo.education.course.service.common.bo.AssembleViewBO;
import com.roncoo.education.course.service.common.dto.AssemblePageDTO;
import com.roncoo.education.course.service.common.dto.AssembleViewDTO;
import com.roncoo.education.course.service.common.req.*;
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
import com.roncoo.education.util.enums.IsAsmerEnum;
import com.roncoo.education.util.enums.ResultEnum;
import com.roncoo.education.util.enums.StatusIdEnum;
import com.roncoo.education.util.tools.BeanUtil;
import com.roncoo.education.util.tools.DateUtil;
import com.roncoo.education.util.tools.IdWorker;
import com.xiaoleilu.hutool.util.ObjectUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

/**
 * 广告信息
 *
 */
@Component
public class ApiAssembleBiz {

    @Autowired
    private AssembleDao dao;
    @Autowired
    private CourseAuditDao courseAuditDao;
    @Autowired
    private AssembleCourseDao assembleCourseDao;
    @Autowired
    private IBossUserExt bossUserExt;

    public Result<Page<AssemblePageDTO>> list(AssemblePageBO bo) {
        AssembleExample example = new AssembleExample();
        AssembleExample.Criteria c = example.createCriteria();
        c.andPidEqualTo(bo.getPid());
        c.andStatusEqualTo(1);//进行中的拼团
        example.setOrderByClause("status_id desc, sort desc, id desc");
        Page<Assemble> listForPage = dao.listForPage(bo.getPageCurrent(), bo.getPageSize(), example);
        Page<AssemblePageDTO> page = PageUtil.transform(listForPage, AssemblePageDTO.class);
        for (AssemblePageDTO assemblePageDTO : page.getList()) {
            UserExtVO userVO = bossUserExt.getByUserNo(assemblePageDTO.getUid());
            assemblePageDTO.setUserExtVO(userVO);

            AssembleCourse assembleCourse = assembleCourseDao.getById(assemblePageDTO.getCid());
            assemblePageDTO.setLackAssembleNum(assembleCourse.getPeople() - page.getList().size());
        }
        return Result.success(page);
    }
}
