package com.vn.edu.attendance_be.repository;

import com.vn.edu.attendance_be.domain.Student;
import com.vn.edu.attendance_be.domain.StudentJoinClass;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface StudentJoinClassRepository extends JpaRepository<StudentJoinClass, Long> {
    @Transactional
    void deleteByStudent(Student student);

    List<StudentJoinClass> findByAclass_Id(Long id);

}