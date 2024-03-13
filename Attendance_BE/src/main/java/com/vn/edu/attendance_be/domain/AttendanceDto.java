package com.vn.edu.attendance_be.domain;

import lombok.Value;

import java.io.Serializable;
import java.util.List;

/**
 * DTO for {@link Attendance}
 */
@Value
public class AttendanceDto implements Serializable {

    String status;
    List<StudentDto> studentDtoList;
}