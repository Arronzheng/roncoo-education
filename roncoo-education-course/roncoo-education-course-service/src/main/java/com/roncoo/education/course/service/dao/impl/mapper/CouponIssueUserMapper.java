package com.roncoo.education.course.service.dao.impl.mapper;

import com.roncoo.education.course.service.dao.impl.mapper.entity.CouponIssueUser;
import com.roncoo.education.course.service.dao.impl.mapper.entity.CouponIssueUserExample;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 优惠券前台用户领取记录表 Mapper 接口
 *
 * @author husend
 * @since 2020-05-22
 */
@Mapper
public interface CouponIssueUserMapper {
            int countByExample(CouponIssueUserExample example);

            int deleteByExample(CouponIssueUserExample example);

            int deleteByPrimaryKey(Long id);

            int insert(CouponIssueUser record);

            int insertSelective(CouponIssueUser record);

            List<CouponIssueUser> selectByExample(CouponIssueUserExample example);

            CouponIssueUser selectByPrimaryKey(Long id);

            int updateByExampleSelective(@Param("record") CouponIssueUser record, @Param("example") CouponIssueUserExample example);

            int updateByExample(@Param("record") CouponIssueUser record, @Param("example") CouponIssueUserExample example);

            int updateByPrimaryKeySelective(CouponIssueUser record);

            int updateByPrimaryKey(CouponIssueUser record);
        }
