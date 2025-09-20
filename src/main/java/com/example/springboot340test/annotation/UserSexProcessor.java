package com.example.springboot340test.annotation;

import com.example.springboot340test.object.vo.UserVO;

/**
 * @Description
 * @Author xujing
 * @Data 2025/9/5 20:35
 */
@UserProcessor
public class UserSexProcessor implements UserProcessorHandler {
    @Override
    public void process(UserVO vo) {
        vo.setSex(100);
    }
}
