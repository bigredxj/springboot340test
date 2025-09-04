package com.example.springboot340test.classloader;

public class ParserName {

    public String parse(String str){
        String[] arr=str.split(",");
        return arr[0];
    }
}
