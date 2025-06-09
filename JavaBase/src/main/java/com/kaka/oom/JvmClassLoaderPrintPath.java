package com.kaka.oom;

import java.lang.reflect.Field;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.List;

public class JvmClassLoaderPrintPath {
    public static void main(String[] args) {

        // 获取启动类加载器
        ClassLoader bootstrapClassLoader = Object.class.getClassLoader();
        System.out.println("启动类加载器: " + bootstrapClassLoader);

        // 获取应用类加载器
        ClassLoader appClassLoader = ClassLoader.getSystemClassLoader();
        System.out.println("应用类加载器: " + appClassLoader);

        // 获取应用类加载器的父加载器
        ClassLoader parentClassLoader = appClassLoader.getParent();
        System.out.println("应用类加载器的父加载器: " + parentClassLoader);

    }
}
