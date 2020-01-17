package com.roncoo.education.user.service.dao.impl.mapper;

import com.roncoo.education.user.service.dao.impl.mapper.entity.Svip;
import com.roncoo.education.user.service.dao.impl.mapper.entity.SvipExample;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 会员信息表 Mapper 接口
 *
 * @author ${author}
 * @since 2019-12-17
 */
@Mapper
public interface SvipMapper {
            int countByExample(SvipExample example);

            int deleteByExample(SvipExample example);

            int deleteByPrimaryKey(Long id);

            int insert(Svip record);

            int insertSelective(Svip record);

            List<Svip> selectByExample(SvipExample example);

            Svip selectByPrimaryKey(Long id);

            int updateByExampleSelective(@Param("record") Svip record, @Param("example") SvipExample example);

            int updateByExample(@Param("record") Svip record, @Param("example") SvipExample example);

            int updateByPrimaryKeySelective(Svip record);

            int updateByPrimaryKey(Svip record);

            Svip getVipByUserNo(Long userNo);
        }
