package com.vn.edu.attendance_be.service;

import com.vn.edu.attendance_be.domain.Class;
import com.vn.edu.attendance_be.domain.Student;
import com.vn.edu.attendance_be.domain.Teacher;
import com.vn.edu.attendance_be.dto.ClassDto;
import com.vn.edu.attendance_be.exeception.ClassException;
import com.vn.edu.attendance_be.repository.AttendanceRepository;
import com.vn.edu.attendance_be.repository.Attendance_StudentRepository;
import com.vn.edu.attendance_be.repository.ClassRepository;
import com.vn.edu.attendance_be.repository.StudentRepository;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.FileOutputStream;
import java.util.List;
import java.util.Optional;


@Service
public class ClassService {
    @Autowired
    private ClassRepository classRepository;
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private AttendanceRepository attendanceRepository;
    @Autowired
    private Attendance_StudentRepository attendance_studentRepository;
    public Class save(ClassDto dto) {

        Class entity = new Class();

        entity.setId(dto.getId());
        entity.setClassName(dto.getClassName());
        Teacher teacher = new Teacher();
        teacher.setId(dto.getTeacher_id());
        entity.setTeacher(teacher);

        return classRepository.save(entity);
    }

    public Class update(Long id,ClassDto entity) {
        Optional<Class> existed = classRepository.findById(id);
        if(!existed.isPresent())
        {
            throw new ClassException("Lớp có id " + id + " không tồn tại");
        }

        try {
           Class existedClass = existed.get();
           existedClass.setClassName(entity.getClassName());
           Teacher teacher = new Teacher();
           teacher.setId(entity.getTeacher_id());
           existedClass.setTeacher(teacher);
           return classRepository.save(existedClass);
        }catch (Exception ex)
        {
            throw new ClassException("Lớp muốn cập nhật bị lỗi");
        }
    }

    public List<Class> findAll() {
        return classRepository.findAll();
    }

    public List<Class> findAllByStudent(String id) {
        return classRepository.findByStudentList_Student_Id(id);
    }

    public Class findById(Long id) {
        Optional<Class> found = classRepository.findById(id);

        if (!found.isPresent())
        {
            throw new ClassException("Lớp có id "+ id + "không tồn tại");
        }
        return found.get();
    }

    public void  deleteById(Long id){
        Class existed = findById(id);

        classRepository.delete(existed);
    }

    public double calculateAttendanceRate(Long classId,String studentId) {
        Class aClass = classRepository.findById(classId).orElse(null);
        if (aClass == null || aClass.getAttendanceList() == null || aClass.getAttendanceList().isEmpty()) {
            return 0.0;
        }
        int totalSessions = aClass.getAttendanceList().size();
        Long attendedSessions = attendance_studentRepository.countByAttendance_aClass_IdAndStudent_Id(classId,studentId);
        return ((double) attendedSessions / totalSessions) * 100;
    }

    public void writeAttendanceDataToExcel(Long classId, String filePath) {
        try (Workbook workbook = new XSSFWorkbook()) {
            Sheet sheet = workbook.createSheet("Thống kê đểm danh");

            // Create headers
            Row headerRow = sheet.createRow(0);
            headerRow.createCell(0).setCellValue("MSSV");
            headerRow.createCell(1).setCellValue("Họ và tên");
            headerRow.createCell(2).setCellValue("(%)");
            List<Student> students = studentRepository.findByStudentList_Aclass_Id(classId);
            // Fill data
            int rowNum = 1;
            for (Student student : students) {
                Row row = sheet.createRow(rowNum++);
                row.createCell(0).setCellValue(student.getId());
                row.createCell(1).setCellValue(student.getName());
                double countTong = calculateAttendanceRate(classId,student.getId());
                row.createCell(2).setCellValue(countTong);
                // Assuming attendance rate is stored in student object
                System.out.println("Tong: "+ countTong);
            }

            // Auto-size columns
            for (int i = 0; i < 3; i++) {
                sheet.autoSizeColumn(i);
            }

            // Write the workbook to the file system
            try (FileOutputStream fos = new FileOutputStream(filePath)) {
                workbook.write(fos);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
