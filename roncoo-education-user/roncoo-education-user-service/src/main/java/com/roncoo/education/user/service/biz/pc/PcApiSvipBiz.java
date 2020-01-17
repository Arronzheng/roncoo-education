package com.roncoo.education.user.service.biz.pc;

import com.roncoo.education.user.service.common.req.*;
import com.roncoo.education.user.service.common.resq.SvipPageRESQ;
import com.roncoo.education.user.service.common.resq.SvipViewRESQ;
import com.roncoo.education.user.service.dao.SvipDao;
import com.roncoo.education.user.service.dao.impl.mapper.entity.SvipExample;
import com.roncoo.education.util.base.Page;
import com.roncoo.education.util.base.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

/**
 * 广告信息
 *
 */
@Component
public class PcApiSvipBiz {

    @Autowired
    private SvipDao dao;

    public Result<Page<SvipPageRESQ>> listForPage(SvipPageREQ req) {

        return null;
    }

    /**
     *
     * 添加Svip
     *
     * @param req
     * @return
     */
    public Result<Integer> save(SvipSaveREQ req) {

        return null;
    }

    /**
     * 更新Svip信息
     *
     * @param req
     * @return
     */
    public Result<Integer> update(SvipUpdateREQ req) {

        return null;
    }

    /**
     * 删除Svip信息
     *
     * @param req
     * @return
     */
    public Result<Integer> delete(SvipDeleteREQ req) {

        return null;
    }

    /**
     * Svip查看
     *
     * @param req
     * @return
     */
    public Result<SvipViewRESQ> view(SvipViewREQ req) {

        return null;
    }

}