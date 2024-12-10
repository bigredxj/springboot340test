package com.example.springboot340test.controller;

import com.example.springboot340test.bean.User;
import com.example.springboot340test.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {
    @Autowired
    UserService userService;

    @GetMapping("all-users")
    public List<User> allUser(){
        return userService.allUsers();
    }

}
