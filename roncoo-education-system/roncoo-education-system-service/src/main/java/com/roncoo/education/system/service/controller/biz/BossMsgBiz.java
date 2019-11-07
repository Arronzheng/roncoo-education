package com.roncoo.education.system.service.controller.biz;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.aliyun.oas.utils.StringUtil;
import com.roncoo.education.system.common.bean.qo.MsgQO;
import com.roncoo.education.system.common.bean.vo.MsgPushVO;
import com.roncoo.education.system.common.bean.vo.MsgVO;
import com.roncoo.education.system.service.common.CacheRedis;
import com.roncoo.education.system.service.dao.MsgDao;
import com.roncoo.education.system.service.dao.MsgUserDao;
import com.roncoo.education.system.service.dao.impl.mapper.entity.Msg;
import com.roncoo.education.system.service.dao.impl.mapper.entity.MsgExample;
import com.roncoo.education.system.service.dao.impl.mapper.entity.MsgExample.Criteria;
import com.roncoo.education.system.service.dao.impl.mapper.entity.MsgUser;
import com.roncoo.education.user.common.bean.vo.UserExtMsgVO;
import com.roncoo.education.user.feign.IBossUserExt;
import com.roncoo.education.util.base.BaseBiz;
import com.roncoo.education.util.base.BaseException;
import com.roncoo.education.util.base.Page;
import com.roncoo.education.util.base.PageUtil;
import com.roncoo.education.util.enums.HasNoticeEnum;
import com.roncoo.education.util.enums.IsSendEnum;
import com.roncoo.education.util.enums.IsTimeSendEnum;
import com.roncoo.education.util.enums.RedisPreEnum;
import com.roncoo.education.util.enums.StatusIdEnum;
import com.roncoo.education.util.tools.ArrayListUtil;
import com.roncoo.education.util.tools.BeanUtil;
import com.roncoo.education.util.tools.Constants;
import com.xiaoleilu.hutool.util.CollectionUtil;

/**
 * 站内信息表
 *
 * @author wuyun
 */
@Component
public class BossMsgBiz extends BaseBiz {

	@Autowired
	private MsgDao dao;
	@Autowired
	private MsgUserDao msgUserDao;

	@Autowired
	private IBossUserExt bossUserExt;

	@Autowired
	private CacheRedis cacheRedis;

	public Page<MsgVO> listForPage(MsgQO qo) {
		MsgExample example = new MsgExample();
		Criteria c = example.createCriteria();
		if (qo.getStatusId() != null) {
			c.andStatusIdEqualTo(qo.getStatusId());
		} else {
			c.andStatusIdLessThan(Constants.FREEZE);
		}
		if (StringUtil.isNotEmpty(qo.getMsgTitle())) {
			c.andMsgTitleLike(PageUtil.rightLike(qo.getMsgTitle()));
		}
		example.setOrderByClause(" status_id desc, sort desc, id desc ");
		Page<Msg> page = dao.listForPage(qo.getPageCurrent(), qo.getPageSize(), example);
		return PageUtil.transform(page, MsgVO.class);
	}

	public int save(MsgQO qo) {
		Msg record = BeanUtil.copyProperties(qo, Msg.class);
		return dao.save(record);
	}

	@Transactional
	public int deleteById(Long id) {
		msgUserDao.deleteByMsgId(id);
		int result = dao.deleteById(id);
		if (result < 1) {
			throw new BaseException("更新表失败");
		}
		return result;
	}

	public MsgVO getById(Long id) {
		Msg record = dao.getById(id);
		return BeanUtil.copyProperties(record, MsgVO.class);
	}

	public int updateById(MsgQO qo) {
		Msg record = BeanUtil.copyProperties(qo, Msg.class);
		return dao.updateById(record);
	}

	@Transactional
	public int pushByManual(Long id) {
		// 获得模板
		Msg msg = dao.getById(id);
		if (msg == null) {
			throw new BaseException("查找msg失败");
		}
		final MsgPushVO msgPush = BeanUtil.copyProperties(msg, MsgPushVO.class);
		// 刷新站内信
		updateMsg(id);
		callbackExecutor.execute(new Runnable() {
			@Override
			public void run() {
				pushToUserByMsgPush(msgPush);
			}
		});
		return 1;
	}

	private void pushToUserByMsgPush(MsgPushVO msgPush) {
		// 获取缓存的条数
		int num = getCacheNum();
		for (int i = 1; i < num + 1; i++) {
			List<UserExtMsgVO> list = cacheRedis.list(RedisPreEnum.SYS_MSG_SEND.getCode() + "_" + i, UserExtMsgVO.class);
			if (CollectionUtil.isNotEmpty(list)) {
				// 批量生成
				for (UserExtMsgVO vo : list) {
					saveMsgUser(msgPush, vo);
				}
			}
		}

	}

	private int getCacheNum() {
		boolean flag = cacheRedis.hasKey(RedisPreEnum.SYS_MSG_SEND_NUM.getCode());
		if (!flag) {// 找不到，去缓存用户信息
			bossUserExt.cachUserForMsg();
		}
		int num = cacheRedis.get(RedisPreEnum.SYS_MSG_SEND_NUM.getCode(), int.class);
		return num;
	}

	private void updateMsg(Long id) {
		Msg msgNew = new Msg();
		msgNew.setId(id);
		msgNew.setIsSend(HasNoticeEnum.YES.getCode());
		dao.updateById(msgNew);
	}

	private void saveMsgUser(MsgPushVO msg, UserExtMsgVO vo) {
		MsgUser record = new MsgUser();
		record.setStatusId(StatusIdEnum.YES.getCode());
		record.setMsgId(msg.getId());
		record.setMsgTitle(msg.getMsgTitle());
		record.setUserNo(vo.getUserNo());
		record.setMobile(vo.getMobile());
		msgUserDao.save(record);
	}

	/**
	 * 定时器任务：推送站内信到用户
	 * 
	 * @return
	 */
	@Transactional
	public int push() {
		List<Msg> list = dao.listByStatusIdAndIsSendAndIsTimeSendAndSendTime(StatusIdEnum.YES.getCode(), IsSendEnum.NO.getCode(), IsTimeSendEnum.YES.getCode(), new Date());
		List<MsgPushVO> msgList = ArrayListUtil.copy(list, MsgPushVO.class);
		if (CollectionUtil.isNotEmpty(msgList)) {
			for (MsgPushVO vo : msgList) {
				// 进行推送前，将当前站内信推送状态置为已通知
				updateMsg(vo.getId());

				callbackExecutor.execute(new Runnable() {
					@Override
					public void run() {
						pushToUserByMsgPush(vo);
					}
				});
			}
			return 1;
		}
		return 0;
	}

}
