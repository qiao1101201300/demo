package com.qs.demo.desig.memento;

public class Client {
    public static void main(String[] args) {
        Caretaker caretaker = new Caretaker();
        Originator originator = new Originator();
        caretaker.setStatus(1000);
        originator.setMementos(caretaker.ceateMemento());
        caretaker.setStatus(2000);
        originator.setMementos(caretaker.ceateMemento());
        caretaker.setStatus(3000);
        originator.setMementos(caretaker.ceateMemento());
        caretaker.setStatus(originator.getMementos(0));
        System.out.println(caretaker.status);
    }
}
