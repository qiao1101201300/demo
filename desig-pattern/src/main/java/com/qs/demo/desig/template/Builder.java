package com.qs.demo.desig.template;

public abstract class Builder {
    private House house;

    public House build() {
        house = createHouse();
        buiderBasic();
        buiderWall();
        if (isRoof()) {
            buiderroof();
        }
        return house;
    }

    public abstract House createHouse();

    public abstract boolean isRoof();

    public void buiderBasic() {
        System.out.println(house.getName() + "打地基");
    }

    public void buiderWall() {
        System.out.println(house.getName() + "建围墙");
    }

    public void buiderroof() {
        System.out.println(house.getName() + "铺地板");
    }

}
