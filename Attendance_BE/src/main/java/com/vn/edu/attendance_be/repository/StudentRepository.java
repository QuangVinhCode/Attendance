package com.vn.edu.attendance_be.repository;

import com.vn.edu.attendance_be.domain.Student;
import com.vn.edu.attendance_be.domain.StudentJoinClass;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Long> {


}