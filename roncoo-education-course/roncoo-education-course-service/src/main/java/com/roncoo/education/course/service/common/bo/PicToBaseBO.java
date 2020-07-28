package com.roncoo.education.course.service.common.bo;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @description:
 * @author: husend
 * @time: 2020/7/24
 */
@Data
@Accessors(chain = true)
public class PicToBaseBO implements Serializable {

    private String image;

    private String headImg;

}
