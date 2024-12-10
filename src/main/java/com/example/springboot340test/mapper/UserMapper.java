package com.example.springboot340test.mapper;

import com.example.springboot340test.bean.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {

    List<User> queryAll();
}
