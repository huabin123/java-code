package com.hua.bean;

import com.hua.anotation.MyComponent;

/**
 * @author: huabin
 * @date: 2021/9/23 下午12:03
 * @Version 1.0
 */

@MyComponent
public class UserDao {
    public void findUser(String userName){
        System.out.println("找到一个名字叫："+userName);
    }
}
