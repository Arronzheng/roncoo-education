package com.roncoo.education.course.service.common.dto.auth;

import com.roncoo.education.course.service.common.dto.AssembleViewDTO;
import com.roncoo.education.course.service.common.dto.BargainUserViewDTO;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

/**
 * AuthBargainViewDTO对象
 *
 * @author husend
 * @since 2020-04-14
 */
@Data
@Accessors(chain = true)
@ApiModel(value = "AuthBargainViewDTO分页请求对象", description = "砍价表")
public class AuthBargainUserViewDTO implements Serializable {

        private static final long serialVersionUID = 1L;

        private List<BargainUserViewDTO> bargainUserViewDTOs;
}
