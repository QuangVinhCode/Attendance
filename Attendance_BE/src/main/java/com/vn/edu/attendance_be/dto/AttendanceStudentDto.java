package com.vn.edu.attendance_be.dto;

import com.vn.edu.attendance_be.domain.Attendance_Student;
import lombok.Data;
import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link Attendance_Student}
 */
@Data
public class AttendanceStudentDto implements Serializable {
    Long id_Class;
    String id_Student;
}