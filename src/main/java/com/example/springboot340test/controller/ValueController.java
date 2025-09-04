package com.example.springboot340test.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping( "/value")
public class ValueController {
    @Value("${my.name}")
    private String myName;

    @Value("${my.age}")
    private Integer myAge;

    @Value("${my.sex}")
    private Boolean mySex;

    @GetMapping(value = "/my-name")
    public String getMyName(){
        return myName;

    }

    @GetMapping(value = "/my-sex")
    public Boolean getMySex(){
        return mySex;

    }

    @GetMapping(value = "/my-age")
    public Integer getMyAge(){
        return myAge;

    }
}
