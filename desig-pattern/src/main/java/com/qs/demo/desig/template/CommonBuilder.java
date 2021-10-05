package com.qs.demo.desig.template;

public class CommonBuilder extends Builder {

    @Override
    public House createHouse() {
        House house = new House();
        house.setName("一般房子");
        return house;
    }

    @Override
    public boolean isRoof() {
        return false;
    }
}
