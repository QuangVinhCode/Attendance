package com.vn.edu.attendance_be.exeception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ClassException extends  RuntimeException{
    public ClassException(String message) {
        super(message);
    }
}
