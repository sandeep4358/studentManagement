package com.javafruit.StudentManagment.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Builder
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String author;
    private String isbn;
    @Version
    private Long version;
}
