package com.example.springboot340test.mapper;

import com.example.springboot340test.object.po.UserPO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserMapper {

    Integer insertUser(UserPO userPO);

    Integer batchInsertUser(List<UserPO> list);

    Integer deleteUserById(Integer id);

    Integer deleteUser(UserPO userPO);

    Integer updateUser(UserPO userPO);

    Integer updateUserById(UserPO userPO);

    UserPO getUserById(Integer id);

    List<UserPO> listUser(UserPO userPO);

    List<UserPO> listUserByLimit(@Param("start") Integer start, @Param("end") Integer end);
}
