package com.myspring.demo.entity;

import javax.persistence.*;
import javax.persistence.GeneratedValue;

@Entity
@Table(name="department")
public class Department {
    @Id
    @GeneratedValue
    private int departmentId;

    @Column
    private String departmentName;

    @Column
    private int manager;

    @Column
    private String stime;

    @Column
    private String etime;

    @Column
    private String date;

    @Column
    private int penalty;

    public int getDepartmentId() {
        return departmentId;
    }

    public int getManager() {
        return manager;
    }

    public String getStime() {
        return stime;
    }

    public String getEtime() {
        return etime;
    }

    public String getDate() {
        return date;
    }

    public int getPenalty() {
        return penalty;
    }

    public void setDepartmentId(int departmentId) {
        this.departmentId = departmentId;
    }

    public void setManager(int manager) {
        this.manager = manager;
    }

    public void setStime(String stime) {
        this.stime = stime;
    }

    public void setEtime(String etime) {
        this.etime = etime;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setPenalty(int penalty) {
        this.penalty = penalty;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }
}
