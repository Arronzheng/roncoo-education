package com.roncoo.education.user.service.biz.auth;

import com.roncoo.education.user.service.common.dto.auth.LecturerViewListDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.roncoo.education.user.service.common.bo.LecturerViewBO;
import com.roncoo.education.user.service.common.dto.LecturerViewDTO;
import com.roncoo.education.user.service.dao.LecturerDao;
import com.roncoo.education.user.service.dao.impl.mapper.entity.Lecturer;
import com.roncoo.education.util.base.Result;
import com.roncoo.education.util.tools.BeanUtil;
import com.xiaoleilu.hutool.util.ObjectUtil;

import java.util.List;

/**
 * 讲师信息 
 *
 * @author wujing
 */
@Component
public class AuthApiLecturerBiz {

	@Autowired
	private LecturerDao lecturerDao;

	/**
	 * 讲师信息查看接口
	 * 
	 * @param lecturerUserNo
	 * @author wuyun
	 */
	public Result<LecturerViewDTO> view(LecturerViewBO lecturerViewBO) {
		if (null == lecturerViewBO.getLecturerUserNo()) {
			return Result.error("讲师编号不能为空");
		}
		Lecturer lecturer = lecturerDao.getByLecturerUserNo(lecturerViewBO.getLecturerUserNo());
		if (ObjectUtil.isNull(lecturer)) {
			return Result.error("找不到该讲师");
		}
		return Result.success(BeanUtil.copyProperties(lecturer, LecturerViewDTO.class));
	}

    public Result<LecturerViewListDTO> list() {
		List<Lecturer> LecturerViewDTOs = lecturerDao.list();
		return Result.success(new LecturerViewListDTO().setList(LecturerViewDTOs));
    }
}
