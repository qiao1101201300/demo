package com.qs.demo.desig.bridge;

public class Client {

    public static void main(String[] args) {
        Phone phone = new FoldedPhone(new XiaoMi());
        phone.open();
        phone.call();
        phone.close();
        phone = new UpRightPhone(new HuaWei());
        phone.open();
        phone.call();
        phone.close();
    }
}
