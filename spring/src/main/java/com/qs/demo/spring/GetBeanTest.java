package com.qs.demo.spring;

public abstract class GetBeanTest {

    public void showMe(){
        getBean().showMe();
    }

    public abstract User getBean();
}
