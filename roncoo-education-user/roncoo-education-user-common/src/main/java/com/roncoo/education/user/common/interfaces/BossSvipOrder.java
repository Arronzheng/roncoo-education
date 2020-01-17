package com.roncoo.education.user.common.interfaces;

import com.roncoo.education.user.common.bean.qo.SvipOrderQO;
import com.roncoo.education.user.common.bean.vo.SvipOrderVO;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

public interface BossSvipOrder {

    @RequestMapping(value = "/boss/user/vipOrder/getBySerialNo/{serialNo}", method = RequestMethod.GET)
    SvipOrderVO getBySerialNo(@PathVariable(value = "serialNo") Long serialNo);

    @RequestMapping(value = "/boss/user/vipOrder/updateById", method = RequestMethod.PUT)
    int updateById(SvipOrderVO record);
}
