package com.qs.demo.desig.singleton;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Singleton6 {
    private volatile static Singleton6 singleton6 = null;

    private Singleton6() {

    }

    public static Singleton6 getInstance() {
        if (singleton6 == null) {
            synchronized (Singleton6.class) {
                if (singleton6 == null) {
                    singleton6 = new Singleton6();
                }
            }
        }
        return singleton6;
    }

    private Object readResolve() {
        System.out.println("readResolve");
        return getInstance();
    }

    public static void main(String[] args) {
        Singleton6 singleton6 = Singleton6.getInstance();
        System.out.println(singleton6);
        try {
            Class<?> forName = Class.forName("com.qs.demo.desig.singleton.Singleton6");
            Method method = forName.getMethod("getInstance", null);
            Singleton6 singleton61 = (Singleton6) method.invoke(null, null);
            System.out.println(singleton61);
        } catch (IllegalAccessException | ClassNotFoundException | NoSuchMethodException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}
