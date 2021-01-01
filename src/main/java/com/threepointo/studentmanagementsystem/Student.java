package com.threepointo.studentmanagementsystem;

import java.util.Date;

public class Student {
    String name;
    String date;
    String roll;
    String dept;
    String phone;
    String clss;

    public Student()
    {}
    public Student(String name, String date, String roll, String dept, String phone, String clss) {
        this.name = name;
        this.date = date;
        this.roll = roll;
        this.dept = dept;
        this.phone = phone;
        this.clss = clss;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getRoll() {
        return roll;
    }

    public void setRoll(String roll) {
        this.roll = roll;
    }

    public String getDept() {
        return dept;
    }

    public void setDept(String dept) {
        this.dept = dept;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getClss() {
        return clss;
    }

    public void setClss(String clss) {
        this.clss = clss;
    }
}
