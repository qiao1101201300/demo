package com.qs.demo.desig.decorator;

public class Soy extends Decorator {
    public Soy(Drink drink) {
        super(drink);
        super.setDesc("Soy");
        super.setPrice(1.0f);
    }
}
