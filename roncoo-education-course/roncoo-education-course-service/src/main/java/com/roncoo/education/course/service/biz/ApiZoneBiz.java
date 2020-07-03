package com.roncoo.education.course.service.biz;

import java.util.ArrayList;
import java.util.List;

import com.roncoo.education.course.service.common.bo.ZoneAllBO;
import com.roncoo.education.course.service.common.dto.CourseDTO;
import com.roncoo.education.course.service.common.dto.ZoneListDTO;
import com.roncoo.education.course.service.dao.CourseAuditDao;
import com.roncoo.education.course.service.dao.impl.mapper.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.roncoo.education.course.service.common.bo.ZoneBO;
import com.roncoo.education.course.service.common.dto.ZoneCourseDTO;
import com.roncoo.education.course.service.common.dto.ZoneDTO;
import com.roncoo.education.course.service.dao.CourseDao;
import com.roncoo.education.course.service.dao.ZoneCourseDao;
import com.roncoo.education.course.service.dao.ZoneDao;
import com.roncoo.education.course.service.dao.impl.mapper.entity.ZoneExample.Criteria;
import com.roncoo.education.util.base.Page;
import com.roncoo.education.util.base.PageUtil;
import com.roncoo.education.util.base.Result;
import com.roncoo.education.util.enums.StatusIdEnum;
import com.roncoo.education.util.tools.BeanUtil;

@Component
public class ApiZoneBiz {

	@Autowired
	private ZoneDao zoneDao;
	@Autowired
	private ZoneCourseDao zoneCourseDao;
	@Autowired
	private CourseDao courseDao;
	@Autowired
	private CourseAuditDao courseAuditDaoDao;

	/**
	 * 专区课程分页列表接口
	 *
	 * @param zoneBO
	 * @author wuyun
	 */
	public Result<Page<ZoneDTO>> listForPage(ZoneBO zoneBO) {
		if (StringUtils.isEmpty(zoneBO.getZoneLocation())) {
			return Result.error("zoneLocation不能为空");
		}
		ZoneExample example = new ZoneExample();
		Criteria c = example.createCriteria();
		c.andStatusIdEqualTo(StatusIdEnum.YES.getCode());
		if (!StringUtils.isEmpty(zoneBO.getId())) {
			c.andIdEqualTo(zoneBO.getId());
		}
		if (!StringUtils.isEmpty(zoneBO.getZoneLocation())) {
			c.andZoneLocationEqualTo(zoneBO.getZoneLocation());
		}
		example.setOrderByClause("sort desc, id desc");
		Page<Zone> zonePage = zoneDao.listForPage(zoneBO.getPageCurrent(), zoneBO.getPageSize(), example);
		if (StringUtils.isEmpty(zonePage)) {
			return Result.error("找不到信息");
		}
		Page<ZoneDTO> page = PageUtil.transform(zonePage, ZoneDTO.class);
		for (ZoneDTO zone : page.getList()) {
			List<ZoneCourse> zoneCourseList = zoneCourseDao.listByZoneIdAndStatusId(zone.getId(), StatusIdEnum.YES.getCode());
			List<CourseDTO> courseListDTO = new ArrayList<>();
			for (ZoneCourse zoneCourse : zoneCourseList) {
				CourseAudit courseAudit = courseAuditDaoDao.getById(zoneCourse.getCourseId());
				courseListDTO.add(BeanUtil.copyProperties(courseAudit, CourseDTO.class));
			}
			zone.setCourseList(courseListDTO);
		}
		return Result.success(page);
	}

    public Result<ZoneListDTO> list(ZoneAllBO zoneAllBO) {
		if (StringUtils.isEmpty(zoneAllBO.getZoneLocation())) {
			return Result.error("zoneLocation不能为空");
		}
		ZoneExample example = new ZoneExample();
		Criteria c = example.createCriteria();
		c.andStatusIdEqualTo(StatusIdEnum.YES.getCode());
		if (!StringUtils.isEmpty(zoneAllBO.getId())) {
			c.andIdEqualTo(zoneAllBO.getId());
		}
		if (!StringUtils.isEmpty(zoneAllBO.getZoneLocation())) {
			c.andZoneLocationEqualTo(zoneAllBO.getZoneLocation());
		}
		example.setOrderByClause("sort desc, id desc");
		List<Zone> zones = zoneDao.listByLocation(example);
		List<ZoneDTO> zoneDTOs = BeanUtil.copyProperties(zones, ZoneDTO.class);
		if (StringUtils.isEmpty(zones)) {
			return Result.error("找不到信息");
		}
		for (ZoneDTO zoneDTO : zoneDTOs) {
			List<ZoneCourse> zoneCourseList = zoneCourseDao.listByZoneIdAndStatusId(zoneDTO.getId(), StatusIdEnum.YES.getCode());
			List<CourseDTO> courseListDTO = new ArrayList<>();
			for (ZoneCourse zoneCourse : zoneCourseList) {
				CourseAudit courseAudit = courseAuditDaoDao.getById(zoneCourse.getCourseId());
				courseListDTO.add(BeanUtil.copyProperties(courseAudit, CourseDTO.class));
			}
			zoneDTO.setCourseList(courseListDTO);
		}
		ZoneListDTO zoneListDTO = new ZoneListDTO();
		zoneListDTO.setZoneListDTO(zoneDTOs);
		return Result.success(zoneListDTO);
    }
}
