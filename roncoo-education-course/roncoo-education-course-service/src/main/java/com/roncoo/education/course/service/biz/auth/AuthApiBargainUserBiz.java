package com.roncoo.education.course.service.biz.auth;

import com.roncoo.education.course.service.common.bo.BargainRecordBO;
import com.roncoo.education.course.service.common.dto.BargainUserHelpListDTO;
import com.roncoo.education.course.service.common.dto.BargainUserHelpSaveDTO;
import com.roncoo.education.course.service.common.dto.BargainUserViewDTO;
import com.roncoo.education.course.service.common.dto.CourseViewDTO;
import com.roncoo.education.course.service.common.dto.auth.AuthBargainUserViewDTO;
import com.roncoo.education.course.service.common.req.BargainUserHelpREQ;
import com.roncoo.education.course.service.common.req.BargainUserSaveREQ;
import com.roncoo.education.course.service.common.req.BargainUserViewREQ;
import com.roncoo.education.course.service.common.resq.BargainUserPageRESQ;
import com.roncoo.education.course.service.common.resq.BargainUserViewRESQ;
import com.roncoo.education.course.service.dao.BargainCourseDao;
import com.roncoo.education.course.service.dao.BargainUserDao;
import com.roncoo.education.course.service.dao.BargainUserHelpDao;
import com.roncoo.education.course.service.dao.CourseAuditDao;
import com.roncoo.education.course.service.dao.impl.mapper.entity.BargainCourse;
import com.roncoo.education.course.service.dao.impl.mapper.entity.BargainUser;
import com.roncoo.education.course.service.dao.impl.mapper.entity.BargainUserHelp;
import com.roncoo.education.course.service.dao.impl.mapper.entity.CourseAudit;
import com.roncoo.education.user.common.bean.vo.UserExtVO;
import com.roncoo.education.user.feign.IBossUserExt;
import com.roncoo.education.util.base.Result;
import com.roncoo.education.util.enums.ResultEnum;
import com.roncoo.education.util.enums.StatusIdEnum;
import com.roncoo.education.util.tools.BeanUtil;
import com.roncoo.education.util.tools.DateUtil;
import com.roncoo.education.util.tools.IdWorker;
import com.xiaoleilu.hutool.util.CollectionUtil;
import com.xiaoleilu.hutool.util.ObjectUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;
import java.util.List;
import java.util.Random;

/**
 * 广告信息
 *
 */
@Component
public class AuthApiBargainUserBiz {

    @Autowired
    private BargainUserDao dao;
    @Autowired
    private CourseAuditDao courseAuditDao;
    @Autowired
    private BargainCourseDao bargainCourseDao;
    @Autowired
    private IBossUserExt bossUserExt;
    @Autowired
    private BargainUserHelpDao bargainUserHelpDao;

    /**
     * 添加BargainUser
     * @param req
     * @return
     */
    @Transactional
    public Result<BargainUserPageRESQ> save(BargainUserSaveREQ req) {
        //根据砍价课程id获取砍价课程信息
//        BargainCourse bargainCourse = bargainCourseDao.getByIdAndStatus(req.getBargainId(), StatusIdEnum.YES.getCode());
        List<BargainUser> bargainUsers = dao.getByBargainIdAndUidAndStatus(req.getBargainId(), req.getUid(), 1);
        if (null == bargainUsers || bargainUsers.isEmpty()) {
            BargainUser result = BeanUtil.copyProperties(req, BargainUser.class);
            result.setAddTime(new Date());
            result.setStatus(1);
            result.setIsDel(0);
            // 设置砍掉的价格（第一刀）,第一刀砍90%~95%的价格
            Double max = req.getBargainPrice().multiply(new BigDecimal("0.95")).doubleValue();
            Double min = req.getBargainPrice().multiply(new BigDecimal("0.90")).doubleValue();
            Double i = Math.floor(Math.random()*(max - min))+ min;
            //最小0.01元
            if (i < 0.01) {
                i = 0.01;
            }
//        Random random = new Random();

//        int s = random.nextInt(max)%(max-min+1) + min;
            result.setPrice(new BigDecimal(String.valueOf(i)).setScale(2, RoundingMode.HALF_UP));
            int results = dao.save(result);
            if (results > 0) {
                //记录自己的第一刀
                BargainUserHelp record = new BargainUserHelp();
                record.setAddTime(new Date());
                record.setBargainId(req.getBargainId()); // 砍价课程id
                record.setBargainUserId(result                                                                                                                                                                               .getId());// 用户参与砍价表id
                record.setPrice(result.getPrice());//砍掉的价格
                record.setUid(req.getUid()); // 帮助用户的id
                bargainUserHelpDao.save(record);
                BargainUserPageRESQ bargainUserPageRESQ = BeanUtil.copyProperties(result, BargainUserPageRESQ.class);
                return Result.success(bargainUserPageRESQ);
            }
            return Result.error(ResultEnum.COURSE_SAVE_FAIL);
        } else {
            BargainUserPageRESQ bargainUserPageRESQ = BeanUtil.copyProperties(bargainUsers.get(0), BargainUserPageRESQ.class);
            return Result.success(bargainUserPageRESQ);
        }

    }

    /**
     * 用户砍价详情
     *
     * @param req
     * @return
     */
    public Result<BargainUserViewDTO> view(BargainUserViewREQ req) {
        //根据用户砍价id查询砍价信息
        BargainUser bargainUser = dao.getById(req.getId());
        BargainUserViewDTO bargainUserViewDTO = BeanUtil.copyProperties(bargainUser, BargainUserViewDTO.class);
        //获取用户信息
        UserExtVO userExtVO = bossUserExt.getByUserNo(bargainUser.getUid());
        bargainUserViewDTO.setUserExtVO(userExtVO);
        //获取课程信息
        BargainCourse bargainCourse = bargainCourseDao.getById(bargainUser.getBargainId());
        CourseAudit courseAudit = courseAuditDao.getById(bargainCourse.getProductId());
        CourseViewDTO courseViewDTO = BeanUtil.copyProperties(courseAudit, CourseViewDTO.class);
        bargainUserViewDTO.setCourseViewDTO(courseViewDTO);
        //获取帮助砍价列表
        List<BargainUserHelp> bargainUserHelps = bargainUserHelpDao.getByBargainUserId(req.getId());
        List<BargainUserHelpListDTO> bargainUserHelpListDTOS = BeanUtil.copyProperties(bargainUserHelps, BargainUserHelpListDTO.class);
        for (BargainUserHelpListDTO bargainUserHelpListDTO : bargainUserHelpListDTOS) {
            UserExtVO extVO = bossUserExt.getByUserNo(bargainUserHelpListDTO.getUid());
            bargainUserHelpListDTO.setUserExtVO(extVO);
        }
        bargainUserViewDTO.setBargainUserHelpDTOList(bargainUserHelpListDTOS);
        return Result.success(bargainUserViewDTO);
    }

    @Transactional
    public synchronized Result<BargainUserHelpSaveDTO> helpBargain(BargainUserHelpREQ bargainUserHelpREQ) {
        BargainUser bargainUser = dao.getById(bargainUserHelpREQ.getId());
        if (!StringUtils.isEmpty(bargainUser)) {
            if (bargainUser.getStatus() == 3) {
                return Result.error(ResultEnum.BARGAIN_HELP_END);
            }
            //剩下的金额
            BigDecimal leftover = bargainUser.getBargainPrice().subtract(bargainUser.getPrice());
            // 设置砍掉的价格（非第一刀）,砍5%~10%的价格
            Double max = leftover.multiply(new BigDecimal("0.10")).doubleValue();
            Double min = leftover.multiply(new BigDecimal("0.05")).doubleValue();
            Double i = Math.floor(Math.random()*(max - min))+ min;
            //最小0.01元
            if (i < 0.01) {
                i = 0.01;
            }
            if (leftover.compareTo(new BigDecimal(String.valueOf(i))) < 0) {
                i = leftover.doubleValue();
            }
            BigDecimal bp = new BigDecimal(String.valueOf(i)).setScale(2, RoundingMode.HALF_UP);

            BargainUserHelp bargainUserHelp = new BargainUserHelp();
            bargainUserHelp.setUid(bargainUserHelpREQ.getUserNo());
            bargainUserHelp.setBargainId(bargainUser.getBargainId());
            bargainUserHelp.setBargainUserId(bargainUser.getId());
            bargainUserHelp.setPrice(bp);
            bargainUserHelp.setAddTime(new Date());
            bargainUserHelpDao.save(bargainUserHelp);

            //砍掉的价格
            BigDecimal bargaindPrice = bargainUser.getPrice().add(bp);
            if (bargaindPrice.compareTo(bargainUser.getBargainPrice()) == 0) {
                bargainUser.setStatus(3);
            }
            bargainUser.setPrice(bargaindPrice);
            int r = dao.updateById(bargainUser);
            BargainUserHelpSaveDTO bargainUserHelpSaveDTO = BeanUtil.copyProperties(bargainUserHelp, BargainUserHelpSaveDTO.class);
            return Result.success(bargainUserHelpSaveDTO);
        }
        return Result.error(ResultEnum.ERROR);
    }

    public Result<AuthBargainUserViewDTO> record(BargainRecordBO bargainRecordBO) {
        AuthBargainUserViewDTO authBargainUserViewDTO = new AuthBargainUserViewDTO();
        List<BargainUser> bargainUsers = dao.getByUid(bargainRecordBO.getUserNo());
        List<BargainUserViewDTO> bargainUserViewDTOs = BeanUtil.copyProperties(bargainUsers, BargainUserViewDTO.class);
        for (BargainUserViewDTO bargainUserViewDTO : bargainUserViewDTOs) {
            BargainCourse bargainCourse = bargainCourseDao.getById(bargainUserViewDTO.getBargainId());
            CourseAudit courseAudit = courseAuditDao.getById(bargainCourse.getProductId());
            CourseViewDTO courseViewDTO = BeanUtil.copyProperties(courseAudit, CourseViewDTO.class);
            bargainUserViewDTO.setCourseViewDTO(courseViewDTO);
        }
        authBargainUserViewDTO.setBargainUserViewDTOs(bargainUserViewDTOs);
        return Result.success(authBargainUserViewDTO);
    }
}
