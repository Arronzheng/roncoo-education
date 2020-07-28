package com.roncoo.education.system.service.api.pc;

import com.roncoo.education.system.service.biz.pc.PcApiPosterBiz;
import com.roncoo.education.system.service.common.req.*;
import com.roncoo.education.system.service.common.resq.PosterPageRESQ;
import com.roncoo.education.system.service.common.resq.PosterViewRESQ;
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
 * @author husend
 * @since 2020-07-25
 */
@RestController
@RequestMapping("/system/pc/poster")
    public class PcApiPosterController extends BaseController {

        @Autowired
        private PcApiPosterBiz biz;

        /**
         * Poster分页列表接口
         * @param posterPageREQ   分页信息
         * @return
         */
        @ApiOperation(value = "poster分页列表接口", notes = "poster分页列表接口")
        @RequestMapping(value = "/list", method = RequestMethod.POST)
        public Result<Page<PosterPageRESQ>> list(@RequestBody PosterPageREQ posterPageREQ) {
            return biz.list(posterPageREQ);
        }

        /**
        * Poster添加接口
        * @param posterSaveREQ
        * @return
        */
        @ApiOperation(value = "poster添加接口", notes = "poster添加接口")
        @RequestMapping(value = "/save", method = RequestMethod.POST)
        public Result<Integer> save(@RequestBody PosterSaveREQ posterSaveREQ) {
            return biz.save(posterSaveREQ);
        }

        /**
         * Poster更新接口
         * @param posterUpdateREQ
         * @return
         */
        @ApiOperation(value = "poster更新接口", notes = "poster更新接口")
        @RequestMapping(value = "/update", method = RequestMethod.POST)
        public Result<Integer> update(@RequestBody PosterUpdateREQ posterUpdateREQ) {
            return biz.update(posterUpdateREQ);
        }

        /**
         * Poster删除接口
         * @param posterDeleteREQ
         * @return
         */
        @ApiOperation(value = "poster删除接口", notes = "poster删除接口")
        @RequestMapping(value = "/delete", method = RequestMethod.POST)
        public Result<Integer> delete(@RequestBody PosterDeleteREQ posterDeleteREQ) {
            return biz.delete(posterDeleteREQ);
        }

        /**
         * Poster查看接口
         * @param posterViewREQ
         * @return
         */
        @ApiOperation(value = "poster查看接口", notes = "poster查看接口")
        @RequestMapping(value = "/view", method = RequestMethod.POST)
        public Result<PosterViewRESQ> view(@RequestBody PosterViewREQ posterViewREQ) {
            return biz.view(posterViewREQ);
        }


    }

