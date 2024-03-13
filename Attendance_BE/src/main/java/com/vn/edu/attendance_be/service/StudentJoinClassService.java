package com.vn.edu.attendance_be.service;

import com.vn.edu.attendance_be.domain.Student;
import com.vn.edu.attendance_be.domain.StudentJoinClass;
import com.vn.edu.attendance_be.exeception.ClassException;
import com.vn.edu.attendance_be.repository.StudentJoinClassRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentJoinClassService {
    @Autowired
    private StudentJoinClassRepository studentJoinClassRepository;

    public StudentJoinClass save(StudentJoinClass entity) {
        return studentJoinClassRepository.save(entity);
    }

    public List<StudentJoinClass> findAll() {
        return studentJoinClassRepository.findAll();
    }
    public StudentJoinClass findById(Long id) {
        Optional<StudentJoinClass> found = studentJoinClassRepository.findById(id);
        if (!found.isPresent())
        {
            throw new ClassException("Không tồn tại");
        }
        return found.get();
    }
    public void  deleteById(Student student){

        studentJoinClassRepository.deleteByStudent(student);

    }
}
