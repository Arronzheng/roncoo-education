package com.roncoo.education.course.service.api.auth;

import com.roncoo.education.course.service.biz.auth.AuthCourseCommentBiz;
import com.roncoo.education.course.service.common.dto.CourseViewDTO;
import com.roncoo.education.course.service.common.req.*;
import com.roncoo.education.course.service.common.resq.CourseCommentViewRESQ;
import com.roncoo.education.util.base.BaseController;
import com.roncoo.education.util.base.Result;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *  前端控制器
 *
 * @since 2019-12-05
 */
@RestController
@RequestMapping("/course/auth/courseComment")
    public class AuthCourseCommentController extends BaseController {

        @Autowired
        private AuthCourseCommentBiz biz;

        /**
        * CourseComment添加接口
        * @param courseCommentSaveREQ
        * @return
        */
        @ApiOperation(value = "courseComment添加接口", notes = "courseComment添加接口")
        @RequestMapping(value = "/save", method = RequestMethod.POST)
        public Result<CourseViewDTO> save(@RequestBody CourseCommentSaveREQ courseCommentSaveREQ) {
            return biz.save(courseCommentSaveREQ);
        }

        /**
         * CourseComment更新接口
         * @param courseCommentUpdateREQ
         * @return
         */
        @ApiOperation(value = "courseComment更新接口", notes = "courseComment更新接口")
        @RequestMapping(value = "/update", method = RequestMethod.POST)
        public Result<Integer> update(@RequestBody CourseCommentUpdateREQ courseCommentUpdateREQ) {
            return biz.update(courseCommentUpdateREQ);
        }

        /**
         * CourseComment删除接口
         * @param courseCommentDeleteREQ
         * @return
         */
        @ApiOperation(value = "courseComment删除接口", notes = "courseComment删除接口")
        @RequestMapping(value = "/delete", method = RequestMethod.POST)
        public Result<CourseViewDTO> delete(@RequestBody CourseCommentDeleteREQ courseCommentDeleteREQ) {
            return biz.delete(courseCommentDeleteREQ);
        }

        /**
         * CourseComment查看接口
         * @param courseCommentViewREQ
         * @return
         */
        @ApiOperation(value = "courseComment查看接口", notes = "courseComment查看接口")
        @RequestMapping(value = "/view", method = RequestMethod.POST)
        public Result<CourseCommentViewRESQ> view(@RequestBody CourseCommentViewREQ courseCommentViewREQ) {
            return biz.view(courseCommentViewREQ);
        }


    }

