package com.roncoo.education.user.service.dao.impl;

import java.util.List;
import java.util.Map;

import com.roncoo.education.user.service.common.req.UserExtPageREQ;
import com.roncoo.education.user.service.common.resq.UserExtPageRESQ;
import com.roncoo.education.util.tools.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import com.roncoo.education.user.service.dao.UserExtDao;
import com.roncoo.education.user.service.dao.impl.mapper.UserExtMapper;
import com.roncoo.education.user.service.dao.impl.mapper.entity.UserExt;
import com.roncoo.education.user.service.dao.impl.mapper.entity.UserExtExample;
import com.roncoo.education.user.service.dao.impl.mapper.entity.UserExtExample.Criteria;
import com.roncoo.education.util.base.Page;
import com.roncoo.education.util.base.PageUtil;
import com.roncoo.education.util.tools.IdWorker;

@Repository
public class UserExtDaoImpl implements UserExtDao {
	@Autowired
	private UserExtMapper userExtMapper;

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public int save(UserExt record) {
		record.setId(IdWorker.getId());
		return this.userExtMapper.insertSelective(record);
	}

	@Override
	public int deleteById(Long id) {
		return this.userExtMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int updateById(UserExt record) {
		return this.userExtMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public UserExt getById(Long id) {
		return this.userExtMapper.selectByPrimaryKey(id);
	}

	@Override
	public Page<UserExtPageRESQ> listForPage(UserExtPageREQ req) {
		UserExtExample example = new UserExtExample();
		Criteria c = example.createCriteria();
		if (StringUtils.hasText(req.getMobile())) {
			c.andMobileLike(PageUtil.rightLike(req.getMobile()));
		}
		if (StringUtils.hasText(req.getNickname())) {
			c.andNicknameLike(PageUtil.like(req.getNickname()));
		}
		if (req.getStatusId() != null) {
			c.andStatusIdEqualTo(req.getStatusId());
		}
		if (StringUtils.hasText(req.getBeginGmtCreate())) {
			req.setBeginGmtCreate(DateUtil.formatDate(DateUtil.addDate(DateUtil.parseDate(req.getBeginGmtCreate(), "yyyy-MM-dd"),1)));
			c.andGmtCreateGreaterThanOrEqualTo(DateUtil.parseDate(req.getBeginGmtCreate()));
		}
		if (StringUtils.hasText(req.getEndGmtCreate())) {
			req.setEndGmtCreate(DateUtil.formatDate(DateUtil.addDate(DateUtil.parseDate(req.getEndGmtCreate(), "yyyy-MM-dd"), 1)));
			c.andGmtCreateLessThanOrEqualTo(DateUtil.parseDate(req.getEndGmtCreate()));
		}
		example.setOrderByClause(" status_id desc, id desc ");
		int count = this.userExtMapper.countByExample(example);
		int pageSize = PageUtil.checkPageSize(req.getPageSize());
		int pageCurrent = PageUtil.checkPageCurrent(count, req.getPageSize(), req.getPageCurrent());
		int totalPage = PageUtil.countTotalPage(count, req.getPageSize());
		req.setLimitStart(PageUtil.countOffset(pageCurrent, pageSize));
		List<UserExtPageRESQ> userRESQ = this.userExtMapper.selectUnionSvip(req);
		return new Page<UserExtPageRESQ>(count, totalPage, pageCurrent, pageSize, userRESQ);
	}

	@Override
	public UserExt getByUserNo(Long userNo) {
		UserExtExample example = new UserExtExample();
		Criteria criteria = example.createCriteria();
		criteria.andUserNoEqualTo(userNo);
		List<UserExt> list = this.userExtMapper.selectByExample(example);
		if (list.isEmpty()) {
			return null;
		}
		return list.get(0);
	}

	@Override
	public int updateByUserNo(UserExt record) {
		UserExtExample example = new UserExtExample();
		example.createCriteria().andUserNoEqualTo(record.getUserNo());
		return this.userExtMapper.updateByExampleSelective(record, example);
	}

	@Override
	public UserExt getByMobile(String mobile) {
		UserExtExample example = new UserExtExample();
		Criteria c = example.createCriteria();
		c.andMobileEqualTo(mobile);
		List<UserExt> list = this.userExtMapper.selectByExample(example);
		if (list.size() < 0 || list.isEmpty()) {
			return null;
		}
		return list.get(0);
	}

	/**
	 * 获取用户注册量
	 * 
	 */
	@Override
	public Integer sumByCountOrders(String date) {
		StringBuilder builder = new StringBuilder();
		builder.append("select count(*) as count from user_ext where");
		builder.append(" status_id = 1 ");
		builder.append("and gmt_create >= '").append(date).append(" 00:00:00' ");
		builder.append("and gmt_create <= '").append(date).append(" 23:59:59'");
		String sql = builder.toString();
		Integer count = 0;
		Map<String, Object> map = jdbcTemplate.queryForMap(sql);
		if (!StringUtils.isEmpty(map.get("count"))) {
			count = Integer.valueOf(String.valueOf(map.get("count")));
		}
		return count;
	}
}