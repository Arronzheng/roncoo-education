package com.roncoo.education.course.service.controller.biz;

import com.roncoo.education.course.service.dao.BargainUserDao;
import com.roncoo.education.course.service.dao.impl.mapper.entity.BargainUser;
import com.roncoo.education.course.service.dao.impl.mapper.entity.BargainUserExample;
import com.roncoo.education.util.base.Page;
import com.xiaoleilu.hutool.util.CollectionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @description:
 * @author: husend
 * @time: 2020/6/13
 */
@Component
public class BossBargainBiz {

    @Autowired
    private BargainUserDao dao;

    /**
     * 24小时后如果砍价不成功，就标记砍价失败，每次处理50条数据
     *
     * @return
     */
    public int handleScheduledTasks() {
        // 1.砍价信息的处理
        BargainUserExample example = new BargainUserExample();
        BargainUserExample.Criteria c = example.createCriteria();
        c.andStatusEqualTo(1);
        c.andAddTimeLessThan(new Date(System.currentTimeMillis() - 86400000L));
        example.setOrderByClause(" id desc ");
        Page<BargainUser> page = dao.listForPage(1, 50, example);
        if (CollectionUtil.isNotEmpty(page.getList())) {
            for (BargainUser bargainUser : page.getList()) {
                BargainUser argBargainUser = new BargainUser();
                argBargainUser.setId(bargainUser.getId());
                argBargainUser.setStatus(2);
                dao.updateById(argBargainUser);
            }
        }
        return 1;
    }
}
