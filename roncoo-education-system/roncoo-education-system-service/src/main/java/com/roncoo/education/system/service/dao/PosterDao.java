
package com.roncoo.education.system.service.dao;

import com.roncoo.education.system.service.dao.impl.mapper.entity.Poster;
import com.roncoo.education.system.service.dao.impl.mapper.entity.PosterExample;
import com.roncoo.education.util.base.Page;

/**
 *  服务类
 *
 * @author husend
 * @since 2020-07-25
 */
public interface PosterDao {
            int save(Poster record);

            int deleteById(Long id);

            int updateById(Poster record);

            Poster getById(Long id);

            Page<Poster> listForPage(int pageCurrent, int pageSize, PosterExample example);

            Poster getByPosterType(Integer posterType);
}
