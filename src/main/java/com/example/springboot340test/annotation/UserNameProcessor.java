package com.example.springboot340test.annotation;

import com.example.springboot340test.object.vo.UserVO;

/**
 * @Description
 * @Author xujing
 * @Data 2025/9/5 18:33
 */
@UserProcessor
public class UserNameProcessor implements UserProcessorHandler{
    @Override
    public void process(UserVO vo) {
        vo.setName("<<"+vo.getName()+">>");
    }
}
