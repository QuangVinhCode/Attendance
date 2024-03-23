package com.vn.edu.attendance_be.repository;

import com.vn.edu.attendance_be.domain.Attendance_Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface Attendance_StudentRepository extends JpaRepository<Attendance_Student, Long> {

    List<Attendance_Student> findByAttendance_Id(Long id);

    Optional<Attendance_Student> findByStudent_IdAndAttendance_Id(Long id, Long id1);

}