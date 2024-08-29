package com.vn.edu.attendance_be.service;

import com.vn.edu.attendance_be.domain.Teacher;
import com.vn.edu.attendance_be.domain.User;
import com.vn.edu.attendance_be.dto.UserDto;
import com.vn.edu.attendance_be.dto.UserInfoDto;
import com.vn.edu.attendance_be.exeception.ClassException;
import com.vn.edu.attendance_be.repository.TeacherRepository;
import com.vn.edu.attendance_be.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    TeacherRepository teacherRepository;

    public User register(UserDto dto) {
        List<?> foundList = userRepository.findByUsernameContains(dto.getUsername());

        if (!foundList.isEmpty()) {
            throw new ClassException("Tên tài khoản đã tồn tại trong hệ thống");
        }
        User entity = new User();
        Random random = new Random();
        // Tạo ID ngẫu nhiên trong khoảng từ 1 đến 9999
        int randomId = random.nextInt(9999) + 1;

        // Lấy thời gian hiện tại
        LocalDateTime currentTime = LocalDateTime.now();

        // Định dạng thời gian với giờ, phút, giây
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");

        // Ghép số ngẫu nhiên với thời gian hiện tại
        String finalId = randomId + currentTime.format(formatter);

        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        // Mã hóa mật khẩu
        String hashedPassword = passwordEncoder.encode(dto.getPassword());
        Long id = Long.parseLong(finalId);
        entity.setId(id);
        entity.setUsername(dto.getUsername());
        entity.setPassword(hashedPassword);
        entity.setRole(dto.getRole());
        Teacher teacher = new Teacher();
        teacher.setName(dto.getName());
        teacher.setEmail(dto.getEmail());
        Teacher anew = teacherRepository.save(teacher);
        entity.setTeacher(anew);
        return userRepository.save(entity);
    }

    public List<?> findAll() {
        return userRepository.findAll();
    }


    public User findById(Long id) {
        Optional<User> found = userRepository.findById(id);

        if (!found.isPresent())
        {
            throw new ClassException("Tài khoản có id "+ id + "không tồn tại");
        }
        return found.get();
    }

    public UserInfoDto findStudentInfoDto(String username) {
        Optional<UserInfoDto> found = userRepository.findUserInfoById(username);

        if (!found.isPresent())
        {
            throw new ClassException("Sinh viên "+ username + " không tồn tại");
        }
        return found.get();
    }

    public User login(String username,String password) {
        System.out.println("________________");
        System.out.println(username + password);
        Optional<User> found = userRepository.findByUsernameContainsIgnoreCase(username);

        if (!found.isPresent())
        {
            throw new ClassException("Tên đăng nhập hoặc mật khẩu không chính xác");
        }
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        boolean matches = passwordEncoder.matches(password, found.get().getPassword());
        if (matches != true)
        {
            throw new ClassException("Tên đăng nhập hoặc mật khẩu không chính xác");
        }
        return found.get();
    }

    public void  deleteById(Long id){
        User existed = findById(id);

        userRepository.delete(existed);
    }

    public User update(Long id ,UserDto dto) {
        Optional<User> foundList = userRepository.findById(id);

        if (!foundList.isPresent()) {
            throw new ClassException("Tên tài khoản đã tồn tại trong hệ thống");
        }
        User entity = new User();
        entity.setId(dto.getId());
        entity.setUsername(dto.getUsername());
        entity.setRole(dto.getRole());
        Teacher teacher = new Teacher();
        teacher.setId(dto.getTeacher_id());
        entity.setTeacher(teacher);

        return userRepository.save(entity);
    }
}
