package com.javafruit.StudentManagment.service;

import com.javafruit.StudentManagment.dto.StudentDto;
import com.javafruit.StudentManagment.model.Book;
import com.javafruit.StudentManagment.model.Student;
import com.javafruit.StudentManagment.repo.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Map;

public interface StudentService {
    void addListOfStudents(List<Student> studentList);
    void addStudent(Student student);
    StudentDto updateStudent(StudentDto student);

    public StudentDto updateRecordByFields(int id , Map<String,Object> fields);
    void deleteStudent(int studentId);
    StudentDto getStudentById(Long studentId);
    List<Student> getAllStudents();

    public List<Book> getAllBooks() ;

    public Book getBookById(Long id);

    public Book createBook(Book book) ;

    public void deleteBook(Long id);

    public List<Student> fetchTheSortedList(String sortedString);

    public Page<Student>  fetchSorteAndPaginatedResult(String offset, String pageSize, String sortedString);
    public Page<Student> fetchPaginatedResult(String offset,String pageSize);

}
