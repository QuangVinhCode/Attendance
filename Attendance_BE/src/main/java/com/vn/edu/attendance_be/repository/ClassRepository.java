package com.vn.edu.attendance_be.repository;

import com.vn.edu.attendance_be.domain.Class;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ClassRepository extends JpaRepository<Class, Long> {


    List<Class> findByStudentList_Student_Id(String id);
}