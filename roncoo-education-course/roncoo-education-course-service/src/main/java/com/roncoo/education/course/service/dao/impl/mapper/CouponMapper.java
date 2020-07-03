package com.roncoo.education.course.service.dao.impl.mapper;

import com.roncoo.education.course.service.dao.impl.mapper.entity.Coupon;
import com.roncoo.education.course.service.dao.impl.mapper.entity.CouponExample;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 优惠券表 Mapper 接口
 *
 * @author husend
 * @since 2020-05-22
 */
@Mapper
public interface CouponMapper {
            int countByExample(CouponExample example);

            int deleteByExample(CouponExample example);

            int deleteByPrimaryKey(Long id);

            int insert(Coupon record);

            int insertSelective(Coupon record);

            List<Coupon> selectByExample(CouponExample example);

            Coupon selectByPrimaryKey(Long id);

            int updateByExampleSelective(@Param("record") Coupon record, @Param("example") CouponExample example);

            int updateByExample(@Param("record") Coupon record, @Param("example") CouponExample example);

            int updateByPrimaryKeySelective(Coupon record);

            int updateByPrimaryKey(Coupon record);
        }
