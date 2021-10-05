package com.qs.demo.desig.singleton;

public class Singleton5 {
    private Singleton5() {

    }

    private static Singleton5 singleton5;

    public static Singleton5 getInstance() {
        if (singleton5 == null) {
            synchronized (Singleton5.class){
                singleton5 = new Singleton5();
            }
        }
        return singleton5;
    }
}
