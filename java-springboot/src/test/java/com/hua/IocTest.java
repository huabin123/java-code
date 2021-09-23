package com.hua;

import com.hua.bean.UserService;

/**
 * 测试自定义IOC
 *
 * @author: huabin
 * @date: 2021/9/23 下午2:17
 * @Version 1.0
 */
public class IocTest {

    public static void main(String[] args) throws Exception {
        MyApplicationContext myApplicationContext = new MyApplicationContext();
        UserService userService = (UserService)myApplicationContext.getBean("userService");
        userService.findUser("张三");
    }
    
}
