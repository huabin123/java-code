package com.hua.generic;

import org.junit.Test;

import java.util.*;

/**
 *
 * 泛型
 *
 * @author: huabin
 * @date: 2021/9/8 7:27 上午
 */
public class GenericTest {

    @Test
    public void test1(){
        ArrayList list = new ArrayList();

        //需求：存放学生的成绩
        list.add(78);
        list.add(76);
        list.add(89);
        list.add(88);

        //问题一：类型不安全
//        list.add("Tom");
        for(Object score : list){
            //问题二：强转时，可能出现ClassCastException
            int stuScore = (Integer) score;

            System.out.println(stuScore);

        }
    }

    // 在集合中使用泛型
    @Test
    public void test2(){

        ArrayList<Integer> list = new ArrayList<>();
        list.add(78);
        list.add(76);
        list.add(89);
        list.add(88);
        for (Integer score : list) {
            // 避免强转操作
            int stuScore = score;
            System.out.println("score = " + score);
        }

    }

    //在集合中使用泛型的情况：以HashMap为例
    @Test
    public void test3(){
//        Map<String,Integer> map = new HashMap<String,Integer>();
        //jdk7新特性：类型推断
        Map<String,Integer> map = new HashMap<>();

        map.put("Tom",87);
        map.put("Jerry",87);
        map.put("Jack",67);

//        map.put(123,"ABC");
        //泛型的嵌套
        Set<Map.Entry<String,Integer>> entry = map.entrySet();
        Iterator<Map.Entry<String, Integer>> iterator = entry.iterator();

        while(iterator.hasNext()){
            Map.Entry<String, Integer> e = iterator.next();
            String key = e.getKey();
            Integer value = e.getValue();
            System.out.println(key + "----" + value);
        }



    }

}
