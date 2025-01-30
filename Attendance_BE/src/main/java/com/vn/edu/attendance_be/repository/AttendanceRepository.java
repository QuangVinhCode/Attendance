package com.vn.edu.attendance_be.repository;

import com.vn.edu.attendance_be.domain.Attendance;
import com.vn.edu.attendance_be.dto.AttendanceInfoDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface AttendanceRepository extends JpaRepository<Attendance, Long> {


    List<Attendance> findByAttendanceStudents_Student_Id(String id);

    List<Attendance> findByaClass_Id(Long id);

    @Query("SELECT new com.vn.edu.attendance_be.dto.AttendanceInfoDto(a.id, a.name, " +
            "CASE WHEN (COUNT(ast.id) > 0) THEN 'Đã điểm danh' ELSE 'Không điểm danh' END) " +
            "FROM Attendance a " +
            "LEFT JOIN a.attendanceStudents ast ON ast.student.id = :studentId " +
            "WHERE a.aClass.id = :classId " +
            "GROUP BY a.id, a.name")
    List<AttendanceInfoDto> findAttendanceInfoByStudentAndClass(@Param("studentId") String studentId,
                                                                @Param("classId") Long classId);
}