package com.kaka.oom;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class CustomClassLoader extends ClassLoader {

    private final String classPath;

    public CustomClassLoader(String classPath) {
        this.classPath = classPath;
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        byte[] classBytes = loadClassBytes(name);
        if (classBytes == null) {
            throw new ClassNotFoundException("类未找到: " + name);
        }
        return defineClass(name, classBytes, 0, classBytes.length);
    }

    private byte[] loadClassBytes(String className) {
        // 将包名转换为文件路径，例如 com.example.MyClass -> com/example/MyClass.class
        String path = classPath + File.separatorChar
                + className.replace('.', File.separatorChar) + ".class";
        try (InputStream is = new FileInputStream(path);
             ByteArrayOutputStream os = new ByteArrayOutputStream()) {
            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = is.read(buffer)) != -1) {
                os.write(buffer, 0, bytesRead);
            }
            return os.toByteArray();
        } catch (IOException e) {
            System.err.println("加载类文件出错: " + e.getMessage());
            return null;
        }
    }

    public static void main(String[] args) throws Exception {
        CustomClassLoader loader = new CustomClassLoader("D:\\code_space\\JAVA\\kaka\\JavaAdvanced\\JavaAdvanced\\JavaBase\\src\\main\\java");
        Class<?> clazz = loader.loadClass("com.kaka.common.Student");
        Object instance = clazz.getDeclaredConstructor().newInstance();
        System.out.println("实例创建成功: " + instance);
        Method[] methods = instance.getClass().getMethods();
        for (Method method : methods) {
            System.out.println("method : " + method.getName());
        }
        Method[] declaredMethods = instance.getClass().getDeclaredMethods();
        for (Method declaredMethod : declaredMethods) {
            System.out.println("declareMethod : " + declaredMethod.getName());
        }

        // 2. 设置私有属性
        Field name = clazz.getDeclaredField("name");
        name.setAccessible(true);
        name.set(instance, "kaka");

        // 3. 获取方法对象（假设方法名为 declareMethod，无参数）
        Method method = clazz.getDeclaredMethod("hello");
        // 4. 如果是私有方法，设置可访问性
        method.setAccessible(true);
        // 5. 调用方法
        Object result = method.invoke(instance);
    }
}
