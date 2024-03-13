package com.vn.edu.attendance_be.dto;

import com.vn.edu.attendance_be.domain.Student;
import lombok.Data;
import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link Student}
 */
@Data
public class StudentDto implements Serializable {
    Long id;
    String name;
    String email;
    Long class_id;
}