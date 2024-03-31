package com.vn.edu.attendance_be.dto;

import lombok.Data;
import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link com.vn.edu.attendance_be.domain.StudentJoinClass}
 */
@Data
public class StudentJoinClassDto implements Serializable {
    Long class_id;

    String student_id;
}