package com.qs.demo.desig.template;

public class Client {
    public static void main(String[] args) {
        Builder builder = new CommonBuilder();
        builder.build();
        builder = new HighBuilder();
        builder.build();
    }
}
