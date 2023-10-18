package com.javafruit.StudentManagment.exception;


import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class CustomGlobalExceptionHandler {
    @ExceptionHandler
    public ResponseEntity studentNotFound(StudentNotFoundException exception){
      log.error(exception.getMessage(), HttpStatus.EXPECTATION_FAILED);
            return new ResponseEntity(exception.getMessage(), HttpStatus.EXPECTATION_FAILED);
    }

    @ExceptionHandler
    public ResponseEntity duplicateStudent(StudentAlreadyPresent exception){
        log.error(exception.getMessage(), HttpStatus.EXPECTATION_FAILED);
        return new ResponseEntity(exception.getMessage(), HttpStatus.EXPECTATION_FAILED);
    }


    @ExceptionHandler
    public ResponseEntity generalStudentException(StudentException exception){
        log.error(exception.getMessage(), HttpStatus.EXPECTATION_FAILED);
        return new ResponseEntity(exception.getMessage(), HttpStatus.EXPECTATION_FAILED);
    }

    @ExceptionHandler
    public ResponseEntity generalStudentException(UserAlreadyExistException exception){
        log.error(exception.getMessage(), HttpStatus.EXPECTATION_FAILED);
        return new ResponseEntity(exception.getMessage(), HttpStatus.EXPECTATION_FAILED);
    }

}
