package com.roncoo.education.user.service.dao.impl.mapper;

import com.roncoo.education.user.service.dao.impl.mapper.entity.SvipOrder;
import com.roncoo.education.user.service.dao.impl.mapper.entity.SvipOrderExample;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 订单信息表 Mapper 接口
 *
 * @author ${author}
 * @since 2019-12-23
 */
@Mapper
public interface SvipOrderMapper {
            int countByExample(SvipOrderExample example);

            int deleteByExample(SvipOrderExample example);

            int deleteByPrimaryKey(Long id);

            int insert(SvipOrder record);

            int insertSelective(SvipOrder record);

            List<SvipOrder> selectByExample(SvipOrderExample example);

            SvipOrder selectByPrimaryKey(Long id);

            int updateByExampleSelective(@Param("record") SvipOrder record, @Param("example") SvipOrderExample example);

            int updateByExample(@Param("record") SvipOrder record, @Param("example") SvipOrderExample example);

            int updateByPrimaryKeySelective(SvipOrder record);

            int updateByPrimaryKey(SvipOrder record);
        }
