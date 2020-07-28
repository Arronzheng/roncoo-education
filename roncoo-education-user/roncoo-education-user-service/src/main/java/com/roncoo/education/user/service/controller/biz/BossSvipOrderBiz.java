package com.roncoo.education.user.service.controller.biz;

import com.alipay.api.response.AlipayTradePrecreateResponse;
import com.alipay.api.response.AlipayTradeQueryResponse;
import com.roncoo.education.system.common.bean.vo.SysVO;
import com.roncoo.education.system.common.bean.vo.WebsiteVO;
import com.roncoo.education.system.feign.IBossSys;
import com.roncoo.education.system.feign.IBossWebsite;
import com.roncoo.education.user.common.bean.vo.SvipOrderVO;
import com.roncoo.education.user.service.common.bo.auth.AuthSvipOrderBO;
import com.roncoo.education.user.service.common.bo.auth.AuthSvipOrderViewBO;
import com.roncoo.education.user.service.common.dto.auth.AuthSvipOrderDTO;
import com.roncoo.education.user.service.dao.SvipDao;
import com.roncoo.education.user.service.dao.SvipOrderDao;
import com.roncoo.education.user.service.dao.UserExtDao;
import com.roncoo.education.user.service.dao.impl.mapper.entity.SvipOrder;
import com.roncoo.education.user.service.dao.impl.mapper.entity.UserExt;
import com.roncoo.education.util.base.BaseBiz;
import com.roncoo.education.util.base.BaseException;
import com.roncoo.education.util.base.Result;
import com.roncoo.education.util.enums.*;
import com.roncoo.education.util.pay.AlipayUtil;
import com.roncoo.education.util.pay.WeixinPayUtil;
import com.roncoo.education.util.tools.BeanUtil;
import com.roncoo.education.util.tools.NOUtil;
import com.xiaoleilu.hutool.util.ObjectUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;

/**
 * 订单信息表
 *
 *
 */
@Component
public class BossSvipOrderBiz extends BaseBiz {

	@Autowired
	private SvipOrderDao dao;

    public SvipOrderVO getBySerialNo(Long serialNo) {
		return BeanUtil.copyProperties(dao.getBySerialNo(serialNo), SvipOrderVO.class);
    }

	public int updateById(SvipOrderVO record) {
		SvipOrder vipOrder = BeanUtil.copyProperties(record, SvipOrder.class);
		return dao.updateById(vipOrder);
	}
}
