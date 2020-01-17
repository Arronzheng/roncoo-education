
package com.roncoo.education.user.service.dao;

import com.roncoo.education.user.service.dao.impl.mapper.entity.SvipBuyLog;
import com.roncoo.education.user.service.dao.impl.mapper.entity.SvipBuyLogExample;
import com.roncoo.education.util.base.Page;

/**
 * 会员购买日志表 服务类
 *
 * @author ${author}
 * @since 2019-12-25
 */
public interface SvipBuyLogDao {
            int save(SvipBuyLog record);

            int deleteById(Long id);

            int updateById(SvipBuyLog record);

            SvipBuyLog getById(Long id);

            Page<SvipBuyLog> listForPage(int pageCurrent, int pageSize, SvipBuyLogExample example);

        }
