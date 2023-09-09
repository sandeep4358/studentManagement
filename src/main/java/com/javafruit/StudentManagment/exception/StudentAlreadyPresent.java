package com.javafruit.StudentManagment.exception;

public class StudentAlreadyPresent extends RuntimeException{
    public StudentAlreadyPresent(String message){
        super(message);
    }
}
