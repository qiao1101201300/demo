package com.qs.demo.desig.factory.simple;

import com.qs.demo.desig.factory.*;

public class SimpleFactory {
    public Animal getAnimal(int type) {
        Animal animal = null;
        switch (type) {
            case 0:
                animal = new Man();
                break;
            case 1:
                animal = new Woman();
                break;
            case 2:
                animal = new Dog();
                break;
            case 3:
                animal = new Cat();
                break;
        }
        return animal;
    }
}
