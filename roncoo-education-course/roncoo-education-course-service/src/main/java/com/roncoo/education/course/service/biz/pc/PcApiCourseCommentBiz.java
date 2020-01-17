package com.roncoo.education.course.service.biz.pc;

import com.roncoo.education.course.service.common.req.*;
import com.roncoo.education.course.service.common.resq.AdvPageRESQ;
import com.roncoo.education.course.service.common.resq.CourseCommentPageRESQ;
import com.roncoo.education.course.service.common.resq.CourseCommentViewRESQ;
import com.roncoo.education.course.service.dao.CourseAuditDao;
import com.roncoo.education.course.service.dao.CourseCommentDao;
import com.roncoo.education.course.service.dao.impl.mapper.entity.*;
import com.roncoo.education.user.common.bean.vo.UserExtVO;
import com.roncoo.education.user.feign.IBossUserExt;
import com.roncoo.education.util.base.Page;
import com.roncoo.education.util.base.PageUtil;
import com.roncoo.education.util.base.Result;
import com.roncoo.education.util.enums.ResultEnum;
import com.roncoo.education.util.tools.BeanUtil;
import com.roncoo.education.util.tools.DateUtil;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 广告信息
 *
 */
@Component
public class PcApiCourseCommentBiz {

    @Autowired
    private CourseCommentDao dao;
    @Autowired
    private CourseAuditDao courseAuditDao;
    @Autowired
    private IBossUserExt bossUserExt;

    public Result<Page<CourseCommentPageRESQ>> list(CourseCommentPageREQ req) {
        CourseCommentExample example = new CourseCommentExample();
        CourseCommentExample.Criteria c = example.createCriteria();

        if (req.getStatus() != null) {
            c.andStatusEqualTo(req.getStatus());
        }
        if (req.getContent() != null) {
            c.andContentLike(PageUtil.like(req.getContent()));
        }
        if (StringUtils.hasText(req.getBeginCreateTime())) {
            c.andCreatedAtGreaterThanOrEqualTo(DateUtil.parseDate(req.getBeginCreateTime(), "yyyy-MM-dd"));
        }
        if (StringUtils.hasText(req.getEndCreateTime())) {
            c.andCreatedAtLessThanOrEqualTo(DateUtil.addDate(DateUtil.parseDate(req.getEndCreateTime(), "yyyy-MM-dd"), 1));
        }
        c.andPidEqualTo(0L);
        example.setOrderByClause(" created_at desc ");
        Page<CourseComment> page = dao.listForPage(req.getPageCurrent(), req.getPageSize(), example);
        Page<CourseCommentPageRESQ> listForPage = PageUtil.transform(page, CourseCommentPageRESQ.class);
        for (CourseCommentPageRESQ resq: listForPage.getList()) {
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
            resq.setChildren(recursionList(resq.getId()));
        }
        return Result.success(listForPage);
    }

    /**
     *
     * 添加CourseComment
     *
     * @param req
     * @return
     */
    public Result<Integer> save(CourseCommentSaveREQ req) {

        return null;
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
    public Result<Integer> delete(CourseCommentDeleteREQ req) {
        if (StringUtils.isEmpty(req.getId())) {
            return Result.error("ID不能为空");
        }
        int results = dao.deleteById(req.getId());
        if (results > 0) {
            dao.deleteByPid(req.getId());
            return Result.success(results);
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