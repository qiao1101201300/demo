package com.qs.demo.desig.singleton;

public class Singleton7 {

    public static Singleton7 getInstance() {
        return SingletonInstance.singletonInstance;
    }

    private static class SingletonInstance {
        private final static Singleton7 singletonInstance = new Singleton7();
    }
}
