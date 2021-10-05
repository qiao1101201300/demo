package com.qs.demo.desig.proxy;

import com.qs.demo.desig.proxy.dynamic.ProxyFactory;
import com.qs.demo.desig.proxy.staticproxy.ITeacherDao;

public class Client {
    public static void main(String[] args) {

        TeacherDao teacherDao = new TeacherDaoImpl();
        ITeacherDao iteacherDao = new ITeacherDao(teacherDao);
        iteacherDao.teacher();
        ProxyFactory proxyFactory = new ProxyFactory(teacherDao);
        TeacherDao proxyInstance = (TeacherDao) proxyFactory.getProxyInstance();
        proxyInstance.teacher();
    }
}
