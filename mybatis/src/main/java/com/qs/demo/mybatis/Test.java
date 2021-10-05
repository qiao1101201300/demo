package com.qs.demo.mybatis;

import cn.hutool.core.io.FileUtil;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class Test {

    //    public static void main(String[] args) {
//        SqlSession sqlSession = null;
//        try {
//            String file = "mybatis.xml";
//            InputStream inputStream = Resources.getResourceAsStream(file);
//            SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
//            sqlSession = sqlSessionFactory.openSession(ExecutorType.BATCH);
//
//            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
//        } catch (IOException e) {
//            e.printStackTrace();
//        } finally {
//            if (sqlSession != null) {
//                sqlSession.close();
//            }
//        }
//    }
    public static void main(String[] args) {
        List<File> files = FileUtil.loopFiles("E:\\百度网盘\\BaiduNetdiskDownload\\杀戮都市PDF");
        files.stream().forEach(file -> {
            FileUtil.rename(file, file.getName().replace("【淘宝｜漫友窝】", ""), true);
        });
    }
}
