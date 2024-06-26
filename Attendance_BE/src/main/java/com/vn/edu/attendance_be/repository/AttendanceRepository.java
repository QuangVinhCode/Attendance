package com.vn.edu.attendance_be.repository;

import com.vn.edu.attendance_be.domain.Attendance;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AttendanceRepository extends JpaRepository<Attendance, Long> {


    List<Attendance> findByAttendanceStudents_Student_Id(String id);

    List<Attendance> findByaClass_Id(Long id);
}