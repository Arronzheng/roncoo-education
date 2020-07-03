package com.roncoo.education.course.service.api.pc;

import com.roncoo.education.course.service.biz.pc.PcApiBargainCourseBiz;
import com.roncoo.education.course.service.common.req.*;
import com.roncoo.education.course.service.common.resq.BargainCoursePageRESQ;
import com.roncoo.education.course.service.common.resq.BargainCourseViewRESQ;
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
 * 砍价表 前端控制器
 *
 * @author husend
 * @since 2020-04-14
 */
@RestController
@RequestMapping("/course/pc/bargain/course")
    public class PcApiBargainCourseController extends BaseController {

        @Autowired
        private PcApiBargainCourseBiz biz;

        /**
         * BargainCourse分页列表接口
         * @param bargainCoursePageREQ   分页信息
         * @return
         */
        @ApiOperation(value = "bargainCourse分页列表接口", notes = "bargainCourse分页列表接口")
        @RequestMapping(value = "/list", method = RequestMethod.POST)
        public Result<Page<BargainCoursePageRESQ>> list(@RequestBody BargainCoursePageREQ bargainCoursePageREQ) {
            return biz.list(bargainCoursePageREQ);
        }

        /**
        * BargainCourse添加接口
        * @param bargainCourseSaveREQ
        * @return
        */
        @ApiOperation(value = "bargainCourse添加接口", notes = "bargainCourse添加接口")
        @RequestMapping(value = "/save", method = RequestMethod.POST)
        public Result<Integer> save(@RequestBody BargainCourseSaveREQ bargainCourseSaveREQ) {
            return biz.save(bargainCourseSaveREQ);
        }

        /**
         * BargainCourse更新接口
         * @param bargainCourseUpdateREQ
         * @return
         */
        @ApiOperation(value = "bargainCourse更新接口", notes = "bargainCourse更新接口")
        @RequestMapping(value = "/update", method = RequestMethod.POST)
        public Result<Integer> update(@RequestBody BargainCourseUpdateREQ bargainCourseUpdateREQ) {
            return biz.update(bargainCourseUpdateREQ);
        }

        /**
         * BargainCourse删除接口
         * @param bargainCourseDeleteREQ
         * @return
         */
        @ApiOperation(value = "bargainCourse删除接口", notes = "bargainCourse删除接口")
        @RequestMapping(value = "/delete", method = RequestMethod.POST)
        public Result<Integer> delete(@RequestBody BargainCourseDeleteREQ bargainCourseDeleteREQ) {
            return biz.delete(bargainCourseDeleteREQ);
        }

        /**
         * BargainCourse查看接口
         * @param bargainCourseViewREQ
         * @return
         */
        @ApiOperation(value = "bargainCourse查看接口", notes = "bargainCourse查看接口")
        @RequestMapping(value = "/view", method = RequestMethod.POST)
        public Result<BargainCourseViewRESQ> view(@RequestBody BargainCourseViewREQ bargainCourseViewREQ) {
            return biz.view(bargainCourseViewREQ);
        }


    }

