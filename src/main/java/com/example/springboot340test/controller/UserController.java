package com.example.springboot340test.controller;

import com.example.springboot340test.annotation.UserProcessor;
import com.example.springboot340test.annotation.UserProcessorHandler;
import com.example.springboot340test.object.dto.UserDTO;
import com.example.springboot340test.object.vo.UserVO;
import com.example.springboot340test.service.UserService;
import com.jing.utils.AnnotationScannerUtils;
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

    @GetMapping("get-user")
    public UserVO getUser(){
        UserVO user = new UserVO();
        user.setName("徐徐");
        user.setSex(1);
        List<Class> list =AnnotationScannerUtils.scanClass("com.example.springboot340test.annotation", UserProcessor.class);
        list.forEach(c->{
            try {
                UserProcessorHandler p = (UserProcessorHandler) c.newInstance();
                p.process(user);
                System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
            } catch (InstantiationException e) {
                throw new RuntimeException(e);
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        });
        return user;
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
