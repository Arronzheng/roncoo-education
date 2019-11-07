package com.roncoo.education.course.service.common.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class CourseCategoryTreeDTO implements Serializable {

    private String label;
    @JsonSerialize(using = ToStringSerializer.class)
    private Long value;
    private List<CourseCategoryTreeDTO> children;

}
