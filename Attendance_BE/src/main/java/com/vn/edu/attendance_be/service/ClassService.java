package com.vn.edu.attendance_be.service;

import com.vn.edu.attendance_be.domain.Class;
import com.vn.edu.attendance_be.domain.Teacher;
import com.vn.edu.attendance_be.dto.ClassDto;
import com.vn.edu.attendance_be.exeception.ClassException;
import com.vn.edu.attendance_be.repository.ClassRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClassService {
    @Autowired
    private ClassRepository classRepository;
    public Class save(ClassDto dto) {

        Class entity = new Class();

        entity.setId(dto.getId());
        entity.setClassName(dto.getClassName());
        Teacher teacher = new Teacher();
        teacher.setId(dto.getTeacher_id());
        entity.setTeacher(teacher);

        return classRepository.save(entity);
    }

    public Class update(Long id,ClassDto entity) {
        Optional<Class> existed = classRepository.findById(id);
        if(!existed.isPresent())
        {
            throw new ClassException("Lớp có id " + id + " không tồn tại");
        }

        try {
           Class existedClass = existed.get();
           existedClass.setClassName(entity.getClassName());
           Teacher teacher = new Teacher();
           teacher.setId(entity.getTeacher_id());
           existedClass.setTeacher(teacher);
           return classRepository.save(existedClass);
        }catch (Exception ex)
        {
            throw new ClassException("Lớp muốn cập nhật bị lỗi");
        }
    }

    public List<Class> findAll() {
        return classRepository.findAll();
    }

    public Class findById(Long id) {
        Optional<Class> found = classRepository.findById(id);

        if (!found.isPresent())
        {
            throw new ClassException("Lớp có id "+ id + "không tồn tại");
        }
        return found.get();
    }

    public void  deleteById(Long id){
        Class existed = findById(id);

        classRepository.delete(existed);
    }
}
