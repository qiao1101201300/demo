package com.qs.demo.desig.decorator;

public class Chocolate extends Decorator {
    public Chocolate(Drink drink) {
        super(drink);
        setDesc("Chocolate");
        setPrice(3.0f);
    }
}
