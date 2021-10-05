package com.qs.demo.desig.visitor;

public class Persion {

    private String name;

    public Persion(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    void accpet(Action action) {
        System.out.println(action.result(this));
    }
}
