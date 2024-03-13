package com.vn.edu.attendance_be.repository;

import com.vn.edu.attendance_be.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    List<User> findByUsernameContains(String username);

    Optional<User> findByUsernameContainsIgnoreCase(String username);

}