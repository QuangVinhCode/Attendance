
package com.vn.edu.attendance_be.service;

import com.vn.edu.attendance_be.domain.*;
import com.vn.edu.attendance_be.domain.Class;
import com.vn.edu.attendance_be.dto.AttendanceDto;
import com.vn.edu.attendance_be.dto.Attendance_StudentDto;
import com.vn.edu.attendance_be.dto.StudentDto;
import com.vn.edu.attendance_be.exeception.ClassException;
import com.vn.edu.attendance_be.repository.Attendance_StudentRepository;
import com.vn.edu.attendance_be.repository.StudentJoinClassRepository;
import com.vn.edu.attendance_be.repository.StudentRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class Attendance_StudentService {

    @Autowired
    private Attendance_StudentRepository attendance_studentRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private StudentJoinClassRepository studentJoinClassRepository;

    public Attendance_Student save(Attendance_StudentDto dto) {
        Optional<StudentJoinClass> existstudentclass = studentJoinClassRepository.findByAclass_IdAndStudent_Id(dto.getClass_id(), dto.getStudent_id());
        if (existstudentclass.isEmpty())
        {
            throw new ClassException("Sinh viên không thuộc về lớp này!");
        }
        Optional<Attendance_Student> exist = attendance_studentRepository.findByStudent_IdAndAttendance_Id(dto.getStudent_id(),dto.getAttendance_id());
        if(exist.isPresent())
        {
            throw new ClassException("Sinh viên đã từng được điểm danh!");
        }
        Attendance_Student attendance_student = new Attendance_Student();
        Student aStudent = new Student();
        aStudent.setId(dto.getStudent_id());
        attendance_student.setStudent(aStudent);
        Attendance attendance = new Attendance();
        attendance.setId(dto.getAttendance_id());
        attendance_student.setAttendance(attendance);
        return  attendance_studentRepository.save(attendance_student);
    }

    public List<Attendance_Student> findAllByAttendance(Long id) {
        return attendance_studentRepository.findByAttendance_Id(id);
    }


}
