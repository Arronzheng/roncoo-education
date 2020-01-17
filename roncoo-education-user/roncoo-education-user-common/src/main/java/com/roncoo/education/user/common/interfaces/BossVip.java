package com.roncoo.education.user.common.interfaces;

import com.roncoo.education.user.common.bean.qo.SvipQO;
import com.roncoo.education.user.common.bean.vo.SvipVO;
import com.roncoo.education.util.base.Page;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 会员信息
 */
public interface BossVip {

    @RequestMapping(value = "/boss/user/vip/save", method = RequestMethod.POST)
    int save(SvipQO record);

    @RequestMapping(value = "/boss/user/vip/deleteById/{id}", method = RequestMethod.DELETE)
    int deleteById(@PathVariable(value = "id") Long id);

    @RequestMapping(value = "/boss/user/vip/updateById", method = RequestMethod.PUT)
    int updateById(SvipQO record);

    @RequestMapping(value = "/boss/user/vip/getById/{id}", method = RequestMethod.GET)
    SvipVO getById(@PathVariable(value = "id") Long id);

    @RequestMapping(value = "/boss/user/vip/listForPage", method = RequestMethod.POST)
    Page<SvipVO> listForPage(SvipQO record);

    @RequestMapping(value = "/boss/user/vip/getByUserNo/{userNo}", method = RequestMethod.GET)
    SvipVO getByUserNo(@PathVariable(value = "userNo") Long userNo);
}
