package com.vn.edu.attendance_be.controller;


import com.vn.edu.attendance_be.domain.Class;
import com.vn.edu.attendance_be.domain.Student;
import com.vn.edu.attendance_be.domain.StudentJoinClass;
import com.vn.edu.attendance_be.dto.StudentDto;
import com.vn.edu.attendance_be.repository.ClassRepository;
import com.vn.edu.attendance_be.service.MapValidationErrorService;
import com.vn.edu.attendance_be.service.StudentJoinClassService;
import com.vn.edu.attendance_be.service.StudentService;
import com.vn.edu.attendance_be.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@RestController
@CrossOrigin
@RequestMapping("/api/v1/teachers")
public class TeacherController {
    @Autowired
    TeacherService teacherService;

    @Autowired
    MapValidationErrorService mapValidationErrorService;
    @GetMapping()
    public ResponseEntity<?> getTeachers(){
        return new ResponseEntity<>(teacherService.findAll(),HttpStatus.OK);
    }

}
