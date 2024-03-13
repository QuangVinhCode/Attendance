package com.vn.edu.attendance_be.dto;

import lombok.Data;
import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link com.vn.edu.attendance_be.domain.Teacher}
 */
@Data
public class TeacherDto implements Serializable {
    String name;
    String email;
    Long teacher_id;
}