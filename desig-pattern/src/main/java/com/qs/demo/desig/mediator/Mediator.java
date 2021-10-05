package com.qs.demo.desig.mediator;

public interface Mediator {
    void register(Colleague colleague);

    void relay(Colleague colleague);

}
