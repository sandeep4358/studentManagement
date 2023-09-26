package com.javafruit.StudentManagment.exception;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class CustomGlobalExceptionHandler {
    @ExceptionHandler
    public ResponseEntity studentNotFound(StudentNotFoundException exception){
            return new ResponseEntity(exception.getMessage(), HttpStatus.EXPECTATION_FAILED);
    }

    @ExceptionHandler
    public ResponseEntity dublicateStudent(StudentAlreadyPresent exception){
        return new ResponseEntity(exception.getMessage(), HttpStatus.EXPECTATION_FAILED);
    }

    @ExceptionHandler
    public ResponseEntity generalStudentException(StudentException exception){
        return new ResponseEntity(exception.getMessage(), HttpStatus.EXPECTATION_FAILED);
    }

    @ExceptionHandler
    public ResponseEntity generalStudentException(UserAlreadyExistException exception){
        return new ResponseEntity(exception.getMessage(), HttpStatus.EXPECTATION_FAILED);
    }

}
