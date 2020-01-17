package com.roncoo.education.course.service.api.pc;

import com.roncoo.education.course.service.biz.pc.PcApiCourseUserCollectionBiz;
import com.roncoo.education.course.service.common.req.*;
import com.roncoo.education.course.service.common.resq.CourseUserCollectionPageRESQ;
import com.roncoo.education.course.service.common.resq.CourseUserCollectionViewRESQ;
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
 *  前端控制器
 *
 * @author ${author}
 * @since 2020-01-04
 */
@RestController
@RequestMapping("/course/courseUserCollection")
    public class PcApiCourseUserCollectionController extends BaseController {

        @Autowired
        private PcApiCourseUserCollectionBiz biz;

        /**
         * CourseUserCollection分页列表接口
         * @param courseUserCollectionPageREQ   分页信息
         * @return
         */
        @ApiOperation(value = "courseUserCollection分页列表接口", notes = "courseUserCollection分页列表接口")
        @RequestMapping(value = "/list", method = RequestMethod.POST)
        public Result<Page<CourseUserCollectionPageRESQ>> list(@RequestBody CourseUserCollectionPageREQ courseUserCollectionPageREQ) {
            return biz.list(courseUserCollectionPageREQ);
        }

        /**
        * CourseUserCollection添加接口
        * @param courseUserCollectionSaveREQ
        * @return
        */
        @ApiOperation(value = "courseUserCollection添加接口", notes = "courseUserCollection添加接口")
        @RequestMapping(value = "/save", method = RequestMethod.POST)
        public Result<Integer> save(@RequestBody CourseUserCollectionSaveREQ courseUserCollectionSaveREQ) {
            return biz.save(courseUserCollectionSaveREQ);
        }

        /**
         * CourseUserCollection更新接口
         * @param courseUserCollectionUpdateREQ
         * @return
         */
        @ApiOperation(value = "courseUserCollection更新接口", notes = "courseUserCollection更新接口")
        @RequestMapping(value = "/update", method = RequestMethod.POST)
        public Result<Integer> update(@RequestBody CourseUserCollectionUpdateREQ courseUserCollectionUpdateREQ) {
            return biz.update(courseUserCollectionUpdateREQ);
        }

        /**
         * CourseUserCollection删除接口
         * @param courseUserCollectionDeleteREQ
         * @return
         */
        @ApiOperation(value = "courseUserCollection删除接口", notes = "courseUserCollection删除接口")
        @RequestMapping(value = "/delete", method = RequestMethod.POST)
        public Result<Integer> delete(@RequestBody CourseUserCollectionDeleteREQ courseUserCollectionDeleteREQ) {
            return biz.delete(courseUserCollectionDeleteREQ);
        }

        /**
         * CourseUserCollection查看接口
         * @param courseUserCollectionViewREQ
         * @return
         */
        @ApiOperation(value = "courseUserCollection查看接口", notes = "courseUserCollection查看接口")
        @RequestMapping(value = "/view", method = RequestMethod.POST)
        public Result<CourseUserCollectionViewRESQ> view(@RequestBody CourseUserCollectionViewREQ courseUserCollectionViewREQ) {
            return biz.view(courseUserCollectionViewREQ);
        }


    }

