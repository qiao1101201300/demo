package com.qs.demo.desig.adapter.classadapter;

import com.qs.demo.desig.adapter.IVoltage220V;
import com.qs.demo.desig.adapter.IVoltage5V;

public class ClassAdapter extends IVoltage220V implements IVoltage5V {
    @Override
    public int output5V() {
        return super.output220V() / 44;
    }
}
