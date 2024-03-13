package com.vn.edu.attendance_be.repository;

import com.vn.edu.attendance_be.domain.Attendance;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AttendanceRepository extends JpaRepository<Attendance, Long> {
}