package com.roncoo.education.user.service.controller.biz;

import com.roncoo.education.user.common.bean.qo.SvipQO;
import com.roncoo.education.user.common.bean.vo.SvipVO;
import com.roncoo.education.user.service.dao.SvipDao;
import com.roncoo.education.user.service.dao.impl.mapper.entity.Svip;
import com.roncoo.education.util.tools.BeanUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 用户基本信息
 */
@Component
public class BossVipBiz {

	@Autowired
	private SvipDao dao;


	public int save(SvipQO record) {
		Svip vip = BeanUtil.copyProperties(record, Svip.class);
		return dao.save(vip);
	}

	public int delete(Long id) {
		return dao.deleteById(id);
	}

	public int updateById(SvipQO record) {
		Svip vip = BeanUtil.copyProperties(record, Svip.class);
		return dao.updateById(vip);
	}

	public SvipVO getByUserNo(Long userNo) {
		return BeanUtil.copyProperties(dao.getByUserNo(userNo), SvipVO.class);
	}
}
