package com.vn.edu.attendance_be.dto;

import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link com.vn.edu.attendance_be.domain.Attendance_Student}
 */
@Value
public class Attendance_StudentDto implements Serializable {
    String student_id;
    Long attendance_id;
    Long class_id;
}