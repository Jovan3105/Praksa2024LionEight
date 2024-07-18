package com.example.demo.exceptions;

import org.springframework.http.HttpStatus;

public class CustomException extends RuntimeException{
    private final HttpStatus status;
    public CustomException(String message, HttpStatus httpStatus) {
        super(message);
        this.status = httpStatus;

    }
    public HttpStatus getStatus(){
        return status;
    }
}
