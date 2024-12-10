package com.example.springboot340test.service;

import com.example.springboot340test.bean.User;
import com.example.springboot340test.mapper.UserMapper;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Slf4j
@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    public List<User> allUsers(){
        List<User> users = userMapper.queryAll();
        //log.info("查询全部数据:{}",users);
        return users;
    }
}
