package com.qs.demo.desig.memento;

import java.util.ArrayList;
import java.util.List;

public class Originator {
    List<Memento> mementos = new ArrayList();

    public void setMementos(Memento memento) {
        mementos.add(memento);
    }

    public Memento getMementos(int index) {
        return mementos.get(index);
    }
}
