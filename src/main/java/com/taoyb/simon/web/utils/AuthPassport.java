package com.taoyb.simon.web.utils;

import java.lang.annotation.*;

/**
 * Created by taoyb on 2017-04-20.
 */
@Documented //注解表明这个注解应该被 javadoc工具记录. 默认情况下,javadoc是不包括注解的
@Inherited //标记,然后用定义的注解来标注另一个父类, 父类又有一个子类(subclass),则父类的所有属性将被继承到它的子类中
@Target(ElementType.METHOD) //用于描述注解的使用范围（即：被描述的注解可以用在什么地方）
@Retention(RetentionPolicy.RUNTIME) //这种类型的Annotations将被JVM保留,所以他们能在运行时被JVM或其他使用反射机制的代码所读取和使用
public @interface AuthPassport {
    boolean validate() default true;
    String authority() default "";
}
