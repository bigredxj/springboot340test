package com.example.springboot340test.controller;

import com.example.springboot340test.enums.ResultEnum;
import com.example.springboot340test.exception.GeneralException;
import com.example.springboot340test.object.vo.UserVO;
import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.Gauge;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.prometheusmetrics.PrometheusCounter;
import io.micrometer.prometheusmetrics.PrometheusMeterRegistry;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.Field;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.DoubleAdder;

@RestController
@RequestMapping( "/test")

public class TestController {

    @Autowired
    private MeterRegistry meterRegistry;
    private  Counter counter;
    private  Gauge gauge;
    AtomicInteger atomicInteger = new AtomicInteger();

    @Value("${my.age}")
    private Integer myAge;


    @PostConstruct
    public void init(){
        counter = Counter.builder("my-count")  //名称
                .baseUnit("unit") //基础单位
                .description("my test count") //描述
                .tag("aaa", "bbb") //标签
                .tag("ccc","dddd")//标签
                .register(meterRegistry);//绑定的MeterRegistry



        gauge = Gauge.builder("my-gauge", atomicInteger, AtomicInteger::get)
                .tag("g", "my-g")
                .description("my a gauge")
                .register(meterRegistry);
        atomicInteger.addAndGet(5);

    }

    @GetMapping(value = "/my-age")
    public Integer getMyAge(){
       return myAge;

    }

    @GetMapping(value = "/add-user-counter")
    public void addUser(){
        counter.increment();
        atomicInteger.incrementAndGet();

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
    public UserVO hello(){
        UserVO user = new UserVO();
        user.setName("小明");
        user.setSex(1);
        return user;
    }

    @GetMapping(value = "/get-exception")
    public UserVO getException(){
        UserVO user = new UserVO();
        user.setName("小明");
        user.setSex(1);
        int i=0;
        int j= 9/i;
        return user;
    }

    @GetMapping(value = "/get-general-exception")
    public UserVO getGeneralException(){
        UserVO user = new UserVO();
        user.setName("小明");
        user.setSex(1);
        if (user.getSex()==1) {
            throw new GeneralException(ResultEnum.SYSTEM_ERROR);
        }
        return user;
    }
}
