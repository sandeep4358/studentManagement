package com.javafruit.StudentManagment.service.impl;


import com.javafruit.StudentManagment.dto.StudentDto;
import com.javafruit.StudentManagment.exception.StudentException;
import com.javafruit.StudentManagment.model.Book;
import com.javafruit.StudentManagment.model.Student;
import com.javafruit.StudentManagment.repo.BookRepository;
import com.javafruit.StudentManagment.repo.StudentRepository;
import com.javafruit.StudentManagment.service.StudentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {

        final private StudentRepository repository;

        final private BookRepository bookRepository;
        @Override
        public void addStudent(Student student) {
            // Implement adding a student to the database
        }

        @Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.READ_COMMITTED,readOnly = true)
        public void addListOfStudents(List<Student> studentList){
        log.info("enter");
        Book book1 = new Book();
        book1.setAuthor("J K Rolling");
        book1.setIsbn("58343493");
        book1.setTitle("Harry Potter and Gob-late of Fire");
        try{
                repository.saveAll(studentList);

                //bookRepository.save(book1);
                createBook(book1);

        }catch (Exception ex){
                throw new StudentException(ex.getMessage());
        }
        }
        @Transactional
        @Override
        public StudentDto updateStudent(StudentDto studentDto) {

                //Student build = Student.builder().age(student.getAge()).name(student.getName()).age(student.getAge()).parentContact(student.getParentContact()).parentName(student.getParentName()).rollNumber(student.getRollNumber()).build();
                // Implement updating a student in the database
                Student student1 = repository.findById(studentDto.getId()).get();
                student1.setAge(studentDto.getAge());
                student1.setName(studentDto.getName());
                student1.setRollNumber(studentDto.getRollNumber());
                student1.setParentName(studentDto.getRollNumber());
                student1.setParentContact(studentDto.getParentContact());
                student1.setParentName(studentDto.getParentName());
                student1.setStudentClass(studentDto.getStudentClass());

                Student saveRecord = repository.save(student1);
                return StudentDto.builder().age(saveRecord.getAge()).name(saveRecord.getName()).age(saveRecord.getAge())
                        .parentContact(saveRecord.getParentContact()).parentName(saveRecord.getParentName())
                        .rollNumber(saveRecord.getRollNumber()).studentClass(saveRecord.getStudentClass()).id(studentDto.getId()).build();

        }

        @Transactional
        @Override
        public StudentDto updateRecordByFields(int id , Map<String,Object> fields) {


                Optional<Student> existingProduct = repository.findById(Long.parseLong(id + ""));
                Student saveRecord = new Student();
                if(existingProduct.isPresent()){
                        fields.forEach((key,value)->{
                                Field field = ReflectionUtils.findField(Student.class, key);
                                field.setAccessible(true);
                                ReflectionUtils.setField(field,existingProduct.get(),value);
                        });
                        saveRecord = repository.save(existingProduct.get());
                }


                return StudentDto.builder().age(saveRecord.getAge()).name(saveRecord.getName()).age(saveRecord.getAge())
                        .parentContact(saveRecord.getParentContact()).parentName(saveRecord.getParentName())
                        .rollNumber(saveRecord.getRollNumber()).studentClass(saveRecord.getStudentClass()).id(saveRecord.getId()).build();

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



        public List<Book> getAllBooks() {
                return bookRepository.findAll();
        }

        public Book getBookById(Long id) {
                return bookRepository.findById(id).orElse(null);
        }

        @Transactional(isolation = Isolation.READ_COMMITTED,propagation = Propagation.REQUIRES_NEW,readOnly = false)
        public Book createBook(Book book) {
                log.info("entered ..");
                return bookRepository.save(book);
        }

        public void deleteBook(Long id) {
                bookRepository.deleteById(id);
        }

        /**
         * Sorted the record Based upon the provided sorting String.
         * @param sortedString
         * @return
         */
        @Override
        public List<Student> fetchTheSortedList(String sortedString) {
                return repository.findAll(Sort.by(sortedString));
        }

        @Override
        public Page<Student> fetchSorteAndPaginatedResult(String offset, String pageSize, String sortedString) {
                Page<Student> records = repository.findAll(PageRequest.of(Integer.parseInt(offset), Integer.parseInt(pageSize)).withSort(Sort.by(sortedString)));
                return records;
        }

        @Override
        public Page<Student> fetchPaginatedResult(String offset, String pageSize) {
                Page<Student> records = repository.findAll(PageRequest.of(Integer.parseInt(offset), Integer.parseInt(pageSize)));
                return records;

        }
}
