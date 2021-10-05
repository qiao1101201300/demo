package com.qs.demo.desig.prototype;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class User implements Cloneable, Serializable {
    private String name;
    private User user;
    private List<String> strings = new ArrayList<>();

    public User(String name, User user) {
        this.name = name;
        this.user = user;
    }

    public User shallCopy() {
        User user = null;
        try {
            user = (User) super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return user;
    }

    public User deepCopy1() {
        User user = null;
        try {
            user = (User) super.clone();
            user.user = (User) this.user.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return user;
    }

    public User deepCopy2() {
        User user = null;
        ObjectInputStream ois = null;
        ObjectOutputStream oos = null;
        ByteArrayInputStream bais = null;
        ByteArrayOutputStream baos = null;
        try {
            baos = new ByteArrayOutputStream();
            oos = new ObjectOutputStream(baos);
            oos.writeObject(this);
            bais = new ByteArrayInputStream(baos.toByteArray());
            ois = new ObjectInputStream(bais);
            user = (User) ois.readObject();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                if (ois != null) {
                    ois.close();
                }
                if (bais != null) {
                    bais.close();
                }
                if (oos != null) {
                    oos.close();
                }
                if (baos != null) {
                    baos.close();
                }
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
        return user;
    }

    public static void main(String[] args) {
        User test1 = new User("test1", null);
        User test1Copy = test1.shallCopy();
        System.out.println(test1 == test1Copy);
        User test2 = new User("test2", test1);
        User test2ShallCopy = test2.shallCopy();
        System.out.println(test2.user == test2ShallCopy.user);
        System.out.println(test2.strings == test2ShallCopy.strings);
        User test2deepCopy1 = test2.deepCopy1();
        System.out.println(test2.user == test2deepCopy1.user);
        System.out.println(test2.strings == test2deepCopy1.strings);
        User test2deepCopy2 = test2.deepCopy2();
        System.out.println(test2.user == test2deepCopy2.user);
        System.out.println(test2.strings == test2deepCopy2.strings);
    }

}
