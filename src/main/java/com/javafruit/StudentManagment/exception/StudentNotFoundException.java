package com.javafruit.StudentManagment.exception;

public class StudentNotFoundException extends  RuntimeException{
    StudentNotFoundException(String message){
        super(message);
    }
}
