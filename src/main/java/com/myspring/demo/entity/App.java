package com.myspring.demo.entity;

import javax.persistence.*;

@Entity
@Table(name="app")
public class App {
    @Id
    @GeneratedValue
    private int appId;

    @Column
    private int staffId;

    @Column
    private int recordId;

    @Column
    private String excuse;

    @Column
    private String appDay;

    @Column
    private int departmentId;

    @Column
    private int state;

    public void setState(int state) {
        this.state = state;
    }

    public int getState() {
        return state;
    }

    public int getAppId() {
        return appId;
    }

    public int getStaffId() {
        return staffId;
    }

    public int getRecordId() {
        return recordId;
    }

    public String getExcuse() {
        return excuse;
    }

    public String getAppDay() {
        return appDay;
    }

    public int getDepartmentId() {
        return departmentId;
    }

    public void setAppId(int appId) {
        this.appId = appId;
    }

    public void setStaffId(int staffId) {
        this.staffId = staffId;
    }

    public void setRecordId(int recordId) {
        this.recordId = recordId;
    }

    public void setExcuse(String excuse) {
        this.excuse = excuse;
    }

    public void setAppDay(String appDay) {
        this.appDay = appDay;
    }

    public void setDepartmentId(int departmentId) {
        this.departmentId = departmentId;
    }
}

