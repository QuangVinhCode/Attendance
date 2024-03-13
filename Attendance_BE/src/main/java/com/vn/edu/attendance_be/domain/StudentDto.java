package com.vn.edu.attendance_be.domain;

import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link Student}
 */
@Value
public class StudentDto implements Serializable {
    Long id;
    String name;
    String email;
}