package com.qs.demo.desig.factory.abs;

import com.qs.demo.desig.factory.Animal;
import com.qs.demo.desig.factory.Cat;
import com.qs.demo.desig.factory.Dog;

public class LowerFactory implements AbsFactory {
    @Override
    public Animal getAnimal(int type) {
        Animal animal = null;
        switch (type) {
            case 0:
                animal = new Dog();
                break;
            case 1:
                animal = new Cat();
                break;
        }
        return animal;
    }
}
