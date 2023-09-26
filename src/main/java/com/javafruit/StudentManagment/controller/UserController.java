package com.javafruit.StudentManagment.controller;

import com.javafruit.StudentManagment.model.User;
import com.javafruit.StudentManagment.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/auth")
@Slf4j
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/registerUser")
    public String registerUser(@RequestBody User user) {
      log.info("entered");
        userService.registerUser(user);
        return "Success";
    }
}
