package com.qs.demo.desig.singleton;

public class Singleton6 {
    private volatile static Singleton6 singleton6 = null;

    private Singleton6() {

    }

    private static Singleton6 getInstance() {
        if (singleton6 == null) {
            synchronized (Singleton6.class) {
                if (singleton6 == null) {
                    singleton6 = new Singleton6();
                }
            }
        }
        return singleton6;
    }
}
