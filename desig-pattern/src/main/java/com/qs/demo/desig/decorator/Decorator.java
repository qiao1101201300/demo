package com.qs.demo.desig.decorator;

public class Decorator extends Drink {
    private Drink drink;

    public Decorator(Drink drink) {
        this.drink = drink;
    }

    @Override
    float cost() {
        if (drink == null) {
            return this.getPrice();
        } else {
            return this.getPrice() + drink.cost();
        }

    }
}
