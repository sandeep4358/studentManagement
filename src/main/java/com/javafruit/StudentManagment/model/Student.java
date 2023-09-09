package com.javafruit.StudentManagment.model;

import jakarta.persistence.*;
import lombok.*;

/**
 * parentName: Represents the name of the parent or guardian of the student.
 * parentContact: Represents the contact information of the parent or guardian.
 * studentClass: Represents the class or grade in which the student is enrolled.
 */
@Entity
@Table(name = "student_mst")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String rollNumber;
    private int age;
    private String parentName;
    private String parentContact;
    private String studentClass;

}
