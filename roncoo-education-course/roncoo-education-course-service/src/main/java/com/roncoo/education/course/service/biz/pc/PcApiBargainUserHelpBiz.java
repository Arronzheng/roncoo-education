package com.roncoo.education.course.service.biz.pc;

import com.roncoo.education.course.service.common.req.*;
import com.roncoo.education.course.service.common.resq.BargainUserHelpPageRESQ;
import com.roncoo.education.course.service.common.resq.BargainUserHelpViewRESQ;
import com.roncoo.education.course.service.dao.BargainUserHelpDao;
import com.roncoo.education.util.base.Page;
import com.roncoo.education.util.base.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 广告信息
 *
 */
@Component
public class PcApiBargainUserHelpBiz {

    @Autowired
    private BargainUserHelpDao dao;

    public Result<Page<BargainUserHelpPageRESQ>> list(BargainUserHelpPageREQ req) {

        return null;
    }

    /**
     *
     * 添加BargainUserHelp
     *
     * @param req
     * @return
     */
    public Result<Integer> save(BargainUserHelpSaveREQ req) {

        return null;
    }

    /**
     * 更新BargainUserHelp信息
     *
     * @param req
     * @return
     */
    public Result<Integer> update(BargainUserHelpUpdateREQ req) {

        return null;
    }

    /**
     * 删除BargainUserHelp信息
     *
     * @param req
     * @return
     */
    public Result<Integer> delete(BargainUserHelpDeleteREQ req) {

        return null;
    }

    /**
     * BargainUserHelp查看
     *
     * @param req
     * @return
     */
    public Result<BargainUserHelpViewRESQ> view(BargainUserHelpViewREQ req) {

        return null;
    }

}
