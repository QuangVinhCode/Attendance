package com.vn.edu.attendance_be.dto;

import com.vn.edu.attendance_be.domain.User;
import lombok.Data;
import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link User}
 */
@Value
public class UserInfoDto implements Serializable {
  Long id;
  String username;
  String password;
  String role;
  String name;
  String email;
}