package com.example.springboot340test.controller;

import com.example.springboot340test.object.dto.UserDTO;
import com.example.springboot340test.object.vo.UserVO;
import com.example.springboot340test.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping( "/user")
public class UserController {
    @Autowired
    UserService userService;

    @GetMapping("all-users")
    public List<UserVO> allUser(){
        return userService.listUser(null);
    }


    @GetMapping(value = "/add-user")

    public void queryUser(@RequestParam("name")String name){

        int i = 0;
        int j = 9/i;
    }

    @PostMapping(value = "/add-user")
    public void addUser(@RequestBody UserDTO vo){

    }
}
