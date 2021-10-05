package com.qs.demo.desig.bridge;

public class HuaWei implements Brand {
    @Override
    public void open() {
        System.out.println("华为开机");
    }

    @Override
    public void call() {
        System.out.println("华为打电话");
    }

    @Override
    public void close() {
        System.out.println("华为开关机");
    }
}