package com.example.springboot340test;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.AutowiredAnnotationBeanPostProcessor;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationListener;

@SpringBootTest
class Springboot340TestApplicationTests {

    @Test
    void contextLoads() {

        AutowiredAnnotationBeanPostProcessor autowiredAnnotationBeanPostProcessor=null;
        ApplicationListener configFileApplicationListener =null;
    }

}
