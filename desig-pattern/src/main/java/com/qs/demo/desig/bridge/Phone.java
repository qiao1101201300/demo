package com.qs.demo.desig.bridge;

public abstract class Phone {
    private String type;
    private Brand brand;

    public void setType(String type) {
        this.type = type;
    }

    public Phone(Brand brand) {
        this.brand = brand;
    }
    void open(){
        System.out.println(type);
        brand.open();
    }

    void call(){
        System.out.println(type);
        brand.call();
    }

    void close(){
        System.out.println(type);
        brand.close();
    }
}
