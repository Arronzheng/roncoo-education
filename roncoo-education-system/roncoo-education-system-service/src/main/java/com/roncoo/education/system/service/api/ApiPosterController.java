package com.roncoo.education.system.service.api;

import com.roncoo.education.system.service.biz.ApiPosterBiz;
import com.roncoo.education.system.service.biz.pc.PcApiPosterBiz;
import com.roncoo.education.system.service.common.bo.PosterBO;
import com.roncoo.education.system.service.common.dto.PosterDTO;
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
@RequestMapping("/system/api/poster")
    public class ApiPosterController extends BaseController {

        @Autowired
        private ApiPosterBiz biz;

        /**
         * Poster查询接口
         * params PosterBO
         * @return
         */
        @ApiOperation(value = "poster查看接口", notes = "poster查看接口")
        @RequestMapping(value = "/getByPosterType", method = RequestMethod.POST)
        public Result<PosterDTO> getByPosterType(@RequestBody PosterBO posterBO) {
            return biz.getByPosterType(posterBO);
        }


    }

