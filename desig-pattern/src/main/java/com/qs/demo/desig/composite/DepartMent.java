package com.qs.demo.desig.composite;

import java.util.ArrayList;
import java.util.List;

public class DepartMent extends OrganizationComponet {

    private List<OrganizationComponet> organizationComponets = new ArrayList<>();

    public DepartMent(String name) {
        super(name);
    }

    @Override
    public void add(OrganizationComponet organizationComponet) {
        organizationComponets.add(organizationComponet);
    }

    @Override
    public void remove(OrganizationComponet organizationComponet) {
        organizationComponets.remove(organizationComponet);
    }

    @Override
    public void print() {
        System.out.println(getName());
        for (OrganizationComponet organizationComponet : organizationComponets) {
            organizationComponet.print();
        }
    }
}
