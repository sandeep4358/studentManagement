package com.javafruit.StudentManagment.controller;

import com.javafruit.StudentManagment.dto.RequestObject;
import com.javafruit.StudentManagment.dto.ResponseObject;
import com.javafruit.StudentManagment.exception.StudentAlreadyPresent;
import com.javafruit.StudentManagment.model.Student;
import com.javafruit.StudentManagment.repo.StudentRepository;
import com.javafruit.StudentManagment.service.StudentService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/student")
@RequiredArgsConstructor
@Slf4j
public class StudentController {

    final private StudentRepository repository;

    final private StudentService service;
    @GetMapping
    @RequestMapping("/fetch")
    public ResponseEntity getListOfStudents(){
        log.info("enter :: " );
        Object obj = null;
        obj =  repository.findAll();
        return new ResponseEntity(obj,HttpStatus.FOUND);
    }

    @PostMapping(value= "/save")
    public ResponseEntity saveStudentDetails(@RequestBody RequestObject requestObject){
        log.info("enter");

        /**
         * will create the student from the request object.
         */
        Student student = Student.builder().name(requestObject.getName()).rollNumber(requestObject.getRollNumber()).build();

        ResponseEntity responseEntity = null;
        if(checkTheRedendencyOfStudent(student.getRollNumber())){
            repository.save(student);
            responseEntity =  new ResponseEntity<>(ResponseObject.builder().data(student).build() , HttpStatus.ACCEPTED);
        }else
            throw new StudentAlreadyPresent("Student is already Present with roll no "+ student.getRollNumber());

        return responseEntity;
    }

    private boolean checkTheRedendencyOfStudent(String rollNumber){
        Optional<Student> student =  repository.findByRollNumber(rollNumber);
        if(student.isPresent())
            return false;
        else
            return true;
    }

    /**
     * adding student list in the database
     */



    @PostMapping
    @RequestMapping("/saveList")
    public ResponseEntity saveStudents(@RequestBody RequestObject requestObject) {
        log.info("enter");
        service.addListOfStudents(requestObject.getListOfStudent());
        return  new ResponseEntity("Student saved successfully",HttpStatus.ACCEPTED);
    }

    @GetMapping
    @RequestMapping("/fetchSortedResult/{sortedString}")

    public ResponseEntity getTheSortedList(@PathVariable String sortedString){
        log.info("Entered");
        List<Student> studentList = service.fetchTheSortedList(sortedString);
        return new ResponseEntity<>(new ResponseObject(Integer.parseInt(studentList.stream().count()+""),studentList),HttpStatus.ACCEPTED);
    }

    @GetMapping
    @RequestMapping("/fetchPaginatedResult/{offSet}/{pageSize}")
    public ResponseEntity getPaginatedResult(@PathVariable String offSet,@PathVariable String pageSize){
        log.info("Entered");
        Page<Student> students = service.fetchPaginatedResult(offSet, pageSize);
        return  new ResponseEntity<>(new ResponseObject( students.getSize(),students),HttpStatus.ACCEPTED);
    }

 @GetMapping
    @RequestMapping("/fetchPaginateSorted/{offSet}/{pageSize}/{sortingString}")
    public ResponseEntity getPaginatedSortedResult(@PathVariable String offSet,@PathVariable String pageSize,@PathVariable String sortingString){
        log.info("Entered");
        Page<Student> students = service.fetchSorteAndPaginatedResult(offSet, pageSize,sortingString);
        return  new ResponseEntity<>(new ResponseObject(students.getSize(),students),HttpStatus.ACCEPTED);
    }
}
