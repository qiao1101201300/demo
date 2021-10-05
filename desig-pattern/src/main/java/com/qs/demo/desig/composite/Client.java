package com.qs.demo.desig.composite;

public class Client {
    public static void main(String[] args) {
        University university = new University("清华");
        College college1 = new College("软件学院");
        College college2 = new College("信息学院");
        university.add(college1);
        university.add(college2);
        DepartMent department1 = new DepartMent("软件工程");
        DepartMent department2 = new DepartMent("信息工程");
        college1.add(department1);
        college2.add(department2);
        university.print();
    }
}
