package com.qs.demo.desig.singleton;

public class Singleton1 {
    private Singleton1() {

    }

    private static Singleton1 singleton1 = new Singleton1();

    private static Singleton1 getInstance() {
        return singleton1;
    }
}
