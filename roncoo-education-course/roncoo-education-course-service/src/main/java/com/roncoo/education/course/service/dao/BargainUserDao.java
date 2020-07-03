
package com.roncoo.education.course.service.dao;

import com.roncoo.education.course.service.dao.impl.mapper.entity.BargainUser;
import com.roncoo.education.course.service.dao.impl.mapper.entity.BargainUserExample;
import com.roncoo.education.util.base.Page;

import java.util.List;

/**
 * 用户参与砍价表 服务类
 *
 * @author husend
 * @since 2020-04-14
 */
public interface BargainUserDao {
            int save(BargainUser record);

            int deleteById(Long id);

            int updateById(BargainUser record);

            BargainUser getById(Long id);

            Page<BargainUser> listForPage(int pageCurrent, int pageSize, BargainUserExample example);

            List<BargainUser> getByBargainIdAndUidAndStatus(Long bargainId, Long uid, int i);

            List<BargainUser> getByUid(Long userNo);
}
