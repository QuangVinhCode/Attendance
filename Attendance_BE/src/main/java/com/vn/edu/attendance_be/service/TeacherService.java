package com.vn.edu.attendance_be.service;

import com.vn.edu.attendance_be.domain.Student;
import com.vn.edu.attendance_be.domain.Teacher;
import com.vn.edu.attendance_be.dto.StudentDto;
import com.vn.edu.attendance_be.dto.TeacherDto;
import com.vn.edu.attendance_be.dto.UserDto;
import com.vn.edu.attendance_be.exeception.ClassException;
import com.vn.edu.attendance_be.repository.StudentJoinClassRepository;
import com.vn.edu.attendance_be.repository.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TeacherService {
    @Autowired
    private TeacherRepository teacherRepository;

    public Teacher save(UserDto dto) {

        Teacher entity = new Teacher();
        entity.setName(dto.getName());
        entity.setEmail(dto.getEmail());

        return teacherRepository.save(entity);
    }

    public Teacher update(Long id,UserDto entity) {
        Optional<Teacher> existed = teacherRepository.findById(id);
        if(!existed.isPresent())
        {
            throw new ClassException("Học sinh có id " + id + " không tồn tại");
        }

        try {
            Teacher existedStudent = existed.get();
            existedStudent.setName(entity.getName());
            existedStudent.setEmail(entity.getEmail());
           return teacherRepository.save(existedStudent);
        }catch (Exception ex)
        {
            throw new ClassException("Học sinh muốn cập nhật bị lỗi");
        }
    }

    public List<Teacher> findAll() {
        return teacherRepository.findAll();
    }

    public Teacher findById(Long id) {
        Optional<Teacher> found = teacherRepository.findById(id);

        if (!found.isPresent())
        {
            throw new ClassException("Học sinh có id "+ id + "không tồn tại");
        }
        return found.get();
    }

    public void  deleteById(Long id){
        Teacher existed = findById(id);

        teacherRepository.delete(existed);
    }
}
