package com.roncoo.education.user.service.dao.impl.mapper;

import com.roncoo.education.user.service.dao.impl.mapper.entity.SvipBuyLog;
import com.roncoo.education.user.service.dao.impl.mapper.entity.SvipBuyLogExample;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 会员购买日志表 Mapper 接口
 *
 * @author ${author}
 * @since 2019-12-25
 */
@Mapper
public interface SvipBuyLogMapper {
            int countByExample(SvipBuyLogExample example);

            int deleteByExample(SvipBuyLogExample example);

            int deleteByPrimaryKey(Long id);

            int insert(SvipBuyLog record);

            int insertSelective(SvipBuyLog record);

            List<SvipBuyLog> selectByExample(SvipBuyLogExample example);

            SvipBuyLog selectByPrimaryKey(Long id);

            int updateByExampleSelective(@Param("record") SvipBuyLog record, @Param("example") SvipBuyLogExample example);

            int updateByExample(@Param("record") SvipBuyLog record, @Param("example") SvipBuyLogExample example);

            int updateByPrimaryKeySelective(SvipBuyLog record);

            int updateByPrimaryKey(SvipBuyLog record);
        }
