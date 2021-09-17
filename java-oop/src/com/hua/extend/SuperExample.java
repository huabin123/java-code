package com.hua.extend;

/**
 * @author: huabin
 * @date: 2021/9/6 10:54 上午
 */
public class SuperExample {

    protected int x;
    protected int y;

    public SuperExample(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void func() {
        System.out.println("SuperExample.func()");
    }

    public static class SuperExtendExample extends SuperExample {

        public SuperExtendExample(int x, int y, int z) {
            super(x, y);
        }

        @Override
        public void func() {
            super.func();
            System.out.println("SuperExtendExample.func()");
        }
    }

    public static void main(String[] args){
        SuperExample e = new SuperExtendExample(1, 2, 3);
        e.func();
    }

}


