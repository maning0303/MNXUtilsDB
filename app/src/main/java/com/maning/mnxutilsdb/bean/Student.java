package com.maning.mnxutilsdb.bean;

import org.xutils.db.annotation.Column;
import org.xutils.db.annotation.Table;

/**
 * Created by maning on 2017/8/3.
 */


@Table(name = "Student")
public class Student {

    @Column(name = "id" , isId = true)
    private int ID;

    @Column(name = "name")
    private String name;

    @Column(name = "age")
    private int age;

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Student{" +
                "ID=" + ID +
                ", name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
