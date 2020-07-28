package com.roncoo.education.course.service.biz.auth;

import com.roncoo.education.course.service.common.bo.AssembleIngBO;
import com.roncoo.education.course.service.common.bo.AssemblePageBO;
import com.roncoo.education.course.service.common.bo.AssembleRecordBO;
import com.roncoo.education.course.service.common.bo.AssembleViewBO;
import com.roncoo.education.course.service.common.dto.AssemblePageDTO;
import com.roncoo.education.course.service.common.dto.AssembleViewDTO;
import com.roncoo.education.course.service.common.dto.CourseViewDTO;
import com.roncoo.education.course.service.common.dto.auth.AuthAssembleViewDTO;
import com.roncoo.education.course.service.common.req.AssembleSaveREQ;
import com.roncoo.education.course.service.dao.AssembleCourseDao;
import com.roncoo.education.course.service.dao.AssembleDao;
import com.roncoo.education.course.service.dao.CourseAuditDao;
import com.roncoo.education.course.service.dao.impl.mapper.entity.Assemble;
import com.roncoo.education.course.service.dao.impl.mapper.entity.AssembleCourse;
import com.roncoo.education.course.service.dao.impl.mapper.entity.AssembleExample;
import com.roncoo.education.course.service.dao.impl.mapper.entity.CourseAudit;
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
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.List;

/**
 * 广告信息
 *
 */
@Component
public class AuthApiAssembleBiz {

    @Autowired
    private AssembleDao dao;
    @Autowired
    private CourseAuditDao courseAuditDao;
    @Autowired
    private AssembleCourseDao assembleCourseDao;
    @Autowired
    private IBossUserExt bossUserExt;

    /**
     * 添加Assemble
     * @param req
     * @return
     */
    @Transactional
    public Result<Integer> save(AssembleSaveREQ req) {
        // 根据课程编号获取课程信息
        CourseAudit courseAudit = courseAuditDao.getByCourseIdAndStatusId(req.getPid(), StatusIdEnum.YES.getCode());
        if (ObjectUtil.isNull(courseAudit)) {
            return Result.error("找不到课程信息");
        }
        //根据拼团课程id获取拼团课程信息
        AssembleCourse assembleCourse = assembleCourseDao.getById(req.getCid());
        Assemble result = BeanUtil.copyProperties(req, Assemble.class);
        if(StringUtils.isEmpty(req.getAssembleId())){
            result.setAssembleId(IdWorker.getId());//发起拼团
            result.setIsAsmer(1);
            result.setPeople(1);
        } else {
            result.setAssembleId(req.getAssembleId());//参团
            result.setIsAsmer(0); //是否团长（0：否，1：是）
            result.setPeople(1);
        }
//        result.setOrderId();
        result.setAddTime(new Date());
        result.setStopTime(DateUtil.addDateByhour(result.getAddTime(), assembleCourse.getEffectiveTime()));
        result.setStatus(4); // 拼团状态1进行中2已完成3未完成4未开始，未支付是为未开始状态
        result.setOrderId(req.getOrderId());
        result.setIsRefund(0); //是否退款 0未退款 1已退款
        int results = dao.save(result);
        if(!StringUtils.isEmpty(req.getAssembleId())){
            List<Assemble> assembleList = dao.getByAssembleId(req.getAssembleId()); // 进行中的拼团
            //根据同一个拼团id的记录条数判断拼团是否完成
            if (assembleList.size() == assembleCourse.getPeople()) {
                //拼团成功，修改状态
                Assemble a = new Assemble();
                a.setAssembleId(req.getAssembleId());
                a.setStatus(2);
                dao.updateByAssembleId(a);
            }
        }
        if (results > 0) {
            return Result.success(results);
        }
        return Result.error(ResultEnum.COURSE_SAVE_FAIL);
    }

    /**
     * Assemble查看
     *
     * @param bo
     * @return
     */
    public Result<AuthAssembleViewDTO> view(AssembleViewBO bo) {
        AuthAssembleViewDTO authAssembleViewDTO = new AuthAssembleViewDTO();
        //根据拼团id查询参团信息
        List<Assemble> assembles = dao.getByAssembleId(bo.getAssembleId());
        List<AssembleViewDTO> assembleViewDTOs = BeanUtil.copyProperties(assembles, AssembleViewDTO.class);
        //根据拼团表的用户id查用户基本信息
        for (AssembleViewDTO asm : assembleViewDTOs) {
            UserExtVO usv = bossUserExt.getByUserNo(asm.getUid());
            asm.setExtVO(usv);
        }
        authAssembleViewDTO.setAssembleViewDTOs(assembleViewDTOs);
        return Result.success(authAssembleViewDTO);
    }

    public Result<AuthAssembleViewDTO> record(AssembleRecordBO assembleRecordBO) {
        AuthAssembleViewDTO authAssembleViewDTO = new AuthAssembleViewDTO();
        //根据拼团id查询参团信息
        List<Assemble> assembles = dao.getByUid(assembleRecordBO.getUserNo());
        List<AssembleViewDTO> assembleViewDTOs = BeanUtil.copyProperties(assembles, AssembleViewDTO.class);
        //根据拼团表的用户id查用户基本信息
        for (AssembleViewDTO asm : assembleViewDTOs) {
            CourseAudit courseAudit = courseAuditDao.getById(asm.getPid());
            CourseViewDTO courseViewDTO = BeanUtil.copyProperties(courseAudit, CourseViewDTO.class);
            asm.setCourseViewDTO(courseViewDTO);
        }
        authAssembleViewDTO.setAssembleViewDTOs(assembleViewDTOs);
        return Result.success(authAssembleViewDTO);
    }

    public Result<Boolean> isAssemble(AssembleIngBO assembleIngBO) {
        Assemble assemble = dao.getByUserNoAndPidAndStatus(assembleIngBO);
        if (ObjectUtils.isEmpty(assemble)) {
            return Result.success(false);
        }
        return Result.success(true);
    }
}
