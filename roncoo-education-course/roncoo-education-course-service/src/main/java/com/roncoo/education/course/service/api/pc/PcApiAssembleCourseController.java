package com.roncoo.education.course.service.api.pc;

import com.roncoo.education.course.service.biz.pc.PcApiAssembleCourseBiz;
import com.roncoo.education.course.service.common.req.*;
import com.roncoo.education.course.service.common.resq.AssembleCoursePageRESQ;
import com.roncoo.education.course.service.common.resq.AssembleCourseViewRESQ;
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
 * 拼团产品表 前端控制器
 *
 * @author husend
 * @since 2020-04-14
 */
@RestController
@RequestMapping("/course/pc/assemble/course")
    public class PcApiAssembleCourseController extends BaseController {

        @Autowired
        private PcApiAssembleCourseBiz biz;

        /**
         * AssembleCourse分页列表接口
         * @param assembleCoursePageREQ   分页信息
         * @return
         */
        @ApiOperation(value = "assembleCourse分页列表接口", notes = "assembleCourse分页列表接口")
        @RequestMapping(value = "/list", method = RequestMethod.POST)
        public Result<Page<AssembleCoursePageRESQ>> list(@RequestBody AssembleCoursePageREQ assembleCoursePageREQ) {
            return biz.list(assembleCoursePageREQ);
        }

        /**
        * AssembleCourse添加接口
        * @param assembleCourseSaveREQ
        * @return
        */
        @ApiOperation(value = "assembleCourse添加接口", notes = "assembleCourse添加接口")
        @RequestMapping(value = "/save", method = RequestMethod.POST)
        public Result<Integer> save(@RequestBody AssembleCourseSaveREQ assembleCourseSaveREQ) {
            return biz.save(assembleCourseSaveREQ);
        }

        /**
         * AssembleCourse更新接口
         * @param assembleCourseUpdateREQ
         * @return
         */
        @ApiOperation(value = "assembleCourse更新接口", notes = "assembleCourse更新接口")
        @RequestMapping(value = "/update", method = RequestMethod.POST)
        public Result<Integer> update(@RequestBody AssembleCourseUpdateREQ assembleCourseUpdateREQ) {
            return biz.update(assembleCourseUpdateREQ);
        }

        /**
         * AssembleCourse删除接口
         * @param assembleCourseDeleteREQ
         * @return
         */
        @ApiOperation(value = "assembleCourse删除接口", notes = "assembleCourse删除接口")
        @RequestMapping(value = "/delete", method = RequestMethod.POST)
        public Result<Integer> delete(@RequestBody AssembleCourseDeleteREQ assembleCourseDeleteREQ) {
            return biz.delete(assembleCourseDeleteREQ);
        }

        /**
         * AssembleCourse查看接口
         * @param assembleCourseViewREQ
         * @return
         */
        @ApiOperation(value = "assembleCourse查看接口", notes = "assembleCourse查看接口")
        @RequestMapping(value = "/view", method = RequestMethod.POST)
        public Result<AssembleCourseViewRESQ> view(@RequestBody AssembleCourseViewREQ assembleCourseViewREQ) {
            return biz.view(assembleCourseViewREQ);
        }


    }

