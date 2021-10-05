package com.qs.demo.desig.builder;

public class CommonHouse implements Builder {
    @Override
    public House createHouse() {
        return new House("普通房子");
    }

    @Override
    public int builderBasic() {
        return 10;
    }

    @Override
    public int builderWall() {
        return 10;
    }

    @Override
    public int builderRoof() {
        return 10;
    }

}
