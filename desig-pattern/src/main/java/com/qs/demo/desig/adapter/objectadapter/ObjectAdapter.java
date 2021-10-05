package com.qs.demo.desig.adapter.objectadapter;

import com.qs.demo.desig.adapter.IVoltage220V;
import com.qs.demo.desig.adapter.IVoltage5V;

public class ObjectAdapter implements IVoltage5V {
    private IVoltage220V iVoltage220V;

    public ObjectAdapter(IVoltage220V iVoltage220V) {
        this.iVoltage220V = iVoltage220V;
    }

    @Override
    public int output5V() {
        return iVoltage220V.output220V() / 44;
    }
}
