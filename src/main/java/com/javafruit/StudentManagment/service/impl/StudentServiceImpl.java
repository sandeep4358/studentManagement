package com.javafruit.StudentManagment.service.impl;


import com.javafruit.StudentManagment.exception.StudentException;
import com.javafruit.StudentManagment.model.Student;
import com.javafruit.StudentManagment.repo.StudentRepository;
import com.javafruit.StudentManagment.service.StudentService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {

        final private StudentRepository repository;
        @Override
        public void addStudent(Student student) {
            // Implement adding a student to the database
        }

        public void addListOfStudents(List<Student> studentList){
        log.info("enter");
        try{
                repository.saveAll(studentList);
        }catch (Exception ex){
                throw new StudentException(ex.getMessage());
        }
        }
        @Transactional
        @Override
        public void updateStudent(Student student) {
            // Implement updating a student in the database
        }

        @Override
        public void deleteStudent(int studentId) {
            // Implement deleting a student from the database
        }

        @Override
        public Student getStudentById(int studentId) {
            // Implement getting a student by ID from the database
            return null;
        }

        @Override
        public List<Student> getAllStudents() {
            // Implement getting all students from the database
            return null;
        }
    }
