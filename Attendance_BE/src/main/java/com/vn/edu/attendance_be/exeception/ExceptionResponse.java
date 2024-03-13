package com.vn.edu.attendance_be.exeception;

import lombok.Data;

@Data
public class ExceptionResponse {
    private String message;

    public ExceptionResponse(String message) {
        this.message = message;
    }
}
