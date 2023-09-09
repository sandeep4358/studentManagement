package com.javafruit.StudentManagment.repo;

import com.javafruit.StudentManagment.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
      Optional<Student> findByRollNumber(String rollNumber);
      Optional<Student> findByName(String name);


}
