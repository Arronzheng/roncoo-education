
package com.roncoo.education.course.service.dao;

import com.roncoo.education.course.service.dao.impl.mapper.entity.BargainUserHelp;
import com.roncoo.education.course.service.dao.impl.mapper.entity.BargainUserHelpExample;
import com.roncoo.education.util.base.Page;

import java.util.List;

/**
 * 砍价用户帮助表 服务类
 *
 * @author husend
 * @since 2020-04-14
 */
public interface BargainUserHelpDao {
            int save(BargainUserHelp record);

            int deleteById(Long id);

            int updateById(BargainUserHelp record);

            BargainUserHelp getById(Long id);

            Page<BargainUserHelp> listForPage(int pageCurrent, int pageSize, BargainUserHelpExample example);

            List<BargainUserHelp> getByBargainUserId(Long id);

            BargainUserHelp getByUidAndBargainUserId(Long uid, Long bargainUserId);
}
