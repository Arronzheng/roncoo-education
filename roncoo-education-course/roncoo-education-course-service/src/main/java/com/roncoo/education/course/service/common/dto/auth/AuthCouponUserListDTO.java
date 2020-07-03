package com.roncoo.education.course.service.common.dto.auth;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

/**
 * @description:
 * @author: husend
 * @time: 2020/5/28
 */
@Data
@Accessors(chain = true)
public class AuthCouponUserListDTO implements Serializable {
    private List<AuthCouponUserViewDTO> authCouponUserViewDTOs;
}
