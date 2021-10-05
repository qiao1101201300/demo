package com.qs.demo.desig.builder;

public class HighHouse implements Builder {

    @Override
    public House createHouse() {
        return new House("高楼");
    }

    @Override
    public int builderBasic() {
        return 50;
    }

    @Override
    public int builderWall() {
        return 50;
    }

    @Override
    public int builderRoof() {
        return 50;
    }

}
