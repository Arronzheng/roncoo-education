
package com.roncoo.education.user.service.dao;

import com.roncoo.education.user.common.bean.vo.SvipVO;
import com.roncoo.education.user.service.dao.impl.mapper.entity.Svip;
import com.roncoo.education.user.service.dao.impl.mapper.entity.SvipExample;
import com.roncoo.education.util.base.Page;

/**
 * 会员信息表 服务类
 *
 * @author ${author}
 * @since 2019-12-17
 */
public interface SvipDao {

    int save(Svip record);

    int deleteById(Long id);

    int updateById(Svip record);

    Svip getById(Long id);

    Page<Svip> listForPage(int pageCurrent, int pageSize, SvipExample example);

    Svip getByUserNo(Long userNo);
}
