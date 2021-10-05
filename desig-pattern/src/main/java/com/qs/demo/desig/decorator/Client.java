package com.qs.demo.desig.decorator;

public class Client {
    public static void main(String[] args) {
        Drink drink = new LongBlack();
        drink = new Chocolate(drink);
        drink = new Soy(drink);
        System.out.println(drink.cost());
    }
}
