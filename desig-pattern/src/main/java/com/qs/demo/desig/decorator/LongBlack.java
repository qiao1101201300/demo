package com.qs.demo.desig.decorator;

public class LongBlack extends Drink {

    public LongBlack() {
        setDesc("LongBlack");
        setPrice(8.0f);
    }

    @Override
    float cost() {
        return getPrice();
    }
}
