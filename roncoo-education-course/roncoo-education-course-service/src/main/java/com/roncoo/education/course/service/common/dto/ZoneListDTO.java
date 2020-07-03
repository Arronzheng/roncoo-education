package com.roncoo.education.course.service.common.dto;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @description:
 * @author: husend
 * @time: 2020/3/31
 */
@Data
@Accessors(chain = true)
public class ZoneListDTO implements Serializable {
    private List<ZoneDTO> zoneListDTO = new ArrayList<>();
}
