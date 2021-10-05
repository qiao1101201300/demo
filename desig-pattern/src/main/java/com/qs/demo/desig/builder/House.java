package com.qs.demo.desig.builder;

public class House {
    private String name;
    private int basic;
    private int wall;
    private int roof;

    public House(String name) {
        this.name = name;
    }

    public int getBasic() {
        return basic;
    }

    public void setBasic(int basic) {
        this.basic = basic;
    }

    public int getWall() {
        return wall;
    }

    public void setWall(int wall) {
        this.wall = wall;
    }

    public int getRoof() {
        return roof;
    }

    public void setRoof(int roof) {
        this.roof = roof;
    }

    @Override
    public String toString() {
        return "House{" +
                "name='" + name + '\'' +
                ", basic=" + basic +
                ", wall=" + wall +
                ", roof=" + roof +
                '}';
    }
}
