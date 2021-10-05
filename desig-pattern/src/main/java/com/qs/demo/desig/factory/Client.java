package com.qs.demo.desig.factory;

import com.qs.demo.desig.factory.abs.AbsFactory;
import com.qs.demo.desig.factory.abs.SeniorFactory;
import com.qs.demo.desig.factory.method.MethodFactory;
import com.qs.demo.desig.factory.simple.SimpleFactory;

public class Client {
    public static void main(String[] args) {
        SimpleFactory simpleFactory = new SimpleFactory();
        System.out.println(simpleFactory.getAnimal(2).getName());
        AbsFactory absFactory = new SeniorFactory();
        System.out.println(absFactory.getAnimal(1).getName());
        MethodFactory methodFactory = new com.qs.demo.desig.factory.method.SeniorFactory();
        System.out.println(methodFactory.getAnimal(0).getName());
    }
}
