package com.qs.demo.desig.memento;

public class Caretaker {
    int status = 100;

    public void setStatus(int status) {
        this.status = status;
    }

    public Memento ceateMemento() {
        return new Memento(status);
    }

    public void setStatus(Memento mementos) {
        setStatus(mementos.status);
    }
}
