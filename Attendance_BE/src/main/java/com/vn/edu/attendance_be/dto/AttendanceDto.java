package com.vn.edu.attendance_be.dto;

import com.vn.edu.attendance_be.domain.Attendance_Student;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * DTO for {@link Attendance_Student}
 */
@Data
public class AttendanceDto implements Serializable {

    Long class_id;

    String name;

}