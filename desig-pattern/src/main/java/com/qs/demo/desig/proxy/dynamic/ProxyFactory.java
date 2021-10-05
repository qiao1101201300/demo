package com.qs.demo.desig.proxy.dynamic;


import java.lang.reflect.Proxy;

public class ProxyFactory {
    private Object object;

    public ProxyFactory(Object object) {
        this.object = object;
    }
    public Object getProxyInstance(){
        System.out.println("JDK 代理开始");
        return Proxy.newProxyInstance(object.getClass().getClassLoader(),object.getClass().getInterfaces(),(proxy, method, args) -> {
            proxy = method.invoke(object,args);
            return proxy;
        });
    }
}
