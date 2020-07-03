package com.roncoo.education.course.service.api.auth;

import com.roncoo.education.course.service.biz.auth.AuthApiCourseUserCollectionBiz;
import com.roncoo.education.course.service.common.bo.auth.AuthCourseUserCollectionCountBO;
import com.roncoo.education.course.service.common.bo.auth.AuthCourseUserCollectionDelBO;
import com.roncoo.education.course.service.common.bo.auth.AuthCourseUserCollectionPageBO;
import com.roncoo.education.course.service.common.bo.auth.AuthCourseUserCollectionSaveBO;
import com.roncoo.education.course.service.common.dto.auth.AuthCourseUserCollectionPageDTO;
import com.roncoo.education.util.base.BaseController;
import com.roncoo.education.util.base.Page;
import com.roncoo.education.util.base.Result;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/course/auth/course/user/collection/")
public class AuthApiCourseUserCollectionController extends BaseController {

    @Autowired
    private AuthApiCourseUserCollectionBiz biz;

    @ApiOperation(value = "分页列出接口", notes = "我的收藏分页列出接口")
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    public Result<Page<AuthCourseUserCollectionPageDTO>> listForPage(@RequestBody AuthCourseUserCollectionPageBO authCourseUserCollectionPageBO){
        return biz.listForPage(authCourseUserCollectionPageBO);
    }

    @ApiOperation(value = "添加接口", notes = "我的收藏添加接口")
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public Result<Integer> addCollection(@RequestBody AuthCourseUserCollectionSaveBO authCourseUserCollectionSaveBO){
        return biz.addCollection(authCourseUserCollectionSaveBO);
    }

    @ApiOperation(value = "删除接口", notes = "我的收藏删除接口")
    @RequestMapping(value = "/cancel", method = RequestMethod.POST)
    public Result<Integer> cancelCollection(@RequestBody AuthCourseUserCollectionDelBO authCourseUserCollectionDelBO){
        return biz.cancelCollection(authCourseUserCollectionDelBO);
    }

    @ApiOperation(value = "统计接口", notes = "我的收藏统计接口")
    @RequestMapping(value = "/count", method = RequestMethod.POST)
    public Result<Integer> countCollection(@RequestBody AuthCourseUserCollectionCountBO authCourseUserCollectionCountBO){
        return biz.countCollection(authCourseUserCollectionCountBO);
    }
}
