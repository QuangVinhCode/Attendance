package com.vn.edu.attendance_be.controller;


import com.vn.edu.attendance_be.domain.Class;
import com.vn.edu.attendance_be.domain.Student;
import com.vn.edu.attendance_be.domain.StudentJoinClass;
import com.vn.edu.attendance_be.dto.StudentDto;
import com.vn.edu.attendance_be.dto.StudentJoinClassDto;
import com.vn.edu.attendance_be.repository.ClassRepository;
import com.vn.edu.attendance_be.service.MapValidationErrorService;
import com.vn.edu.attendance_be.service.StudentJoinClassService;
import com.vn.edu.attendance_be.service.StudentService;
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
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/v1/studentsjoinclass")
public class StudentJoinClassController {
    @Autowired
    StudentJoinClassService studentJoinClassService;

    @Autowired
    MapValidationErrorService mapValidationErrorService;
    @Autowired
    private ClassRepository classRepository;


    @PostMapping
    public ResponseEntity<?> createStudent(@Validated @RequestBody StudentJoinClassDto dto, BindingResult result){

       ResponseEntity<?> responseEntity = mapValidationErrorService.mapValidationFields(result);

       if (responseEntity != null)
       {
           return responseEntity;
       }

        studentJoinClassService.save(dto);

        return new ResponseEntity<>(dto, HttpStatus.CREATED);
    }

    @GetMapping()
    public ResponseEntity<?> getStudents(){
        return new ResponseEntity<>(studentJoinClassService.findAll(),HttpStatus.OK);
    }




    @GetMapping("/{id}/get")
    public  ResponseEntity<?> getStudent(@PathVariable("id") Long id){
        return new ResponseEntity<>(studentJoinClassService.findById(id),HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteStudent(@PathVariable("id") Long id)
    {
        studentJoinClassService.deleteById(id);
        return  new ResponseEntity<>("Học viên có id " + id + " đã được xóa",HttpStatus.OK);
    }
}
