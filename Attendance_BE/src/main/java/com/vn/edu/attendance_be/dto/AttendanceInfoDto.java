package com.vn.edu.attendance_be.dto;

import com.vn.edu.attendance_be.domain.Attendance_Student;
import lombok.Data;
import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link Attendance_Student}
 */
@Value
public class AttendanceInfoDto implements Serializable {
    Long id;
    String name;
    String status;

}