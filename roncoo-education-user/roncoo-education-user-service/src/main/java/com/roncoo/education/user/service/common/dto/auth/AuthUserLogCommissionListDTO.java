package com.roncoo.education.user.service.common.dto.auth;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

/**
 * @description:
 * @author: husend
 * @time: 2020/6/12
 */
@Data
@Accessors(chain = true)
public class AuthUserLogCommissionListDTO implements Serializable {

    private List<AuthUserLogCommissionViewDTO> userLogCommissionViewDTOS;
}
