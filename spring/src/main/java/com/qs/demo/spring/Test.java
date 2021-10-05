package com.qs.demo.spring;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.ClassPathResource;

public class Test {
    public static void main(String[] args){
        BeanFactory factory = new XmlBeanFactory(new ClassPathResource("spring.xml"));
        Hello bean = factory.getBean(Hello.class);
        System.out.println(bean.getName());
        GetBeanTest test = factory.getBean(GetBeanTest.class);
        test.showMe();
        TestChangeMe testChangeMe = factory.getBean(TestChangeMe.class);
        testChangeMe.changeMe();
    }
}
