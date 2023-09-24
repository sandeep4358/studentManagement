package com.javafruit.StudentManagment.repo;

import com.javafruit.StudentManagment.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    // Define custom queries if needed
}

