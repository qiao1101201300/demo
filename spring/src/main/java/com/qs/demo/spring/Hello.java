package com.qs.demo.spring;

public class Hello {
    private String name;
    private String value;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Hello(String name, String value) {
        this.name = name;
        this.value = value;
    }

    public Hello() {
    }
}
