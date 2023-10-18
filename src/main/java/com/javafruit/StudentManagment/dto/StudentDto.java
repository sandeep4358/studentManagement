package com.javafruit.StudentManagment.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StudentDto {


    private Long id;
    private String name;
    private String rollNumber;
    private int age;
    private String parentName;
    private String parentContact;
    private String studentClass;

}
