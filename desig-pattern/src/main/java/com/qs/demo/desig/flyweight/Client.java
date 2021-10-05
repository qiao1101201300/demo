package com.qs.demo.desig.flyweight;

public class Client {
    public static void main(String[] args) {
        WebSiteFactory webSiteFactory = new WebSiteFactory();
        WebSite webSite1 = webSiteFactory.getWebSite("新闻");
        webSite1.use(new User("小1"));
        WebSite webSite2 = webSiteFactory.getWebSite("新闻");
        webSite2.use(new User("小2"));
        WebSite webSite3 = webSiteFactory.getWebSite("网站");
        webSite3.use(new User("小3"));
        WebSite webSite4 = webSiteFactory.getWebSite("网站");
        webSite4.use(new User("小4"));
        System.out.println(webSiteFactory.getWebSiteCount());
    }
}
