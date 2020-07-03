package com.roncoo.education.course.service.dao.impl.mapper;

import com.roncoo.education.course.service.dao.impl.mapper.entity.CouponUser;
import com.roncoo.education.course.service.dao.impl.mapper.entity.CouponUserExample;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 优惠券发放记录表 Mapper 接口
 *
 * @author husend
 * @since 2020-05-22
 */
@Mapper
public interface CouponUserMapper {
            int countByExample(CouponUserExample example);

            int deleteByExample(CouponUserExample example);

            int deleteByPrimaryKey(Long id);

            int insert(CouponUser record);

            int insertSelective(CouponUser record);

            List<CouponUser> selectByExample(CouponUserExample example);

            CouponUser selectByPrimaryKey(Long id);

            int updateByExampleSelective(@Param("record") CouponUser record, @Param("example") CouponUserExample example);

            int updateByExample(@Param("record") CouponUser record, @Param("example") CouponUserExample example);

            int updateByPrimaryKeySelective(CouponUser record);

            int updateByPrimaryKey(CouponUser record);
        }
