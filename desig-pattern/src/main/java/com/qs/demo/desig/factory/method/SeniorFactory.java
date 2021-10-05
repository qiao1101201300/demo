package com.qs.demo.desig.factory.method;

import com.qs.demo.desig.factory.Animal;
import com.qs.demo.desig.factory.Man;
import com.qs.demo.desig.factory.Woman;

public class SeniorFactory extends MethodFactory {
    @Override
    public Animal getAnimal(int type) {
        Animal animal = null;
        switch (type) {
            case 0:
                animal = new Man();
                break;
            case 1:
                animal = new Woman();
                break;
        }
        return animal;
    }
}
