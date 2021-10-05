package com.qs.demo.desig.adapter;

public class Phone {
    public void charging(IVoltage5V iVoltage5V) {
        if (iVoltage5V.output5V() == 5) {
            System.out.println("开始充电");
        } else {
            System.out.println("电压不符");
        }
    }
}
