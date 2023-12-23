package com.javafruit.StudentManagment.service.impl;


import com.javafruit.StudentManagment.dto.StudentDto;
import com.javafruit.StudentManagment.exception.StudentException;
import com.javafruit.StudentManagment.exception.StudentNotFoundException;
import com.javafruit.StudentManagment.model.Book;
import com.javafruit.StudentManagment.model.Student;
import com.javafruit.StudentManagment.repo.BookRepository;
import com.javafruit.StudentManagment.repo.StudentRepository;
import com.javafruit.StudentManagment.service.StudentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
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
       // @Cacheable(value = "myCache", keyGenerator = "myCacheKeyGenerator", unless="#result == null")  // added to the
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
       // @CachePut(value = "myCache", key = "#studentDto.id", unless="#result == null")  // added to the
        public StudentDto updateStudent(StudentDto studentDto) {

                //Student build = Student.builder().age(student.getAge()).name(student.getName()).age(student.getAge()).parentContact(student.getParentContact()).parentName(student.getParentName()).rollNumber(student.getRollNumber()).build();
                // Implement updating a student in the database

                Optional<Student> studentOptional = repository.findById(studentDto.getId());
                Student student1 = null;
                if(studentOptional.isPresent()) {
                        student1 = studentOptional.get();
                }else {
                        student1 = new Student();
                }
                        student1.setAge(studentDto.getAge());
                        student1.setName(studentDto.getName());
                        student1.setRollNumber(studentDto.getRollNumber());
                        student1.setParentName(studentDto.getRollNumber());
                        student1.setParentContact(studentDto.getParentContact());
                        student1.setParentName(studentDto.getParentName());
                        student1.setStudentClass(studentDto.getStudentClass());

                        Student saveRecord = repository.save(student1);
                        log.info("<< StudentService : Student update : Existing update ");
                        return StudentDto.builder().age(saveRecord.getAge()).name(saveRecord.getName()).age(saveRecord.getAge())
                                .parentContact(saveRecord.getParentContact()).parentName(saveRecord.getParentName())
                                .rollNumber(saveRecord.getRollNumber()).studentClass(saveRecord.getStudentClass()).id(studentDto.getId()).build();

//                }else {
//                        log.debug("<< StudentService : Student with this id does not exist : Exiting update");
//                        throw new StudentNotFoundException("Student with this id does not exist");
//                }




        }

        @Transactional
        @Override
       // @CachePut(value = "Student",key="fields.concat('-').concat(#.id)")
        @CachePut(value = "myCache", key = "#id", unless="#result == null")
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
                        log.debug("<< StudentService : Student updated : Exiting update");

                } else {
                        log.debug("<< StudentService : Student with this id does not exist : Exiting update");
                        throw new StudentNotFoundException("Student with this id does not exist");
                }


                return StudentDto.builder().age(saveRecord.getAge()).name(saveRecord.getName()).age(saveRecord.getAge())
                        .parentContact(saveRecord.getParentContact()).parentName(saveRecord.getParentName())
                        .rollNumber(saveRecord.getRollNumber()).studentClass(saveRecord.getStudentClass()).id(saveRecord.getId()).build();

                //return saveRecord;

        }
        @Override
        public void deleteStudent(int studentId) {
            // Implement deleting a student from the database
        }

        @Override
        //@Cacheable(value = "myCache", key = "#studentId", unless="#result == null")  // added to the
        public StudentDto getStudentById(Long studentId) {

                Optional<Student> studentOptional = repository.findById(studentId);
                if(studentOptional.isPresent()){
                        log.info(">> StudentServiceImpl : Find Student with Id : Present");
                        Student fetchedRecord =studentOptional.get();
                        return StudentDto.builder().age(fetchedRecord.getAge()).name(fetchedRecord.getName()).age(fetchedRecord.getAge())
                                .parentContact(fetchedRecord.getParentContact()).parentName(fetchedRecord.getParentName())
                                .rollNumber(fetchedRecord.getRollNumber()).studentClass(fetchedRecord.getStudentClass()).id(fetchedRecord.getId()).build();

                }else {
                        log.info("<< StudentServiceImpl : Find Student with Id : Present");
                        throw new StudentNotFoundException("Student not found with id");
                }


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
       // @Cacheable(value = "myCache", keyGenerator = "myCacheKeyGenerator", unless="#result == null")  // added to the
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
