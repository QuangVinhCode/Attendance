package com.vn.edu.attendance_be.repository;

import com.vn.edu.attendance_be.domain.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeacherRepository extends JpaRepository<Teacher, Long> {
}