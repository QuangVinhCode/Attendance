package com.vn.edu.attendance_be.controller;


import com.vn.edu.attendance_be.domain.Class;
import com.vn.edu.attendance_be.domain.Teacher;
import com.vn.edu.attendance_be.dto.ClassDto;
import com.vn.edu.attendance_be.exeception.ClassException;
import com.vn.edu.attendance_be.service.ClassService;
import com.vn.edu.attendance_be.service.MapValidationErrorService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;


@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/v1/classes")
public class ClassController {
    @Autowired
    ClassService classService;

    @Autowired
    MapValidationErrorService mapValidationErrorService;

    @Value("${file.upload-filepdf-dir}") // Đường dẫn tới thư mục lưu trữ file, được đặt trong application.properties
    private String fileStoragePath;

    @PostMapping
    public ResponseEntity<?> createClass(@Validated @RequestBody ClassDto dto, BindingResult result){

       ResponseEntity<?> responseEntity = mapValidationErrorService.mapValidationFields(result);

       if (responseEntity != null)
       {
           return responseEntity;
       }

        classService.save(dto);



        return new ResponseEntity<>(dto, HttpStatus.CREATED);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<?> updateClass(@PathVariable("id") Long id, @RequestBody ClassDto dto){


        classService.update(id,dto);

        return new ResponseEntity<>(dto, HttpStatus.CREATED);
    }

    @GetMapping()
    public ResponseEntity<?> getClasses(){
        return new ResponseEntity<>(classService.findAll(),HttpStatus.OK);
    }
    @GetMapping("/student/{id}")
    public ResponseEntity<?> getClassesByStudent(@PathVariable("id") String id){
        return new ResponseEntity<>(classService.findAllByStudent(id),HttpStatus.OK);
    }
    @GetMapping("/{id}/get")
    public  ResponseEntity<?> getClass(@PathVariable("id") Long id){
        return new ResponseEntity<>(classService.findById(id),HttpStatus.OK);
    }

    @PostMapping("/excel/{classId}")
    public  ResponseEntity<?> getExcel(@PathVariable("classId") Long classId) throws FileNotFoundException {
           System.out.println("DC "+  fileStoragePath);
            classService.writeAttendanceDataToExcel(classId,fileStoragePath + "/thongke.xlsx");


        // Tạo một InputStream từ file Excel
        InputStream inputStream = new FileInputStream(fileStoragePath + "/thongke.xlsx");

        // Tạo một InputStreamResource từ InputStream
        InputStreamResource resource = new InputStreamResource(inputStream);

        // Trả về một phản hồi với file đính kèm
        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"file.xlsx\"")
                .body(resource);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteClass(@PathVariable("id") Long id)
    {

        classService.deleteById(id);

        return  new ResponseEntity<>("Lớp có id " + id + " đã được xóa",HttpStatus.OK);
    }
}
