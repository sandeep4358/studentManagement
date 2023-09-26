package com.javafruit.StudentManagment.service;

import com.javafruit.StudentManagment.exception.UserAlreadyExistException;
import com.javafruit.StudentManagment.model.User;
import com.javafruit.StudentManagment.repo.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void registerUser(User user) {
        log.info("entered");
        // Check if the username is already taken
        if (userRepository.findByName(user.getName()) != null) {
            throw new UserAlreadyExistException("Username already exists");        }

        // Encrypt the password before saving it to the database
        user.setPassword(user.getPassword());

        userRepository.save(user);

    }
}
