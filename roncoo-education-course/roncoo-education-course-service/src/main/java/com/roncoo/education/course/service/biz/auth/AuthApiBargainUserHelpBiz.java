package com.roncoo.education.course.service.biz.auth;

import com.roncoo.education.course.service.common.bo.BargainUserHelpSaveBO;
import com.roncoo.education.course.service.dao.BargainUserHelpDao;
import com.roncoo.education.course.service.dao.impl.mapper.entity.BargainUserHelp;
import com.roncoo.education.util.base.Result;
import com.roncoo.education.util.enums.ResultEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

/**
 * @description:
 * @author: husend
 * @time: 2020/5/14
 */
@Component
public class AuthApiBargainUserHelpBiz {
    @Autowired
    private BargainUserHelpDao dao;

    public Result<Boolean> getBargainHelpByUid(BargainUserHelpSaveBO bargainUserHelpSaveBO) {
        BargainUserHelp bargainUserHelp = dao.getByUidAndBargainUserId(bargainUserHelpSaveBO.getUid(), bargainUserHelpSaveBO.getBargainUserId());
        if (!StringUtils.isEmpty(bargainUserHelp)) {
            return Result.success(true);
        } else {
            return Result.success(false);
        }
    }
}
