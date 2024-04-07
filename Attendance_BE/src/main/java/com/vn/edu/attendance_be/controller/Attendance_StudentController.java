package com.vn.edu.attendance_be.controller;


import com.vn.edu.attendance_be.domain.Attendance;
import com.vn.edu.attendance_be.domain.Attendance_Student;
import com.vn.edu.attendance_be.dto.AttendanceDto;
import com.vn.edu.attendance_be.dto.Attendance_StudentDto;
import com.vn.edu.attendance_be.service.AttendanceService;
import com.vn.edu.attendance_be.service.Attendance_StudentService;
import com.vn.edu.attendance_be.service.MapValidationErrorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/v1/attendances_students")
public class Attendance_StudentController {
    @Autowired
    Attendance_StudentService attendanceStudentService;

    @Autowired
    MapValidationErrorService mapValidationErrorService;
    @PostMapping
    public ResponseEntity<?> createAttendance(@Validated @RequestBody Attendance_StudentDto dto, BindingResult result){

       ResponseEntity<?> responseEntity = mapValidationErrorService.mapValidationFields(result);

       if (responseEntity != null)
       {
           return responseEntity;
       }

        Attendance_Student attendance =  attendanceStudentService.save(dto);
        return new ResponseEntity<>( dto, HttpStatus.CREATED);
    }
    @PatchMapping("/attendance/{id}")
    public ResponseEntity<?> getStudentByAttendance(@PathVariable("id") Long id){
        return new ResponseEntity<>(attendanceStudentService.findAllByAttendance(id),HttpStatus.OK);
    }


}
