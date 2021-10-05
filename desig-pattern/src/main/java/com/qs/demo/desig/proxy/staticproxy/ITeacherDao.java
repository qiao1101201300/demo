package com.qs.demo.desig.proxy.staticproxy;

import com.qs.demo.desig.proxy.TeacherDao;

public class ITeacherDao {
    private TeacherDao teacherDao;

    public ITeacherDao(TeacherDao teacherDao) {
        this.teacherDao = teacherDao;
    }

    public void teacher() {
        System.out.println("开始教课");
        teacherDao.teacher();
        System.out.println("结束教课");
    }
}
