package com.javafruit.StudentManagment.repo;


import com.javafruit.StudentManagment.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByName(String username);
}