package com.qs.demo.desig.builder;

public class Diector {
    private Builder builder;

    public Diector(Builder builder) {
        this.builder = builder;
    }

    public void setBuilder(Builder builder) {
        this.builder = builder;
    }

    public House builderHouse() {
        House house = builder.createHouse();
        house.setBasic(builder.builderBasic());
        house.setRoof(builder.builderRoof());
        house.setWall(builder.builderWall());
        return house;
    }

}
