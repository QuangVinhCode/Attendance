package com.vn.edu.attendance_be.controller;


import com.vn.edu.attendance_be.domain.*;
import com.vn.edu.attendance_be.domain.Class;
import com.vn.edu.attendance_be.dto.StudentDto;
import com.vn.edu.attendance_be.dto.TeacherDto;
import com.vn.edu.attendance_be.dto.UserDto;
import com.vn.edu.attendance_be.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


@RestController
@CrossOrigin
@RequestMapping("/api/v1/users")
public class UserController {
    @Autowired
    UserService userService;

    @Autowired
    TeacherService teacherService;

    @Autowired
    MapValidationErrorService mapValidationErrorService;


    @PostMapping
    public ResponseEntity<?> createUser(@Validated @RequestBody UserDto dto, BindingResult result){

       ResponseEntity<?> responseEntity = mapValidationErrorService.mapValidationFields(result);

       if (responseEntity != null)
       {
           return responseEntity;
       }

        userService.register(dto);

        return new ResponseEntity<>(dto, HttpStatus.CREATED);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<?> updateUser(@PathVariable("id") Long id, @RequestBody UserDto dto){
        User  user =  userService.update(id,dto);

//        TeacherDto teacherDto = new TeacherDto();
//
//        teacherDto.setName(dto.getName());
//        teacherDto.setEmail(dto.getEmail());
//        teacherDto.setTeacher_id(user.getId());
//
//        teacherService.save(dto);

        return new ResponseEntity<>(dto, HttpStatus.CREATED);
    }

    @GetMapping()
    public ResponseEntity<?> getUsers(){
        return new ResponseEntity<>(userService.findAll(),HttpStatus.OK);
    }

    @GetMapping("/{id}/get")
    public  ResponseEntity<?> getUser(@PathVariable("id") Long id){
        return new ResponseEntity<>(userService.findById(id),HttpStatus.OK);
    }

    @GetMapping("/student-info/{id}")
    public  ResponseEntity<?> getStudentInfoDto(@PathVariable("id") String id){
        return new ResponseEntity<>(userService.findStudentInfoDto(id),HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable("id") Long id)
    {
        User user = userService.findById(id);
//        userService.deleteById(id);
        return  new ResponseEntity<>("Tài khoản có id " + id + " đã được xóa",HttpStatus.OK);
    }

    @PatchMapping("/login/{username}/{password}")
    public ResponseEntity<?> loginUser(@PathVariable("username") String username,@PathVariable("password") String password) {

        User loggedInAccount = userService.login(username,password);
        return new ResponseEntity<>(loggedInAccount, HttpStatus.OK);
    }
}
