
package com.vn.edu.attendance_be.service;


import com.vn.edu.attendance_be.domain.Attendance;
import com.vn.edu.attendance_be.domain.Class;
import com.vn.edu.attendance_be.dto.AttendanceDto;
import com.vn.edu.attendance_be.exeception.ClassException;
import com.vn.edu.attendance_be.repository.AttendanceRepository;
import com.vn.edu.attendance_be.repository.ClassRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class AttendanceService {

    @Autowired
    private AttendanceRepository attendanceRepository;

    @Autowired
    private ClassRepository classRepository;

    public Attendance save(AttendanceDto dto) {

        Date currentDate = new Date();
        Attendance attendance = new Attendance();
        Optional<Class> aClass = classRepository.findById(dto.getClass_id());
        Class a = new Class();
        a.setId(aClass.get().getId());
        attendance.setAClass(a);
        attendance.setDate(currentDate);
        attendance.setName(dto.getName());

        return attendanceRepository.save(attendance);
    }

    public List<Attendance> findAll(Long id) {

        return attendanceRepository.findByaClass_Id(id);
    }

    public List<Attendance> findAllByStudent(String id) {
        return attendanceRepository.findByAttendanceStudents_Student_Id(id);
    }

    public List<?> findAttendanceInfoByStudentAndClass(String idStudent,Long idClass) {
        return attendanceRepository.findAttendanceInfoByStudentAndClass(idStudent,idClass);
    }

    public Attendance findById(Long id) {
        Optional<Attendance> found = attendanceRepository.findById(id);

        if (!found.isPresent())
        {
            throw new ClassException("Buổi điểm danh có id "+ id + "không tồn tại");
        }
        return found.get();
    }

    public void  deleteById(Long id){
        Attendance existed = findById(id);

        attendanceRepository.delete(existed);
    }
}
