package com.javafruit.StudentManagment.model;


import jakarta.persistence.*;
import lombok.*;


@Entity
@Data
@Builder
@Table(name = "user_mst")
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @Column(unique = true)
    private String email;

    @Version
    private Long version;
    private String about;
    private String password;

    // getters and setters
}

