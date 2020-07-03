package com.roncoo.education.course.service.common.dto.auth;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.roncoo.education.course.service.common.dto.AssembleViewDTO;
import com.roncoo.education.user.common.bean.vo.UserExtVO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * AssembleViewRESQ对象
 *
 * @author husend
 * @since 2020-04-14
 */
@Data
@Accessors(chain = true)
@ApiModel(value = "AuthAssembleViewDTO分页请求对象", description = "拼团表")
public class AuthAssembleViewDTO implements Serializable {

        private static final long serialVersionUID = 1L;

        private List<AssembleViewDTO> assembleViewDTOs;
}
