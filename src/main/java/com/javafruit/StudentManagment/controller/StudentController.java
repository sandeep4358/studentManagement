package com.javafruit.StudentManagment.controller;

import com.javafruit.StudentManagment.dto.RequestObject;
import com.javafruit.StudentManagment.dto.ResponseObject;
import com.javafruit.StudentManagment.dto.StudentDto;
import com.javafruit.StudentManagment.exception.StudentAlreadyPresent;
import com.javafruit.StudentManagment.exception.StudentNotFoundException;
import com.javafruit.StudentManagment.model.Student;
import com.javafruit.StudentManagment.repo.StudentRepository;
import com.javafruit.StudentManagment.service.StudentService;
import jakarta.validation.Valid;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

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

    @GetMapping
    @RequestMapping("/welcome")
    public ResponseEntity getGreetings(){
        log.info("enter :: " );
        Object obj = null;

        return new ResponseEntity("Welcome to the MPS school !! " + new Date(),HttpStatus.FOUND);
    }


    @GetMapping
    @RequestMapping("/fetchById/{id}")
    public ResponseEntity getStudents(@PathVariable String id){
        log.info(">> StudentController : getStudent : /fetchById/{id}" );
        Object obj = null;
        if(id == "" ||id == null){
            log.info(">> StudentController : getStudent : Student id is Null " );
            throw new StudentNotFoundException("Student id is Null :: ");
        }

        StudentDto student = service.getStudentById(Long.parseLong(id));

        return new ResponseEntity(new ResponseObject(0,student,"00"),HttpStatus.FOUND);
    }

    @PostMapping(value= "/save")
    public ResponseEntity saveStudentDetails(@Valid @RequestBody RequestObject requestObject){
        log.info("enter");

        /**
         * will create the student from the request object.
         */
        Student student = Student.builder().name(requestObject.getName()).rollNumber(requestObject.getRollNumber()).build();

        ResponseEntity responseEntity = null;
        if(checkTheRedendencyOfStudent(student.getRollNumber())){
            repository.save(student);
            responseEntity =  new ResponseEntity<>(ResponseObject.builder().data(student).build() , HttpStatus.ACCEPTED);
        }else{
            log.error("Student is already Present with roll no "+ student.getRollNumber());
            throw new StudentAlreadyPresent("Student is already Present with roll no "+ student.getRollNumber());

        }

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
        log.info(">> enter");
        service.addListOfStudents(requestObject.getListOfStudent());
        return  new ResponseEntity("Student saved successfully",HttpStatus.ACCEPTED);
    }

    @GetMapping
    @RequestMapping("/fetchSortedResult/{sortedString}")

    public ResponseEntity getTheSortedList(@PathVariable String sortedString){
        log.info("Entered");
        List<Student> studentList = service.fetchTheSortedList(sortedString);
        return new ResponseEntity<>(new ResponseObject(Integer.parseInt(studentList.stream().count()+""),studentList,"00"),HttpStatus.ACCEPTED);
    }

    @GetMapping
    @RequestMapping("/fetchPaginatedResult/{offSet}/{pageSize}")
    public ResponseEntity getPaginatedResult(@PathVariable String offSet,@PathVariable String pageSize){
        log.info("Entered");
        Page<Student> students = service.fetchPaginatedResult(offSet, pageSize);
        return  new ResponseEntity<>(new ResponseObject( students.getSize(),students,"00"),HttpStatus.ACCEPTED);
    }

 @GetMapping
    @RequestMapping("/fetchPaginateSorted/{offSet}/{pageSize}/{sortingString}")
    public ResponseEntity getPaginatedSortedResult(@PathVariable String offSet,@PathVariable String pageSize,@PathVariable String sortingString){
        log.info("Entered");
        Page<Student> students = service.fetchSorteAndPaginatedResult(offSet, pageSize,sortingString);
        return  new ResponseEntity<>(new ResponseObject(students.getSize(),students,"00"),HttpStatus.ACCEPTED);
    }

    @PutMapping("/updateStudent")
    public ResponseEntity upadteStudent(@Valid @RequestBody RequestObject requestObject){
     log.info("Enter");


       ResponseObject responseObject = new ResponseObject();
        StudentDto studentDto = service.updateStudent(requestObject.getStudent());

        return  new ResponseEntity(new ResponseObject(0,studentDto,"00"), HttpStatus.ACCEPTED);

    }

    /**
     * It is basically a patch request that will update only the file tha is needed to be updated.
     * @param id
     * @param fields
     * @return
     */

    @PatchMapping("/updateRecordByFields/{id}")
    public ResponseEntity updateProductFields(@PathVariable int id,@RequestBody Map<String, Object> fields){
        log.info(">> StudentController : updateProductFields : /updateRecordByFields/{id}" );
        StudentDto studentDto = service.updateRecordByFields(id, fields);
        return  new ResponseEntity(new ResponseObject(0,studentDto,"00"), HttpStatus.ACCEPTED);
    }
}
