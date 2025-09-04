package com.example.springboot340test.object.vo;

import com.alibaba.fastjson.JSONObject;
import com.example.springboot340test.enums.ResultEnum;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.io.Serializable;


@Data
public class JsonResponse<T> implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 状态码
     */
    private Integer code;
    /**
     * 状态描述,错误描述
     */
    private String message;
    /**
     * 返回数据
     */
    private T data;

    public JsonResponse() {}

    public JsonResponse(T data) {
        this.code = ResultEnum.SUCCESS.getCode();
        this.message = ResultEnum.SUCCESS.getMessage();
        this.data = data;
    }


    public JsonResponse(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public JsonResponse(int code) {
        this.code = code;
    }
    public JsonResponse(String message) {
        this.message = message;
    }


    @Override
    public String toString() {
        return "ResultBean [code=" + code + ", message=" + message + ", data=" + data + "]";
    }

    public Integer getCode() {
        return code;
    }
    public void setCode(Integer code) {
        this.code = code;
    }
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        if(data == null){
            return new JSONObject();
        }else{
            return data;
        }
    }
    @JsonIgnore
    public T getOriginalData() {
        return data;
    }

    public void setData(T bodyData) {
        this.data = bodyData;
    }

}
