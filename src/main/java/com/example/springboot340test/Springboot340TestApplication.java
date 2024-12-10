package com.example.springboot340test;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.example.springboot340test.mapper")
public class Springboot340TestApplication {

    public static void main(String[] args) {
        SpringApplication.run(Springboot340TestApplication.class, args);
    }

}
