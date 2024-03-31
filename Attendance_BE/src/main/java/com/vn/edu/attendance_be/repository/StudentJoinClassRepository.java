package com.vn.edu.attendance_be.repository;

import com.vn.edu.attendance_be.domain.StudentJoinClass;
import com.vn.edu.attendance_be.domain.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface StudentJoinClassRepository extends JpaRepository<StudentJoinClass, Long> {
    @Transactional
    void deleteByStudent(Student student);

    List<StudentJoinClass> findByAclass_Id(Long id);

    Optional<StudentJoinClass> findByAclass_IdAndStudent_Id(Long id, String id1);

}