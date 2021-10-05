package com.qs.demo.desig.template;

public class HighBuilder extends Builder {
    @Override
    public House createHouse() {
        House house = new House();
        house.setName("高楼");
        return house;
    }

    @Override
    public boolean isRoof() {
        return true;
    }
}
