package com.vn.edu.attendance_be.service;

import com.vn.edu.attendance_be.domain.*;
import com.vn.edu.attendance_be.domain.Class;
import com.vn.edu.attendance_be.dto.ClassDto;
import com.vn.edu.attendance_be.dto.StudentDto;
import com.vn.edu.attendance_be.exeception.ClassException;
import com.vn.edu.attendance_be.repository.ClassRepository;
import com.vn.edu.attendance_be.repository.StudentJoinClassRepository;
import com.vn.edu.attendance_be.repository.StudentRepository;
import com.vn.edu.attendance_be.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
public class StudentService {
    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private StudentJoinClassRepository studentJoinClass;
    public Student save(StudentDto dto) {

        Student entity = new Student();

        Random random = new Random();
        // Tạo ID ngẫu nhiên trong khoảng từ 1 đến 9999
        int randomId = random.nextInt(9999) + 1;

        // Lấy thời gian hiện tại
        LocalDateTime currentTime = LocalDateTime.now();

        // Định dạng thời gian với giờ, phút, giây
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");

        // Ghép số ngẫu nhiên với thời gian hiện tại
        String finalId = "DH" + randomId + currentTime.format(formatter);

        entity.setId(finalId);
        entity.setName(dto.getName());
        entity.setEmail(dto.getEmail());

        User user = new User();
        String string_student_id=randomId + currentTime.format(formatter);
        Long long_student_id=Long.parseLong(string_student_id);
        user.setId(long_student_id);
        user.setUsername(finalId);
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        // Mã hóa mật khẩu
        String hashedPassword = passwordEncoder.encode(finalId);
        user.setPassword(hashedPassword);
        user.setRole("Sinh viên");
        user.setStudent(entity);
        userRepository.save(user);
        return studentRepository.save(entity);
    }

    public Student update(String id,StudentDto entity) {
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

    public Student findById(String id) {
        Optional<Student> found = studentRepository.findById(id);

        if (!found.isPresent())
        {
            throw new ClassException("Học sinh có id "+ id + "không tồn tại");
        }
        return found.get();
    }

    public void  deleteById(String id){
        Student existed = findById(id);

        studentRepository.delete(existed);
    }
}
