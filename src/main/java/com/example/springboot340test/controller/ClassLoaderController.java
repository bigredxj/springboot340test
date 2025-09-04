package com.example.springboot340test.controller;

import com.example.springboot340test.service.ClassLoaderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping( "/classloader")

public class ClassLoaderController {

    @Autowired
    ClassLoaderService classLoaderService;

    @GetMapping(value = "/get-name")
    public String getName(){
        return  classLoaderService.getName();

    }
}
