package com.vn.edu.attendance_be.dto;

import lombok.Value;

import java.io.Serializable;
import java.util.Date;

/**
 * DTO for {@link com.vn.edu.attendance_be.domain.Attendance}
 */
@Value
public class AttendanceDto implements Serializable {
    Date date;
    boolean status;
}