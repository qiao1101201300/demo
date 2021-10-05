package com.qs.demo.desig.observer;

import java.util.ArrayList;
import java.util.List;

public interface Subject {
    List<Observer> observers = new ArrayList<>();

    default void register(Observer observer) {
        observers.add(observer);
    }

    default void remove(Observer observer) {
        observers.remove(observer);
    }

    default void notifySubject() {
        observers.forEach(Observer::recevice);
    }
}
