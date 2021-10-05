package com.qs.demo.desig.factory.abs;

import com.qs.demo.desig.factory.Animal;

public interface AbsFactory {
    Animal getAnimal(int type);
}
