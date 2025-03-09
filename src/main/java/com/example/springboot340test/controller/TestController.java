package com.example.springboot340test.controller;

import com.example.springboot340test.bean.User;
import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.prometheusmetrics.PrometheusCounter;
import io.micrometer.prometheusmetrics.PrometheusMeterRegistry;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.Field;
import java.util.concurrent.atomic.DoubleAdder;

@RestController
public class TestController {

    @Autowired
    private MeterRegistry meterRegistry;
    private  Counter counter;
    @PostConstruct
    public void init(){
        counter = Counter.builder("my-count")  //名称
                .baseUnit("unit") //基础单位
                .description("my test count") //描述
                .tag("aaa", "bbb") //标签
                .tag("ccc","dddd")//标签
                .register(meterRegistry);//绑定的MeterRegistry
    }

    @GetMapping(value = "/add-user-counter")
    public void getUser(){
        counter.increment();
    }

    @GetMapping(value = "/clear-user-counter")
    public void clearUser(){
        Class c = PrometheusCounter.class;
        try {
            Field count = c.getDeclaredField("count");
            count.setAccessible(true);
            count.set(counter,new DoubleAdder());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }



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
