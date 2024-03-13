package com.vn.edu.attendance_be.exeception;

import lombok.Data;

@Data
public class ClassExceptionResponse {
    private String message;

    public ClassExceptionResponse(String message) {
        this.message = message;
    }
}
