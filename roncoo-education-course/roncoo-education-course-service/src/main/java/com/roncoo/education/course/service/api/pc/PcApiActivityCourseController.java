package com.roncoo.education.course.service.api.pc;

import com.roncoo.education.course.service.biz.pc.PcApiActivityCourseBiz;
import com.roncoo.education.course.service.common.req.*;
import com.roncoo.education.course.service.common.resq.ActivityCoursePageRESQ;
import com.roncoo.education.course.service.common.resq.ActivityCourseViewRESQ;
import com.roncoo.education.util.base.BaseController;
import com.roncoo.education.util.base.Page;
import com.roncoo.education.util.base.Result;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * 活动专区课程关联表 前端控制器
 *
 * @author husend
 * @since 2020-04-10
 */
@RestController
@RequestMapping("/course/pc/activity/course")
    public class PcApiActivityCourseController extends BaseController {

        @Autowired
        private PcApiActivityCourseBiz biz;

        /**
         * ActivityCourse分页列表接口
         * @param activityCoursePageREQ   分页信息
         * @return
         */
        @ApiOperation(value = "activityCourse分页列表接口", notes = "activityCourse分页列表接口")
        @RequestMapping(value = "/list", method = RequestMethod.POST)
        public Result<Page<ActivityCoursePageRESQ>> list(@RequestBody ActivityCoursePageREQ activityCoursePageREQ) {
            return biz.list(activityCoursePageREQ);
        }

        /**
        * ActivityCourse添加接口
        * @param activityCourseSaveREQ
        * @return
        */
        @ApiOperation(value = "activityCourse添加接口", notes = "activityCourse添加接口")
        @RequestMapping(value = "/save", method = RequestMethod.POST)
        public Result<Integer> save(@RequestBody ActivityCourseSaveREQ activityCourseSaveREQ) {
            return biz.save(activityCourseSaveREQ);
        }

        /**
         * ActivityCourse更新接口
         * @param activityCourseUpdateREQ
         * @return
         */
        @ApiOperation(value = "activityCourse更新接口", notes = "activityCourse更新接口")
        @RequestMapping(value = "/update", method = RequestMethod.POST)
        public Result<Integer> update(@RequestBody ActivityCourseUpdateREQ activityCourseUpdateREQ) {
            return biz.update(activityCourseUpdateREQ);
        }

        /**
         * ActivityCourse删除接口
         * @param activityCourseDeleteREQ
         * @return
         */
        @ApiOperation(value = "activityCourse删除接口", notes = "activityCourse删除接口")
        @RequestMapping(value = "/delete", method = RequestMethod.POST)
        public Result<Integer> delete(@RequestBody ActivityCourseDeleteREQ activityCourseDeleteREQ) {
            return biz.delete(activityCourseDeleteREQ);
        }

        /**
         * ActivityCourse查看接口
         * @param activityCourseViewREQ
         * @return
         */
        @ApiOperation(value = "activityCourse查看接口", notes = "activityCourse查看接口")
        @RequestMapping(value = "/view", method = RequestMethod.POST)
        public Result<ActivityCourseViewRESQ> view(@RequestBody ActivityCourseViewREQ activityCourseViewREQ) {
            return biz.view(activityCourseViewREQ);
        }


    }

