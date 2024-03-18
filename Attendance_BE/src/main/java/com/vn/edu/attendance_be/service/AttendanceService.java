
package com.vn.edu.attendance_be.service;


import com.vn.edu.attendance_be.domain.Attendance;
import com.vn.edu.attendance_be.domain.Class;
import com.vn.edu.attendance_be.dto.AttendanceDto;
import com.vn.edu.attendance_be.repository.AttendanceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class AttendanceService {

    @Autowired
    private AttendanceRepository attendanceRepository;

    public Attendance save(AttendanceDto dto) {

        Date currentDate = new Date();
        Attendance attendance = new Attendance();
        Class aClass = new Class();
        aClass.setId(dto.getClass_id());
        attendance.setAClass(aClass);
        attendance.setDate(currentDate);
        attendance.setName(dto.getName());

        return attendanceRepository.save(attendance);
    }

    public List<Attendance> findAll() {
        return attendanceRepository.findAll();
    }

}
