package com.qs.demo.desig.visitor;

public class Client {

    public static void main(String[] args) {
        Persion persion = new Persion("111");
        persion.accpet(new Success());
        Persion persion2 = new Persion("222");
        persion2.accpet(new Fail());

    }
}
