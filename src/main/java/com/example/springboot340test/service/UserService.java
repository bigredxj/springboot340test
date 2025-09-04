package com.example.springboot340test.service;

import com.example.springboot340test.enums.ResultEnum;
import com.example.springboot340test.exception.GeneralException;
import com.example.springboot340test.mapper.UserMapper;

import com.example.springboot340test.object.dto.UserDTO;
import com.example.springboot340test.object.po.UserPO;
import com.example.springboot340test.object.vo.UserVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Slf4j
@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    public Integer insertUser(UserDTO userDTO) {
        Integer insertResult=0;
        UserPO userPO = new UserPO();
        insertResult = userMapper.insertUser(userPO);
        if (insertResult == 0) {
            log.error("insert user failed");
            throw new GeneralException(ResultEnum.OPERATION_FAILED);
        }
        return insertResult;
    }

    public List<UserVO> listUser(UserDTO userDTO) {
        List<UserVO> resultList= new ArrayList<>();
        UserPO userPO = new UserPO();
        List<UserPO> userPOList = new ArrayList<>();
        userPOList = userMapper.listUser(userPO);
        return resultList;
    }

    public List<UserVO> listUserByLimit(Integer start, Integer end) {
        List<UserVO> resultList= new ArrayList<>();
        List<UserPO> userPOList = new ArrayList<>();
        userPOList = userMapper.listUserByLimit(start,end);
        return resultList;
    }

    public Integer updateUser(UserDTO userDTO) {
        Integer updateResult=0;
        UserPO userPO = new UserPO();
        updateResult = userMapper.updateUser(userPO);
        if (updateResult == 0) {
            log.error("update user failed");
            throw new GeneralException(ResultEnum.OPERATION_FAILED);
        }
        return updateResult;
    }

    public Integer deleteUser(UserDTO userDTO) {
        Integer deleteResult=0;
        UserPO userPO = new UserPO();
        deleteResult = userMapper.deleteUser(userPO);
        if (deleteResult == 0) {
            log.error(" delete user failed");
            throw new GeneralException(ResultEnum.OPERATION_FAILED);
        }
        return deleteResult;
    }
}
