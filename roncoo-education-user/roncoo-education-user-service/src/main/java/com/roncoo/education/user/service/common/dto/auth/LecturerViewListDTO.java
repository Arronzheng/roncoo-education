package com.roncoo.education.user.service.common.dto.auth;

import com.roncoo.education.user.service.common.dto.LecturerViewDTO;
import com.roncoo.education.user.service.dao.impl.mapper.entity.Lecturer;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

/**
 * 讲师信息
 *
 */
@Data
@Accessors(chain = true)
public class LecturerViewListDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private List<Lecturer> list;
}
