package com.example.springboot340test.classloader;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;

public class MyClassLoader extends ClassLoader {
    private String path;
    public MyClassLoader(String path){
        this.path=path;
    }
    @Override
    public Class<?> loadClass(String name) throws ClassNotFoundException {
        // 首先尝试使用父类加载器加载类
        try {
            return super.loadClass(name);
        } catch (ClassNotFoundException e) {
            // 如果父类加载器无法加载类，则自己加载类
            return findClass(name);
        }
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        // 在这里实现自己的类加载逻辑，例如从特定位置加载类文件
        // 这里只是一个示例，实际实现需要根据具体需求进行处理
        byte[] classData = loadClassData();
        if (classData == null) {
            throw new ClassNotFoundException();
        }
        return defineClass(name, classData, 0, classData.length);
    }

    private byte[] loadClassData() {
        // 在这里实现加载类文件的逻辑，返回类文件的字节数组
        // 这里只是一个示例，实际实现需要根据具体需求进行处理
        try {
            FileInputStream fis = new FileInputStream(path);
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024];
            int len;
            while ((len = fis.read(buffer)) != -1) {
                bos.write(buffer, 0, len);
            }
            fis.close();
            bos.close();
            return bos.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
