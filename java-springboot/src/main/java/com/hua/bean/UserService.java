package com.hua.bean;

import com.hua.anotation.MyAutowried;
import com.hua.anotation.MyComponent;
import com.hua.bean.UserDao;

/**
 * @author: huabin
 * @date: 2021/9/23 下午12:09
 * @Version 1.0
 */

@MyComponent
public class UserService {

    @MyAutowried
    private UserDao userDao;

    public void findUser(String userName){
        userDao.findUser(userName);
    }

}
