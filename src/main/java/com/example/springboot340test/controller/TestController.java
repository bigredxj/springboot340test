package com.example.springboot340test.controller;

import com.example.springboot340test.bean.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @GetMapping(value = "/health")
    public void health(){

    }

    @GetMapping(value = "/user")
    public User hello(){
        User user = new User();
        user.setName("小明");
        user.setAge(30);
        return user;
    }
}
