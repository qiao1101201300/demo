package com.qs.demo.desig.adapter;

import com.qs.demo.desig.adapter.classadapter.ClassAdapter;
import com.qs.demo.desig.adapter.objectadapter.ObjectAdapter;

public class Client {
    public static void main(String[] args) {
        Phone phone = new Phone();
        phone.charging(new ClassAdapter());
        phone.charging(new ObjectAdapter(new IVoltage220V()));
    }
}
