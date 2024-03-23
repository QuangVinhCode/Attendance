
package com.vn.edu.attendance_be.service;

import com.vn.edu.attendance_be.domain.*;
import com.vn.edu.attendance_be.domain.Class;
import com.vn.edu.attendance_be.dto.AttendanceDto;
import com.vn.edu.attendance_be.dto.Attendance_StudentDto;
import com.vn.edu.attendance_be.dto.StudentDto;
import com.vn.edu.attendance_be.exeception.ClassException;
import com.vn.edu.attendance_be.repository.Attendance_StudentRepository;
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

    public Attendance_Student save(Attendance_StudentDto dto) {
        List<Student> studentList = studentRepository.findByStudentList_Aclass_Id(dto.getClass_id());
        System.out.println("Do dai:" + studentList.toArray().length);
        Student student = new Student();
        for (Student a : studentList)
        {
            System.out.println(a.getId());

            if (a.getId().equals(dto.getStudent_id()))
            {
                BeanUtils.copyProperties(a,student );
            }
        }
        if (student.getId() == null)
        {
            throw new ClassException("Sinh viên không thuộc về lớp này!");
        }
        Optional<Attendance_Student> exist = attendance_studentRepository.findByStudent_IdAndAttendance_Id(dto.getStudent_id(),dto.getAttendance_id());
        if(exist.isPresent())
        {
            throw new ClassException("Sinh viên đã từng được điểm danh!");
        }
        Attendance_Student attendance_student = new Attendance_Student();
        attendance_student.setStatus(dto.isStatus());
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
