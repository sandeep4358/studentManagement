package com.javafruit.StudentManagment.dto;

import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StudentDto implements Serializable {

    static final long serialVersionUID = 42L;

    private Long id;
    @NotNull
    private String name;

    @NotNull
    private String rollNumber;
    @Min(3)
    @Max(25)
    private int age;
    @NotNull
    private String parentName;
    @NotNull
    private String parentContact;
    @NotNull    private String studentClass;

}
