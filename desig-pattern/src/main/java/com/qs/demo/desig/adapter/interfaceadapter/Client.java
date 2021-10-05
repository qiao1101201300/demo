package com.qs.demo.desig.adapter.interfaceadapter;

public class Client {
    public static void main(String[] args) {
        AbsAdapter absAdapter = new AbsAdapter() {
            @Override
            public void m1() {
                System.out.println("test");
            }
        };
        absAdapter.m1();
    }
}
