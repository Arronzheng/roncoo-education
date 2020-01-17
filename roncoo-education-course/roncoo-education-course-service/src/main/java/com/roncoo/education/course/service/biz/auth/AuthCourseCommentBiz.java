package com.roncoo.education.course.service.biz.auth;

import com.roncoo.education.course.service.common.dto.CourseViewDTO;
import com.roncoo.education.course.service.common.dto.auth.AuthCourseCommentDTO;
import com.roncoo.education.course.service.common.req.*;
import com.roncoo.education.course.service.common.resq.CourseCommentPageRESQ;
import com.roncoo.education.course.service.common.resq.CourseCommentViewRESQ;
import com.roncoo.education.course.service.dao.CourseAuditDao;
import com.roncoo.education.course.service.dao.CourseCommentDao;
import com.roncoo.education.course.service.dao.impl.mapper.entity.CourseAudit;
import com.roncoo.education.course.service.dao.impl.mapper.entity.CourseComment;
import com.roncoo.education.user.common.bean.vo.UserExtVO;
import com.roncoo.education.user.feign.IBossUserExt;
import com.roncoo.education.util.base.PageUtil;
import com.roncoo.education.util.base.Result;
import com.roncoo.education.util.enums.ResultEnum;
import com.roncoo.education.util.tools.BeanUtil;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 广告信息
 *
 */
@Component
public class AuthCourseCommentBiz {

    @Autowired
    private CourseCommentDao dao;
    @Autowired
    private CourseAuditDao courseAuditDao;
    @Autowired
    private IBossUserExt bossUserExt;

    /**
     *
     * 添加CourseComment
     *
     * @param req
     * @return
     */
    @Transactional
    public Result<CourseViewDTO> save(CourseCommentSaveREQ req) {
        if (StringUtils.isEmpty(req.getUserId())) {
            return Result.error("用户id不能为空");
        }
        if (StringUtils.isEmpty(req.getCourseId())) {
            return Result.error("课程不能为空");
        }
        if (StringUtils.isEmpty(req.getChapterId())) {
            return Result.error("章节不能为空");
        }
        if (StringUtils.isEmpty(req.getPeriodId())) {
            return Result.error("课时不能为空");
        }
        if(req.getContent().length() > 300){
            return Result.error("输入内容长度超出限制！");
        }
        CourseComment courseComment = BeanUtil.copyProperties(req, CourseComment.class);
        courseComment.setCreatedAt(Date.from(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant()));
        courseComment.setStatus(1);
        courseComment.setTopping(2);
//        int result = dao.save(courseComment);
        if(dao.save(courseComment) > 0){
            List<CourseComment> courseCommentList = dao.getByCourseIdAndPid(courseComment.getCourseId());
            List<AuthCourseCommentDTO> courseCommentDTOList = PageUtil.copyList(courseCommentList, AuthCourseCommentDTO.class);
            for (AuthCourseCommentDTO courseCommentDTO : courseCommentDTOList) {
                courseCommentDTO.setCourseAudit(courseAuditDao.getById(courseCommentDTO.getCourseId()));
                courseCommentDTO.setUserExt(bossUserExt.getByUserNo(courseCommentDTO.getUserId()));
                if(courseCommentDTO.getId().equals(courseComment.getPid())){
                    courseCommentDTO.setIsPackup(0);
                }else{
                    courseCommentDTO.setIsPackup(1);
                }
                List<CourseComment> childList = dao.listByParentId(courseCommentDTO.getId());
                List<AuthCourseCommentDTO> childDTOList = PageUtil.copyList(childList, AuthCourseCommentDTO.class);
                for (AuthCourseCommentDTO childDTO: childDTOList) {
                    childDTO.setCourseAudit(courseAuditDao.getById(childDTO.getCourseId()));
                    childDTO.setUserExt(bossUserExt.getByUserNo(childDTO.getUserId()));
                    childDTO.setParentUserExt(bossUserExt.getByUserNo(childDTO.getParentNo()));
                }
                courseCommentDTO.setChildren(childDTOList);
            }
            CourseViewDTO courseViewDTO = new CourseViewDTO();
            courseViewDTO.setCourseCommentList(courseCommentDTOList);
            return Result.success(courseViewDTO);
        }
        return Result.error(ResultEnum.COURSE_SAVE_FAIL);
    }

    /**
     * 更新CourseComment信息
     *
     * @param req
     * @return
     */
    public Result<Integer> update(CourseCommentUpdateREQ req) {
        if (StringUtils.isEmpty(req.getId())) {
            return Result.error("ID不能为空");
        }
        CourseComment record = BeanUtil.copyProperties(req, CourseComment.class);
        int results = dao.updateById(record);
        if (results > 0) {
            return Result.success(results);
        }
        return Result.error(ResultEnum.COURSE_DELETE_FAIL);
    }

    /**
     * 删除CourseComment信息
     *
     * @param req
     * @return
     */
    @Transactional
    public Result<CourseViewDTO> delete(CourseCommentDeleteREQ req) {
        if (StringUtils.isEmpty(req.getId())) {
            return Result.error("ID不能为空");
        }
        if (StringUtils.isEmpty(req.getUserNo())) {
            return Result.error("userNo不能为空");
        }
        CourseComment courseComment = dao.getById(req.getId());
        if(!courseComment.getUserId().equals(req.getUserNo())){
            return Result.error("传入的用户与该评论的用户不一致!");
        }
//        return null;
        int results = dao.deleteById(req.getId());
        if (results > 0) {
            dao.deleteByPid(req.getId());//删除评论下的回复
            //删除后的评论
            List<CourseComment> courseCommentList = dao.getByCourseIdAndPid(courseComment.getCourseId());
            List<AuthCourseCommentDTO> courseCommentDTOList = PageUtil.copyList(courseCommentList, AuthCourseCommentDTO.class);
            for (AuthCourseCommentDTO courseCommentDTO : courseCommentDTOList) {
                courseCommentDTO.setCourseAudit(courseAuditDao.getById(courseCommentDTO.getCourseId()));
                courseCommentDTO.setUserExt(bossUserExt.getByUserNo(courseCommentDTO.getUserId()));
                if(courseCommentDTO.getId().equals(courseComment.getPid())){
                    courseCommentDTO.setIsPackup(0);
                }else{
                    courseCommentDTO.setIsPackup(1);
                }
                List<CourseComment> childList = dao.listByParentId(courseCommentDTO.getId());
                List<AuthCourseCommentDTO> childDTOList = PageUtil.copyList(childList, AuthCourseCommentDTO.class);
                for (AuthCourseCommentDTO childDTO: childDTOList) {
                    childDTO.setCourseAudit(courseAuditDao.getById(childDTO.getCourseId()));
                    childDTO.setUserExt(bossUserExt.getByUserNo(childDTO.getUserId()));
                    childDTO.setParentUserExt(bossUserExt.getByUserNo(childDTO.getParentNo()));
                }
                courseCommentDTO.setChildren(childDTOList);
            }
            CourseViewDTO courseViewDTO = new CourseViewDTO();
            courseViewDTO.setCourseCommentList(courseCommentDTOList);
            return Result.success(courseViewDTO);
        }
        return Result.error(ResultEnum.COURSE_DELETE_FAIL);
    }

    /**
     * CourseComment查看
     *
     * @param req
     * @return
     */
    public Result<CourseCommentViewRESQ> view(CourseCommentViewREQ req) {

        return null;
    }

    /**
     * 递归展示分类
     *
     * @author WY
     */
    private List<CourseCommentPageRESQ> recursionList(Long parentId) {
        List<CourseCommentPageRESQ> list = new ArrayList<>();
        List<CourseComment> courseCommentList = dao.listByParentId(parentId);
        if (CollectionUtils.isNotEmpty(courseCommentList)) {
            for (CourseComment courseComment : courseCommentList) {
                CourseCommentPageRESQ resq = BeanUtil.copyProperties(courseComment, CourseCommentPageRESQ.class);
                //课程名称
                CourseAudit courseAudit = courseAuditDao.getById(resq.getCourseId());
                resq.setCourseName(courseAudit.getCourseName());
                //用户昵称
                UserExtVO userExtVO = bossUserExt.getByUserNo(resq.getUserId());
                resq.setUserExtName(StringUtils.isEmpty(userExtVO.getNickname()) ? userExtVO.getMobile() : userExtVO.getNickname());
                if(resq.getParentNo() != 0){
                    UserExtVO parentUserExtVO = bossUserExt.getByUserNo(resq.getParentNo());
                    resq.setParentUserExtName(StringUtils.isEmpty(parentUserExtVO.getNickname()) ? parentUserExtVO.getMobile() : parentUserExtVO.getNickname());
                }
                resq.setChildren(recursionList(courseComment.getId()));
                list.add(resq);
            }
        }
        return list;
    }

}