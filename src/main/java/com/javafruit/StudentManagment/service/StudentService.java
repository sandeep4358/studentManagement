package com.javafruit.StudentManagment.service;

import com.javafruit.StudentManagment.model.Student;

import java.util.List;

public interface StudentService {
    void addListOfStudents(List<Student> studentList);
    void addStudent(Student student);
    void updateStudent(Student student);
    void deleteStudent(int studentId);
    Student getStudentById(int studentId);
    List<Student> getAllStudents();
}
