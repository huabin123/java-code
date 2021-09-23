package com.hua.anotation;

import java.lang.annotation.*;

/**
 * 自定义注解，标注哪些类需要进行IOC管理
 *
 * @author: huabin
 * @date: 2021/9/23 上午10:06
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
public @interface MyComponent {

    String value() default "";

}
