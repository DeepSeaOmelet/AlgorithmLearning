package com.test.jie.learn.myAnnotation;

@DemoGet(getValue = "annotation on class")
public class Demo {
    //但是注解的属性如果有多个，无论是否叫value，都必须写明属性的对应关系：
    @DemoGet(getValue = "annotation on field",intValue = 777)
    public String name;

    @DemoGet(getValue = "annotation on field②")
    public String name2;

    @DemoGet
    public String unknown;

    @DemoGet(getValue = "annotation on Method")
    public void hello(){}

    @DemoGet
    public void defaultMethod(){}
}
