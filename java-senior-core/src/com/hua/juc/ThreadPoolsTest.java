package com.hua.juc;

import org.junit.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * 四种线程池的创建及特点
 *
 * @author: huabin
 * @date: 2021/9/21 上午11:10
 */
public class ThreadPoolsTest {

    @Test
    public void NewFixedThreadPool(){

        // 提供指定线程数量的线程池
        ExecutorService service = Executors.newFixedThreadPool(10);
        ExecutorService service1 = (ThreadPoolExecutor)service;

        service.execute(new NumberThread());
        service.execute(new NumberThread1());

    }
}

class NumberThread implements Runnable{

    @Override
    public void run() {
        for (int i = 0; i < 101; i++) {
            if (i%2 == 0) {
                System.out.println("Thread.currentThread().getName() :" + Thread.currentThread().getName()+i);
            }
        }
    }
}

class NumberThread1 implements Runnable{

    @Override
    public void run() {
        for (int i = 0; i < 101; i++) {
            if (i%2 != 0) {
                System.out.println("Thread.currentThread().getName() :" + Thread.currentThread().getName()+i);
            }
        }
    }
}
