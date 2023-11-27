package com.javafruit.StudentManagment.repo;

import com.javafruit.StudentManagment.model.Student;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

      @Lock(LockModeType.OPTIMISTIC)
      Optional<Student> findByRollNumber(String rollNumber);
      @Lock(LockModeType.OPTIMISTIC)
      Optional<Student> findByName(String name);



}
