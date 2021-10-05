package com.qs.demo.desig.visitor;

public class Success extends Action {
    @Override
    public String result(Persion persion) {
        return persion.getName() + " success";
    }
}
