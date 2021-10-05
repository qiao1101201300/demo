package com.qs.demo.desig.singleton;

public class Singleton4 {
    private Singleton4() {

    }

    private static Singleton4 singleton4;

    public static synchronized Singleton4 getInstance() {
        if (singleton4 == null) {
            singleton4 = new Singleton4();
        }
        return singleton4;
    }
}
