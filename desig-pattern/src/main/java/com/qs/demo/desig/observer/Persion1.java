package com.qs.demo.desig.observer;

public class Persion1 implements Observer {
    @Override
    public void recevice() {
        System.out.println("测试1收到牛奶");
    }
}
