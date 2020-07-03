package com.roncoo.education.course.service.dao.impl.mapper;

import com.roncoo.education.course.service.dao.impl.mapper.entity.CouponIssue;
import com.roncoo.education.course.service.dao.impl.mapper.entity.CouponIssueExample;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 优惠券前台领取表 Mapper 接口
 *
 * @author husend
 * @since 2020-05-22
 */
@Mapper
public interface CouponIssueMapper {
            int countByExample(CouponIssueExample example);

            int deleteByExample(CouponIssueExample example);

            int deleteByPrimaryKey(Long id);

            int insert(CouponIssue record);

            int insertSelective(CouponIssue record);

            List<CouponIssue> selectByExample(CouponIssueExample example);

            CouponIssue selectByPrimaryKey(Long id);

            int updateByExampleSelective(@Param("record") CouponIssue record, @Param("example") CouponIssueExample example);

            int updateByExample(@Param("record") CouponIssue record, @Param("example") CouponIssueExample example);

            int updateByPrimaryKeySelective(CouponIssue record);

            int updateByPrimaryKey(CouponIssue record);
        }
