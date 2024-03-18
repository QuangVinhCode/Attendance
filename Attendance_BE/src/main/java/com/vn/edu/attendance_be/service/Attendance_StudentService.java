
package com.vn.edu.attendance_be.service;

import com.vn.edu.attendance_be.domain.*;
import com.vn.edu.attendance_be.dto.AttendanceDto;
import com.vn.edu.attendance_be.dto.StudentDto;
import com.vn.edu.attendance_be.repository.Attendance_StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class Attendance_StudentService {

    @Autowired
    private Attendance_StudentRepository attendance_studentRepository;

    public List<Attendance_Student> save(AttendanceDto dto, Attendance attendance) {


        List<Attendance_Student> list = new ArrayList<>();
        for (StudentDto a : dto.getStudentList())
        {
            Attendance_Student attendance_student = new Attendance_Student();
            attendance_student.setStatus(a.isStatus());
            Student aStudent = new Student();
            aStudent.setId(a.getId());
            attendance_student.setStudent(aStudent);
            attendance_student.setAttendance(attendance);
            attendance_studentRepository.save(attendance_student);
            list.add(attendance_student);
        }
        return list;
    }

    public List<Attendance_Student> findAllByAttendance(Long id) {
        return attendance_studentRepository.findByAttendance_Id(id);
    }

}
