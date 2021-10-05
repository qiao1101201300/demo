package com.qs.demo.desig.facade;

public class Popcron {
    private static Popcron instance = new Popcron();

    public static Popcron getInstance() {
        return instance;
    }
    public void on(){
        System.out.println("Popcron on");
    }
    public void off(){
        System.out.println("Popcron off");
    }
    public void pop(){
        System.out.println("Popcron pop");
    }
}
