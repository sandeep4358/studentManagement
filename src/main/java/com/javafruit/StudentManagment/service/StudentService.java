package com.javafruit.StudentManagment.service;

import com.javafruit.StudentManagment.model.Book;
import com.javafruit.StudentManagment.model.Student;
import com.javafruit.StudentManagment.repo.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public interface StudentService {
    void addListOfStudents(List<Student> studentList);
    void addStudent(Student student);
    void updateStudent(Student student);
    void deleteStudent(int studentId);
    Student getStudentById(int studentId);
    List<Student> getAllStudents();

    public List<Book> getAllBooks() ;

    public Book getBookById(Long id);

    public Book createBook(Book book) ;

    public void deleteBook(Long id);
}
