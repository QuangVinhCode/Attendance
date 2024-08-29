package com.vn.edu.attendance_be.repository;

import com.vn.edu.attendance_be.domain.User;
import com.vn.edu.attendance_be.dto.UserInfoDto;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    List<User> findByUsernameContains(String username);

    Optional<User> findByUsernameContainsIgnoreCase(String username);

    @Query("SELECT new com.vn.edu.attendance_be.dto.UserInfoDto(u.id, u.username, u.password, u.role, u.student.name, u.student.email) " +
            "FROM User u " +
            "WHERE u.username = :id")
    Optional<UserInfoDto> findUserInfoById(@Param("id") String id);
}