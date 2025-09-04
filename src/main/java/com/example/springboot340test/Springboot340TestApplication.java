package com.example.springboot340test;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.context.AnnotationConfigServletWebServerApplicationContext;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
@MapperScan("com.example.springboot340test.mapper")
public class Springboot340TestApplication {

    public static void main(String[] args) {

        AnnotationConfigServletWebServerApplicationContext ac =
                (AnnotationConfigServletWebServerApplicationContext) SpringApplication.run(Springboot340TestApplication.class, args);
        String acName = ac.getApplicationName();
        System.out.println(">>>>>>>>>>>>>>>>" + acName);
    }

}
