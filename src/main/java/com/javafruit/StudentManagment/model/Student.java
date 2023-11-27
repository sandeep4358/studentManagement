package com.javafruit.StudentManagment.model;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

/**
 * parentName: Represents the name of the parent or guardian of the student.
 * parentContact: Represents the contact information of the parent or guardian.
 * studentClass: Represents the class or grade in which the student is enrolled.
 */
@Entity
@Table(name = "student_mst",uniqueConstraints={@UniqueConstraint(columnNames={"rollNumber"})})
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Student implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String rollNumber;
    private int age;
    @Version
    private Long version;
    private String parentName;
    private String parentContact;
    private String studentClass;

}
