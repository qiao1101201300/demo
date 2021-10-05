package com.qs.demo.desig.composite;

public abstract class OrganizationComponet {
    private String name;

    public OrganizationComponet(String name) {
        this.name = name;
    }

    public void add(OrganizationComponet organizationComponet) {
        throw new UnsupportedOperationException();
    }

    public void remove(OrganizationComponet organizationComponet) {
        throw new UnsupportedOperationException();
    }

    public abstract void print();

    public String getName() {
        return name;
    }
}
