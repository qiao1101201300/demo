package com.qs.demo.desig.visitor;

public class Fail extends Action {
    @Override
    public String result(Persion persion) {
        return persion.getName() + " fail";
    }
}
