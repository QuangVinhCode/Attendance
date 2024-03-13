package com.vn.edu.attendance_be.controller;


import com.vn.edu.attendance_be.domain.Class;
import com.vn.edu.attendance_be.domain.Student;
import com.vn.edu.attendance_be.domain.StudentJoinClass;
import com.vn.edu.attendance_be.dto.ClassDto;
import com.vn.edu.attendance_be.dto.StudentDto;
import com.vn.edu.attendance_be.service.ClassService;
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


@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/v1/students")
public class StudentController {
    @Autowired
    StudentService studentService;

    @Autowired
    StudentJoinClassService studentJoinClassService;

    @Autowired
    MapValidationErrorService mapValidationErrorService;


    @PostMapping
    public ResponseEntity<?> createStudent(@Validated @RequestBody StudentDto dto, BindingResult result){

       ResponseEntity<?> responseEntity = mapValidationErrorService.mapValidationFields(result);

       if (responseEntity != null)
       {
           return responseEntity;
       }

        Student student =  studentService.save(dto);

        StudentJoinClass studentJoinClass = new StudentJoinClass();
        Class aClass = new Class();
        aClass.setId(dto.getClass_id());
        studentJoinClass.setAclass(aClass);
        studentJoinClass.setStudent(student);
        studentJoinClassService.save(studentJoinClass);

        return new ResponseEntity<>(dto, HttpStatus.CREATED);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<?> updateStudent(@PathVariable("id") Long id, @RequestBody StudentDto dto){

        StudentJoinClass studentJoinClass = new StudentJoinClass();
        Student  student =  studentService.update(id,dto);
        Class aClass = new Class();
        aClass.setId(dto.getClass_id());
        studentJoinClass.setAclass(aClass);
        studentJoinClass.setStudent(student);
        studentJoinClassService.save(studentJoinClass);

        return new ResponseEntity<>(dto, HttpStatus.CREATED);
    }

    @GetMapping()
    public ResponseEntity<?> getStudents(){
        return new ResponseEntity<>(studentService.findAll(),HttpStatus.OK);
    }

    @GetMapping("/class/{id}")
    public ResponseEntity<?> getStudentsByClass(@PathVariable("id") Long id){
        List<StudentJoinClass> studentJoinClassList = studentService.findAllByClass(id);

        List<Student> students = new ArrayList<>();
        for (StudentJoinClass a : studentJoinClassList)
        {
            Student student = studentService.findById(a.getStudent().getId());
            students.add(student);
        }

        return new ResponseEntity<>(students,HttpStatus.OK);
    }


    @GetMapping("/{id}/get")
    public  ResponseEntity<?> getStudent(@PathVariable("id") Long id){
        return new ResponseEntity<>(studentService.findById(id),HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteStudent(@PathVariable("id") Long id)
    {
        Student student = studentService.findById(id);
        studentJoinClassService.deleteById(student);
        studentService.deleteById(id);
        return  new ResponseEntity<>("Học viên có id " + id + " đã được xóa",HttpStatus.OK);
    }
}
