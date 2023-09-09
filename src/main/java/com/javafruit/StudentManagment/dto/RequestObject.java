package com.javafruit.StudentManagment.dto;

import com.javafruit.StudentManagment.model.Student;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
@Builder
public class RequestObject {
    private String name;
    private String rollNumber;
    private List<Student> listOfStudent;

}
