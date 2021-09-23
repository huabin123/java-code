package com.hua.anotation;

import java.lang.annotation.*;

/**
 * 自定义注解，标注哪些属性进行依赖注入
 *
 * @author: huabin
 * @date: 2021/9/23 上午10:10
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Documented
public @interface MyAutowried {

    String value() default "";

}
