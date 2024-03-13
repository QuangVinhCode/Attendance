package com.vn.edu.attendance_be.dto;

import com.vn.edu.attendance_be.domain.Teacher;
import lombok.Data;
import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link com.vn.edu.attendance_be.domain.Class}
 */
@Data
public class ClassDto implements Serializable {

    Long id;

    String className;

    Long teacher_id;
}