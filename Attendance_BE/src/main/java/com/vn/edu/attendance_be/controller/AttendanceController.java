package com.vn.edu.attendance_be.controller;


import com.vn.edu.attendance_be.domain.Attendance;
import com.vn.edu.attendance_be.domain.Attendance_Student;
import com.vn.edu.attendance_be.dto.AttendanceDto;
import com.vn.edu.attendance_be.dto.ClassDto;
import com.vn.edu.attendance_be.service.AttendanceService;
import com.vn.edu.attendance_be.service.Attendance_StudentService;
import com.vn.edu.attendance_be.service.ClassService;
import com.vn.edu.attendance_be.service.MapValidationErrorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/v1/attendances")
public class AttendanceController {
    @Autowired
    AttendanceService attendanceService;

    @Autowired
    Attendance_StudentService attendanceStudentService;

    @Autowired
    MapValidationErrorService mapValidationErrorService;


    @PostMapping
    public ResponseEntity<?> createAttendance(@Validated @RequestBody AttendanceDto dto, BindingResult result){

       ResponseEntity<?> responseEntity = mapValidationErrorService.mapValidationFields(result);

       if (responseEntity != null)
       {
           return responseEntity;
       }

        Attendance attendance =  attendanceService.save(dto);

        List<Attendance_Student> list = attendanceStudentService.save(dto,attendance);

        return new ResponseEntity<>(list, HttpStatus.CREATED);
    }
    @GetMapping()
    public ResponseEntity<?> getAttendances(){
        return new ResponseEntity<>(attendanceService.findAll(),HttpStatus.OK);
    }
    @PatchMapping("/attendance/{id}")
    public ResponseEntity<?> getStudentByAttendance(@PathVariable("id") Long id){
        return new ResponseEntity<>(attendanceStudentService.findAllByAttendance(id),HttpStatus.OK);
    }
}
