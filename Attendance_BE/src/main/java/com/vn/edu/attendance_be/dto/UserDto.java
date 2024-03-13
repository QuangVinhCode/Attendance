package com.vn.edu.attendance_be.dto;

import com.vn.edu.attendance_be.domain.User;
import lombok.Data;
import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link User}
 */
@Data
public class UserDto implements Serializable {
    Long id;
    String username;
    String password;
    String role;

    Long teacher_id;
    String name;
    String email;
}