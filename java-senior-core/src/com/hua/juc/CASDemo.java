package com.hua.juc;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * cas示例程序
 *
 * @author: huabin
 * @date: 2021/9/22 上午6:26
 */
public class CASDemo {

    public static void main(String[] args) {
        // 创建一个原子类
        AtomicInteger atomicInteger = new AtomicInteger(5);

        /*compareAndSet第一个参数是期望值，第二个是更新值，只有期望值和原来的值相等时，才会更新*/
        System.out.println(atomicInteger.compareAndSet(5,2021)+"\t current data: "+atomicInteger.get());
        System.out.println(atomicInteger.compareAndSet(5, 2020) + "\t current data: " + atomicInteger.get());

        atomicInteger.getAndIncrement();
    }

}
