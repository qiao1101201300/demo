package com.qs.demo.desig.flyweight;

public class ConcreateWebSite extends WebSite {
    private String type = "";

    public ConcreateWebSite(String type) {
        this.type = type;
    }

    @Override
    public void use(User user) {
        System.out.println(user.getName() + "use " + type);
    }
}
