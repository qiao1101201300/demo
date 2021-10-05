package com.qs.demo.desig.builder;

public class Client {
    public static void main(String[] args) {
        Diector diector = new Diector(new CommonHouse());
        System.out.println(diector.builderHouse().toString());
        diector.setBuilder(new HighHouse());
        System.out.println(diector.builderHouse().toString());
    }
}
