package com.example.springboot340test.exception;

import com.example.springboot340test.enums.ResultEnum;
import lombok.extern.slf4j.Slf4j;


@Slf4j
public class GeneralException extends RuntimeException {
    /**
     * 序列化参数
     */
    private static final long serialVersionUID = 1L;
    /**
     * 错误码
     */
    private Integer code;
    private ResultEnum resultEnum;

    public GeneralException(String message){
        super(message);
        this.resultEnum = null;
        this.code = -1;
    }

    public GeneralException(ResultEnum resultEnum) {
        super(resultEnum.getMessage());
        this.resultEnum = resultEnum;
        this.code = resultEnum.getCode();
    }

    public GeneralException(Integer code, String message) {
        super(message);
        this.resultEnum = null;
        this.code = code;
    }

    public static GeneralException getException(Exception e, ResultEnum resultEnum) {
        GeneralException exception;
        if (e instanceof GeneralException) {
            exception = (GeneralException) e;
        } else if (e instanceof NullPointerException) {
            throw (NullPointerException) e;
        } else {
            if (log.isWarnEnabled()) {
                log.warn("The Exception occurred", e);
            }
            exception = new GeneralException(resultEnum);
        }
        return exception;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public ResultEnum getResultEnum() {
        return resultEnum;
    }

    public void setResultEnum(ResultEnum resultEnum) {
        this.resultEnum = resultEnum;
    }
}
