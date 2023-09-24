package com.javafruit.StudentManagment.controller;

import com.javafruit.StudentManagment.model.Book;
import com.javafruit.StudentManagment.service.StudentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/books")
@Slf4j
public class BookController {
    @Autowired
    private StudentService service;

    @GetMapping
    public List<Book> getAllBooks() {
        return service.getAllBooks();
    }

    @GetMapping("/{id}")
    public Book getBookById(@PathVariable Long id) {
        return service.getBookById(id);
    }

    @PostMapping
    public Book createBook(@RequestBody Book book) {
        return service.createBook(book);
    }

    @DeleteMapping("/{id}")
    public void deleteBook(@PathVariable Long id) {
        service.deleteBook(id);
    }

    // Other CRUD operations and endpoints
}