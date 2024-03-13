package com.vn.edu.attendance_be.controller;


import com.vn.edu.attendance_be.domain.Class;
import com.vn.edu.attendance_be.domain.Teacher;
import com.vn.edu.attendance_be.dto.ClassDto;
import com.vn.edu.attendance_be.service.ClassService;
import com.vn.edu.attendance_be.service.MapValidationErrorService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/v1/classes")
public class ClassController {
    @Autowired
    ClassService classService;

    @Autowired
    MapValidationErrorService mapValidationErrorService;


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

    @GetMapping("/{id}/get")
    public  ResponseEntity<?> getClass(@PathVariable("id") Long id){
        return new ResponseEntity<>(classService.findById(id),HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteClass(@PathVariable("id") Long id)
    {

        classService.deleteById(id);

        return  new ResponseEntity<>("Lớp có id " + id + " đã được xóa",HttpStatus.OK);
    }
}
