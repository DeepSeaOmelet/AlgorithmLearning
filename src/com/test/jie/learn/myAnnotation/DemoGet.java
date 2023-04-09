package com.test.jie.learn.myAnnotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.TYPE})
public @interface DemoGet {
    String getValue() default "no 描述";

    //八种基本数据类型
    int intValue() default 666;
//
//    long longValue() default 0L;
//    //...
//
//    //String
//    String StringValue() default "haha";
//    //枚举
//    CityEnum cityName() default CityEnum.GUANGZHOU;
//    //class类型
//    Class clazz() default Demo.class;
//    //注解
//    DemoGet2 demoGet2()  default @DemoGet2;
//    //几种数组
//    int[] intValueArray() default {0};
//    String[] names() default {"hello"};
//    //...
}

@interface DemoGet2 {
    int getNum() default 100;
}

enum CityEnum {
    GUANGZHOU,
    HANGZHOU,
    SHANGHAI;
}
