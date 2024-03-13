package com.vn.edu.attendance_be.service;

import com.vn.edu.attendance_be.domain.Class;
import com.vn.edu.attendance_be.domain.Student;
import com.vn.edu.attendance_be.domain.StudentJoinClass;
import com.vn.edu.attendance_be.domain.Teacher;
import com.vn.edu.attendance_be.dto.ClassDto;
import com.vn.edu.attendance_be.dto.StudentDto;
import com.vn.edu.attendance_be.exeception.ClassException;
import com.vn.edu.attendance_be.repository.ClassRepository;
import com.vn.edu.attendance_be.repository.StudentJoinClassRepository;
import com.vn.edu.attendance_be.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {
    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private StudentJoinClassRepository studentJoinClass;
    public Student save(StudentDto dto) {

        Student entity = new Student();

        entity.setId(dto.getId());
        entity.setName(dto.getName());
        entity.setEmail(dto.getEmail());

        return studentRepository.save(entity);
    }

    public Student update(Long id,StudentDto entity) {
        Optional<Student> existed = studentRepository.findById(id);
        if(!existed.isPresent())
        {
            throw new ClassException("Học sinh có id " + id + " không tồn tại");
        }

        try {
            Student existedStudent = existed.get();
            existedStudent.setName(entity.getName());
            existedStudent.setEmail(entity.getEmail());
           return studentRepository.save(existedStudent);
        }catch (Exception ex)
        {
            throw new ClassException("Học sinh muốn cập nhật bị lỗi");
        }
    }

    public List<Student> findAll() {
        return studentRepository.findAll();
    }

    public List<StudentJoinClass> findAllByClass(Long id) {
        return studentJoinClass.findByAclass_Id(id);
    }

    public Student findById(Long id) {
        Optional<Student> found = studentRepository.findById(id);

        if (!found.isPresent())
        {
            throw new ClassException("Học sinh có id "+ id + "không tồn tại");
        }
        return found.get();
    }

    public void  deleteById(Long id){
        Student existed = findById(id);

        studentRepository.delete(existed);
    }
}
