package com.test.jie.learn.myAnnotation;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

//注解主要被反射读取
// 反射只能读取内存中的字节码信息
// RetentionPolicy.CLASS指的是保留到字节码文件，它在磁盘内，而不是内存中。
// 虚拟机将字节码文件加载进内存后注解会消失 要想被反射读取，保留策略只能用RUNTIME，即运行时仍可读取
public class TestDemo {
    public static void main(String[] args) {
        //反射获取类上注解
        Class<Demo> clazz = Demo.class;
        DemoGet annotationClazz = clazz.getAnnotation(DemoGet.class);
        System.out.println(annotationClazz.getValue());

        //获取成员变量上的注释
        Field[] fields = clazz.getFields();
        for (Field field : fields) {
            DemoGet cur = field.getAnnotation(DemoGet.class);
            System.out.println(cur.getValue());
        }

        //从方法上的注释
        Method[] methods = clazz.getMethods();
        for (Method method : methods) {
            DemoGet cur = method.getAnnotation(DemoGet.class);
            if (cur != null) {
                System.out.println(cur.getValue());
            }
        }
    }
}
