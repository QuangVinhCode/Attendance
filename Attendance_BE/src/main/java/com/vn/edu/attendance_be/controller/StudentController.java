package com.vn.edu.attendance_be.controller;


import com.vn.edu.attendance_be.domain.Class;
import com.vn.edu.attendance_be.domain.Student;
import com.vn.edu.attendance_be.domain.StudentJoinClass;
import com.vn.edu.attendance_be.dto.ClassDto;
import com.vn.edu.attendance_be.dto.StudentDto;
import com.vn.edu.attendance_be.repository.ClassRepository;
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
import java.util.Optional;


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
    @Autowired
    private ClassRepository classRepository;


    @PostMapping
    public ResponseEntity<?> createStudent(@Validated @RequestBody StudentDto dto, BindingResult result){

       ResponseEntity<?> responseEntity = mapValidationErrorService.mapValidationFields(result);

       if (responseEntity != null)
       {
           return responseEntity;
       }

        studentService.save(dto);

        return new ResponseEntity<>(dto, HttpStatus.CREATED);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<?> updateStudent(@PathVariable("id") String id, @RequestBody StudentDto dto){

        StudentJoinClass studentJoinClass = new StudentJoinClass();
        Student  student =  studentService.update(id,dto);

        return new ResponseEntity<>(dto, HttpStatus.CREATED);
    }

    @GetMapping()
    public ResponseEntity<?> getStudents(){
        return new ResponseEntity<>(studentService.findAll(),HttpStatus.OK);
    }

    @GetMapping("/class/{id}")
    public ResponseEntity<?> getStudentsByClass(@PathVariable("id") Long id){
        Optional<Class> aClass = classRepository.findById(id);
        List<StudentJoinClass> studentJoinClassList = studentService.findAllByClass(aClass.get().getId());

        List<Student> students = new ArrayList<>();
        for (StudentJoinClass a : studentJoinClassList)
        {
            Student student = studentService.findById(a.getStudent().getId());
            students.add(student);
        }

        return new ResponseEntity<>(students,HttpStatus.OK);
    }


    @GetMapping("/{id}/get")
    public  ResponseEntity<?> getStudent(@PathVariable("id") String id){
        return new ResponseEntity<>(studentService.findById(id),HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteStudent(@PathVariable("id") String id)
    {
        Student student = studentService.findById(id);
        studentService.deleteById(id);
        return  new ResponseEntity<>("Học viên có id " + id + " đã được xóa",HttpStatus.OK);
    }
}
