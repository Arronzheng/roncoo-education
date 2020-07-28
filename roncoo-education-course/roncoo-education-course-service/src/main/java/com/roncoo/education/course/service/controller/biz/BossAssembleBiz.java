package com.roncoo.education.course.service.controller.biz;

import com.roncoo.education.course.service.dao.AssembleDao;
import com.roncoo.education.course.service.dao.OrderInfoDao;
import com.roncoo.education.course.service.dao.OrderPayDao;
import com.roncoo.education.course.service.dao.impl.mapper.entity.Assemble;
import com.roncoo.education.course.service.dao.impl.mapper.entity.AssembleExample;
import com.roncoo.education.course.service.dao.impl.mapper.entity.OrderInfo;
import com.roncoo.education.course.service.dao.impl.mapper.entity.OrderPay;
import com.roncoo.education.util.base.Page;
import com.roncoo.education.util.pay.WeixinPayUtil;
import com.xiaoleilu.hutool.util.CollectionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @description:
 * @author: husend
 * @time: 2020/6/13
 */
@Component
public class BossAssembleBiz {

    @Autowired
    private AssembleDao dao;
    @Autowired
    private OrderPayDao orderPayDao;
    @Autowired
    private OrderInfoDao orderInfoDao;
    /**
     * 24小时后如果拼团不成功，就标记拼团失败，每次处理50条数据
     *
     * @return
     */
    public int handleScheduledTasks() throws Exception {
        // 1.拼团信息的处理，将支付成功状态为1的修改成拼团失败并退款
        AssembleExample example = new AssembleExample();
        AssembleExample.Criteria c = example.createCriteria();
        c.andStatusEqualTo(1);
        c.andStopTimeLessThanOrEqualTo(new Date(System.currentTimeMillis()));
        example.setOrderByClause(" id desc ");
        Page<Assemble> page = dao.listForPage(1, 50, example);
        if (CollectionUtil.isNotEmpty(page.getList())) {
            for (Assemble assemble : page.getList()) {
                Assemble argAssemble = new Assemble();
                argAssemble.setId(assemble.getId());
                argAssemble.setStatus(3);
                dao.updateById(argAssemble);
                // 退款
                OrderInfo orderInfo = orderInfoDao.getById(assemble.getOrderId());
                OrderPay orderPay = orderPayDao.getByOrderNo(orderInfo.getOrderNo());
                WeixinPayUtil.refund(orderPay.getSerialNumber().toString(), orderInfo.getPricePaid(), orderInfo.getPricePaid());
            }
        }
        return 1;
    }
}
