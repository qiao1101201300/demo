package com.qs.demo.desig.facade;

public class TheaterLight {
    private static TheaterLight instance = new TheaterLight();

    public static TheaterLight getInstance() {
        return instance;
    }
    public void up(){
        System.out.println("TheaterLight up");
    }
    public void on(){
        System.out.println("TheaterLight on");
    }
    public void off(){
        System.out.println("TheaterLight off");
    }

    public void dim() {
        System.out.println("TheaterLight dim");
    }
}
