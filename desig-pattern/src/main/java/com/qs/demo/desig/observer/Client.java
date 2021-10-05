package com.qs.demo.desig.observer;

public class Client {
    public static void main(String[] args) {
        Persion1 persion1 = new Persion1();
        Persion2 persion2 = new Persion2();
        Milk milk = new Milk();
        milk.register(persion1);
        milk.register(persion2);
        milk.notifySubject();
    }
}
