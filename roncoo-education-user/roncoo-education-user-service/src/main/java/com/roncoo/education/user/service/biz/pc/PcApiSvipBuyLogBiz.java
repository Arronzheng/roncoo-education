package com.roncoo.education.user.service.biz.pc;

import com.roncoo.education.user.service.common.req.*;
import com.roncoo.education.user.service.common.resq.SvipBuyLogPageRESQ;
import com.roncoo.education.user.service.common.resq.SvipBuyLogViewRESQ;
import com.roncoo.education.user.service.dao.SvipBuyLogDao;
import com.roncoo.education.user.service.dao.impl.mapper.entity.SvipBuyLog;
import com.roncoo.education.user.service.dao.impl.mapper.entity.SvipBuyLogExample;
import com.roncoo.education.util.base.Page;
import com.roncoo.education.util.base.PageUtil;
import com.roncoo.education.util.base.Result;
import com.roncoo.education.util.tools.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

/**
 * 广告信息
 *
 */
@Component
public class PcApiSvipBuyLogBiz {

    @Autowired
    private SvipBuyLogDao dao;

    public Result<Page<SvipBuyLogPageRESQ>> list(SvipBuyLogPageREQ req) {
        SvipBuyLogExample example = new SvipBuyLogExample();
        SvipBuyLogExample.Criteria c = example.createCriteria();
        if(StringUtils.hasText(req.getMobile())){
            c.andMobileLike(PageUtil.rightLike(req.getMobile()));
        }
        if(StringUtils.hasText(req.getNickname())){
            c.andNicknameLike(PageUtil.rightLike(req.getNickname()));
        }
        if(!StringUtils.isEmpty(req.getBeginGmtCreate()) && !StringUtils.isEmpty(req.getEndGmtCreate())){
            c.andStartTimeBetween(DateUtil.addDate(DateUtil.parseDate(req.getBeginGmtCreate(),"yyyy-MM-dd"),1), DateUtil.addDate(DateUtil.parseDate(req.getEndGmtCreate(),"yyyy-MM-dd"),1));
        }
        example.setOrderByClause(" start_time asc");
        Page<SvipBuyLog> page = dao.listForPage(req.getPageCurrent(), req.getPageSize(), example);
        return Result.success(PageUtil.transform(page, SvipBuyLogPageRESQ.class));
    }

    /**
     *
     * 添加SvipBuyLog
     *
     * @param req
     * @return
     */
    public Result<Integer> save(SvipBuyLogSaveREQ req) {

        return null;
    }

    /**
     * 更新SvipBuyLog信息
     *
     * @param req
     * @return
     */
    public Result<Integer> update(SvipBuyLogUpdateREQ req) {

        return null;
    }

    /**
     * 删除SvipBuyLog信息
     *
     * @param req
     * @return
     */
    public Result<Integer> delete(SvipBuyLogDeleteREQ req) {

        return null;
    }

    /**
     * SvipBuyLog查看
     *
     * @param req
     * @return
     */
    public Result<SvipBuyLogViewRESQ> view(SvipBuyLogViewREQ req) {

        return null;
    }

}