package com.example.springboot340test.service;

import com.example.springboot340test.classloader.MyClassLoader;
import com.example.springboot340test.classloader.ParserName;
import org.springframework.stereotype.Service;

import java.lang.reflect.Method;

@Service
public class ClassLoaderService {

    public String getName(){
        String result = "default";
        String str = "aaa,bbb";
        String path = System.getProperty("user.dir")+"/file/ParserName1.class";
        MyClassLoader myClassLoader = new MyClassLoader(path);
        try {

            Class<?> c = myClassLoader.loadClass("com.example.springboot340test.classloader.ParserName1");
            Object o = c.newInstance();
            Method parse = c.getDeclaredMethod("parse", String.class);
            result = (String) parse.invoke(o, str);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }
}
